package org.mysoft.brainless.neural.core;

import java.util.LinkedList;

public class NeuralNetwork {
	
	private final static int MAX_WEIGHTS = 32;
	
	protected InputLayer inputLayer = InputLayer.create();
	protected LinkedList<NeuronLayer> hiddenLayers = new LinkedList<>();
	protected OutputLayer outputLayer = OutputLayer.create();
	protected StorageLayer storageLayer = StorageLayer.create();
	
	public static NeuralNetwork createEmpty() {
		return new NeuralNetwork();
	}
	
	public static NeuralNetwork createDefault() {
		return create(2,4,8,16);
	}
	
	public static NeuralNetwork create(int... counts) {
		NeuralNetwork n = createEmpty();
		
		n.createHiddenLayers(counts);
		n.createHiddenLayersConnections();
		
		return n;
	}
	
	public NeuralNetwork duplicate() {
		NeuralNetwork newNetwork = NeuralNetwork.createEmpty();
		int hiddenLayersCount = this.hiddenLayers.size();
		
		int[] sizes = new int[hiddenLayersCount];
		
		for(int i=0; i<hiddenLayersCount; i++) {
			sizes[i] = this.hiddenLayers.get(i).size();
		}
		
		newNetwork.createHiddenLayers(sizes);
		newNetwork.createHiddenLayersConnections();
		newNetwork.copyWeightsFrom(this);
		newNetwork.copyStorageNeurons(this);
		
		return newNetwork;
	}

	private void copyStorageNeurons(NeuralNetwork otherNetwork) {
		int count = otherNetwork.storageLayer.size();
		addStorageNeurons(count);
		
	}

	public void createHiddenLayers(int layers, int neuronsInLayer) {
		for(int li=0; li<layers; li++) {
			NeuronLayer layer = NeuronLayer.create(neuronsInLayer);
			hiddenLayers.add(layer);
		}
	}
	
	public void createHiddenLayers(int... counts) {
		for(int i=0; i<counts.length; i++) {
			NeuronLayer layer = NeuronLayer.create(counts[i]);
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
			prevLayer = layer;
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
			layer.randomizeWeightsUpTo(MAX_WEIGHTS);
		}
	}
	
	public void attachInputLayer(InputLayer inputLayer) {
		detachInputLayer();
		
		if(hiddenLayers.isEmpty()) {
			throw new IllegalStateException("No hidden layers");
		}
		
		NeuronLayer firstHiddenLayer = this.hiddenLayers.getFirst();
		
		firstHiddenLayer.connectTo(inputLayer);
		
		for(NeuronInput input: this.storageLayer) {
			firstHiddenLayer.connectTo(input);
		}

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
		
		int neurons = hiddenLayers.getLast().size() - this.storageLayer.size();
		int outputs = outputLayer.size();
		
		if(neurons != outputs) {
			throw new IllegalArgumentException("Expected " + neurons + " outputs in layer, got " + outputs);
		}
		
		for(NetworkOutput output: outputLayer) {
			addOutput(output);
		}
		
		for(StorageNeuron storage: this.storageLayer) {
			addOutput(storage);
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
		
		if(this.storageLayer.size() != otherNetwork.storageLayer.size()) {
			return false;
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
		int outputsCount = this.outputLayer.size();
		
		for(int i=0; i<outputsCount; i++) {
			Neuron neuron = lastHiddenLayer.get(i);
			NetworkOutput output = this.outputLayer.get(i);
			
			double value = neuron.calculatedOutput();
			output.perform(value);
		}
		/*
		int storagesCount = this.storageLayer.size();
		
		for(int j=0; j<storagesCount; j++) {
			Neuron neuron = lastHiddenLayer.get(outputsCount + j);
			NetworkOutput output = this.storageLayer.get(j);
			
			double value = neuron.calculatedOutput();
			output.perform(value);
		}*/
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
	
	public void addStorageNeuron() {
		StorageNeuron storageNeuron = StorageNeuron.create();
		this.storageLayer.add(storageNeuron);
	}
	
	protected boolean hasHiddenLayers() {
		return hiddenLayers.size() > 0;
	}
	
	protected void assertHasHiddenLayers() {
		if(!hasHiddenLayers()) {
			throw new IllegalStateException("There are no hidden layers");
		}
	}

	 void addStorageNeurons(int count) {
		for(int i=0; i<count; i++) {
			addStorageNeuron();
		}
	}
	 
	 public StorageLayer getStorageLayer() {
		return storageLayer;
	}
}
