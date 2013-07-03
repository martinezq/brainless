package org.mysoft.brainless.neural.core;

import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import org.mysoft.brainless.genetics.chromosome.Chromosome;

//klasa do przerobienia

public class NeuralNetwork extends Chromosome {

	protected OutputLayer outputLayer = new OutputLayer();
	protected InputLayer inputLayer = new InputLayer();
	protected LinkedList<NeuronLayer> hiddenLayers = new LinkedList<>();
	
	public static NeuralNetwork create() {
		NeuralNetwork nn = new NeuralNetwork();
		
		nn.addHiddenLayer(new NeuronLayer(5));
		nn.addHiddenLayer(new NeuronLayer(5));
		
		return nn;
	}

	private static NeuralNetwork duplicate(NeuralNetwork neuralNetwork) {
		NeuralNetwork clone = create();

		for(NeuronLayer layer: neuralNetwork.hiddenLayers) {
			NeuronLayer newLayer = layer.duplicate();
			
			clone.addHiddenLayer(newLayer);
		}
	
		return clone;
	}
	
	public void step() {
		reset();
		for(Entry<Neuron,NetworkOutput> entry: outputLayer.entrySet()) {
			Neuron neuron = entry.getKey();
			NetworkOutput output = entry.getValue();
			
			double value = neuron.calculatedOutput();
			output.perform(value);
		}
	}
	
	public void addInput(NeuronInput input) {
		inputLayer.add(input);
	}
	
	public void addHiddenLayer(NeuronLayer layer) {
		if(hiddenLayers.isEmpty()) {
			layer.connectTo(inputLayer);
		} else {
			layer.connectTo(hiddenLayers.getLast());
		}
		
		hiddenLayers.add(layer);
	}
	
	public void addOutput(NetworkOutput output) {
		int lastLayerNeuronCount = hiddenLayers.getLast().size();
		int outputCount = outputLayer.size();
		
		if(outputCount == lastLayerNeuronCount) {
			throw new IllegalStateException("Cannot add output. All " + lastLayerNeuronCount + " neurons connected to output");
		}
		
		Neuron nextNotConnectedNeuron = hiddenLayers.getLast().get(outputCount);
		
		outputLayer.put(nextNotConnectedNeuron, output);
	}
	
	protected void reset() {
		for(NeuronLayer layer: hiddenLayers) {
			for(Neuron neuron: layer) {
				neuron.reset();
			}
		}
		
		for(Neuron neuron: outputLayer.keySet()) {
			neuron.reset();
		}
	}

	@Override
	public Chromosome mutate() {
		for(NeuronLayer layer: hiddenLayers) {
			for(Neuron neuron: layer) {
				Map<NeuronInput, Double> weights = neuron.weights;
				for(NeuronInput input: weights.keySet()) {
					if(Math.random() < parameters.getMutationProbability()) {
						double oldValue = weights.get(input);
						double newValue = oldValue * (0.2 * Math.random() - 0.1);
						weights.put(input, newValue);
					}
				}
			}
		}
		return this;
	}

	@Override
	public Chromosome crossover(Chromosome other) {
		NeuralNetwork otherNetwork = (NeuralNetwork)other;
		
		NeuralNetwork child = NeuralNetwork.duplicate(this);
		child.setParameters(parameters);
		
		for(int li=0; li<child.hiddenLayers.size(); li++) {
			NeuronLayer layer1 = child.hiddenLayers.get(li);
			NeuronLayer layer2 = otherNetwork.hiddenLayers.get(li);
			
			for(int ni=0; ni<layer1.size(); ni++) {
				Neuron neuron1 = layer1.get(ni);
				Neuron neuron2 = layer2.get(ni);
				
				for(int ii=0; ii<neuron1.weights.size(); ii++) {
					NeuronInput input1 = neuron1.inputs.get(ii);
					NeuronInput input2 = neuron2.inputs.get(ii);
					
					if(Math.random() < 0.5) {
						neuron1.weights.put(input1, neuron2.weights.get(input2));
					}
				}
			}
			
		}

		return child;
	}


	public OutputLayer getOutputLayer() {
		return outputLayer;
	}

	public void clearInputs() {
		if(!this.hiddenLayers.isEmpty()) {
			NeuronLayer firstLayer = this.hiddenLayers.getFirst();
			for(Neuron neuron: firstLayer) {
				neuron.inputs.clear();
			}
		}
		this.inputLayer.clear();
	}

	public void clearOutputs() {
		this.outputLayer.clear();
	}
}
