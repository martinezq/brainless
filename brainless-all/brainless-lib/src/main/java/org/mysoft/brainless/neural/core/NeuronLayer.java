package org.mysoft.brainless.neural.core;

import java.util.LinkedList;

public class NeuronLayer extends LinkedList<Neuron> {

	private static final long serialVersionUID = 1L;

	public final static int DEFAULT_SIZE = 4;

	public static NeuronLayer create(int neuronsInLayer) {
		return new NeuronLayer(neuronsInLayer);
	}
	
	public NeuronLayer() {
		this(DEFAULT_SIZE);
	}
	
	public NeuronLayer(int size) {
		createNeurons(size);
	}

	public void connectTo(NeuronLayer anotherLayer) {
		for(Neuron neuron: this) {
			neuron.addInputs(anotherLayer);
		}
	}
	
	public void connectTo(InputLayer inputLayer) {
		for(Neuron neuron: this) {
			neuron.addInputs(inputLayer);
		}	
	}
	
	private void createNeurons(int size) {
		for(int i=0; i<size; i++) {
			add(new Neuron());
		}
	}

	public void copyWeightsFrom(NeuronLayer layerFrom) {
		int neuronsInLayer = this.size();
		
		for(int ni=0; ni<neuronsInLayer; ni++) {
			Neuron neuronTo = this.get(ni);
			Neuron neuronFrom = layerFrom.get(ni);
			
			neuronTo.copyWeightsFrom(neuronFrom);
		}
	}

	public boolean hasEqualTopology(NeuronLayer otherLayer) {
		int neuronsInLayer = this.size();
		
		for(int ni=0; ni<neuronsInLayer; ni++) {
			Neuron neuron = this.get(ni);
			Neuron otherNeuron = otherLayer.get(ni);
			
			boolean eq = neuron.hasEqualTopology(otherNeuron); 
			
			if(!eq) {
				return false;
			}
		}
		
		return true;
	}

	public boolean hasEqualWeights(NeuronLayer otherLayer) {
		if(!hasEqualTopology(otherLayer)) {
			throw new IllegalArgumentException("Compared network layer has different topology");
		}
		
		int neuronsInLayer = this.size();
		
		for(int ni=0; ni<neuronsInLayer; ni++) {
			Neuron neuron = this.get(ni);
			Neuron otherNeuron = otherLayer.get(ni);
			
			boolean eq = neuron.hasEqualWeights(otherNeuron); 
			
			if(!eq) {
				return false;
			}
		}
		
		return true;
	}

	public void randomizeWeights() {
		for(Neuron neuron: this) {
			neuron.randomizeWeights();
		}
	}

	public void randomizeWeightsUpTo(int count) {
		for(Neuron neuron: this) {
			neuron.randomizeWeightsUpTo(count);
		}
	}
	
}
