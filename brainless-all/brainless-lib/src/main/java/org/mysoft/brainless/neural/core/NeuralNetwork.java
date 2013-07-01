package org.mysoft.brainless.neural.core;

import java.util.LinkedList;
import java.util.Map.Entry;


public class NeuralNetwork {

	protected OutputLayer outputLayer = new OutputLayer();
	protected InputLayer inputLayer = new InputLayer();
	protected LinkedList<NeuronLayer> hiddenLayers = new LinkedList<>();
	
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
	
}
