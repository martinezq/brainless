package org.mysoft.brainless.neural.core;

import java.util.LinkedList;

public class NeuralNetwork {

	public final static int DEFAULT_HIDDEN_LAYERS = 1;
	public final static int DEFAULT_NEURONS_IN_HIDDEN_LAYER = 18;
	
	protected InputLayer inputLayer = InputLayer.create();
	protected LinkedList<NeuronLayer> hiddenLayers = new LinkedList<>();
	protected OutputLayer outputLayer = OutputLayer.create();
	
	public static NeuralNetwork createEmpty() {
		return new NeuralNetwork();
	}
	
	public static NeuralNetwork createDefault() {
		NeuralNetwork n = createEmpty();
		
		n.createHiddenLayers(DEFAULT_HIDDEN_LAYERS, DEFAULT_NEURONS_IN_HIDDEN_LAYER);
		n.createHiddenLayersConnections();
		
		return n;
	}
	
	public NeuralNetwork duplicate() {
		NeuralNetwork newNetwork = NeuralNetwork.createEmpty();
		int hiddenLayersCount = this.hiddenLayers.size();
		int neuronsInLayer = this.hiddenLayers.getFirst().size();
		
		newNetwork.createHiddenLayers(hiddenLayersCount, neuronsInLayer);
		newNetwork.createHiddenLayersConnections();
		newNetwork.copyWeightsFrom(this);
		
		return newNetwork;
	}
	

	public void createHiddenLayers(int layers, int neuronsInLayer) {
		for(int li=0; li<layers; li++) {
			NeuronLayer layer = NeuronLayer.create(neuronsInLayer);
			hiddenLayers.add(layer);
		}
	}
	
	public void createHiddenLayersConnections() {
		if(hiddenLayers.size() < 2) {
			return;
		}
		
		NeuronLayer prevLayer = hiddenLayers.getFirst();
		
		for(int i=1; i<hiddenLayers.size(); i++) {
			NeuronLayer layer = hiddenLayers.get(i);
			layer.connectTo(prevLayer);
		}
	}
	
	public void copyWeightsFrom(NeuralNetwork neuralNetwork) {
		
		int hiddenLayersCount = this.hiddenLayers.size();
				
		for(int li=0; li<hiddenLayersCount; li++) {
			NeuronLayer layerTo = hiddenLayers.get(li);
			NeuronLayer layerFrom = neuralNetwork.hiddenLayers.get(li);
			
			layerTo.copyWeightsFrom(layerFrom);

		}
	}
	
	public void randomizeWeights() {
		for(NeuronLayer layer: hiddenLayers) {
			layer.randomizeWeights();
		}
	}
	
	public void attachInputLayer(InputLayer inputLayer) {
		detachInputLayer();
		
		if(hiddenLayers.isEmpty()) {
			throw new IllegalStateException("No hidden layers");
		}
		
		NeuronLayer firstHiddenLayer = this.hiddenLayers.getFirst();
		
		firstHiddenLayer.connectTo(inputLayer);

	}

	public void detachInputLayer() {
		if(!hiddenLayers.isEmpty()) {
			for(Neuron neuron: hiddenLayers.getFirst()) {
				neuron.cleanInputsKeepWeights();
			}
		}
	}
	
	public void attachOutputLayer(OutputLayer outputLayer) {
		detachOutputLayer();
		
		assertHasHiddenLayers();
		
		int neurons = hiddenLayers.getLast().size();
		int outputs = outputLayer.size();
		
		if(neurons != outputs) {
			throw new IllegalArgumentException("Expected " + neurons + " outputs in layer, got " + outputs);
		}
		
		for(NetworkOutput output: outputLayer) {
			addOutput(output);
		}
	}
	
	private void addOutput(NetworkOutput output) {
		this.outputLayer.add(output);
	}

	public void detachOutputLayer() {
		outputLayer.clear();
	}
	
	public boolean hasEqualTopology(NeuralNetwork otherNetwork) {
		int hiddenLayersCount = this.hiddenLayers.size();
		
		for(int li=0; li<hiddenLayersCount; li++) {
			NeuronLayer layer = hiddenLayers.get(li);
			NeuronLayer otherLayer = otherNetwork.hiddenLayers.get(li);
			
			boolean eq = layer.hasEqualTopology(otherLayer);
			
			if(!eq) {
				return false;
			}

		}
		return true;
	}
	
	public boolean hasEqualWeights(NeuralNetwork otherNetwork) {
		if(!hasEqualTopology(otherNetwork)) {
			throw new IllegalArgumentException("Compared network has different topology");
		}
		
		int hiddenLayersCount = this.hiddenLayers.size();
		
		for(int li=0; li<hiddenLayersCount; li++) {
			NeuronLayer layer = hiddenLayers.get(li);
			NeuronLayer otherLayer = otherNetwork.hiddenLayers.get(li);
			
			boolean eq = layer.hasEqualWeights(otherLayer);
			
			if(!eq) {
				return false;
			}

		}
		return true;
	}
	
	protected void reset() {
		for(NeuronLayer layer: hiddenLayers) {
			for(Neuron neuron: layer) {
				neuron.reset();
			}
		}
	}
	
	public void step() {
		assertHasHiddenLayers();
		
		reset();
		
		NeuronLayer lastHiddenLayer = hiddenLayers.getLast();
		int count = this.outputLayer.size();
		
		for(int i=0; i<count; i++) {
			Neuron neuron = lastHiddenLayer.get(i);
			NetworkOutput output = this.outputLayer.get(i);
			
			double value = neuron.calculatedOutput();
			output.perform(value);
		}
	}
	
	public OutputLayer getOutputLayer() {
		return outputLayer;
	}
	
	public InputLayer getInputLayer() {
		return inputLayer;
	}
	
	public LinkedList<NeuronLayer> getHiddenLayers() {
		return hiddenLayers;
	}
	
	protected boolean hasHiddenLayers() {
		return hiddenLayers.size() > 0;
	}
	
	protected void assertHasHiddenLayers() {
		if(!hasHiddenLayers()) {
			throw new IllegalStateException("Network has not hiddel layers");
		}
	}
/////////////////////////
/*	
	
	
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
	
	*/
}
