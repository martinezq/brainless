package org.mysoft.brainless.neural.core;

import java.util.LinkedList;

public class NeuralNetwork {

	public final static int DEFAULT_HIDDEN_LAYERS = 2;
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
			layer.randomizeWeightsUpTo(DEFAULT_NEURONS_IN_HIDDEN_LAYER);
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
	
	public void attachOutputLayer(OutputLayer newOutputLayer) {
		detachOutputLayer();
		
		assertHasHiddenLayers();
		
		int neurons = hiddenLayers.getLast().size();
		int outputs = newOutputLayer.size();
		
		if(neurons != outputs) {
			throw new IllegalArgumentException("Expected " + neurons + " outputs in layer, got " + outputs);
		}
		
		for(NetworkOutput output: newOutputLayer) {
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
}
