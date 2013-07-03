package org.mysoft.brainless.neural.core;

import java.util.LinkedList;

public class NeuronLayer extends LinkedList<Neuron> {

	private static final long serialVersionUID = 1L;

	public final static int DEFAULT_SIZE = 4;

	public NeuronLayer() {
		this(DEFAULT_SIZE);
	}
	
	public NeuronLayer(int size) {
		createNeurons(size);
	}
	
	public NeuronLayer duplicate() {
		NeuronLayer newLayer = new NeuronLayer(this.size());
		for(Neuron neuron: this) {
			Neuron newNeuron = neuron.duplicate();
			newLayer.add(newNeuron);
		}
		return newLayer;
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

	
}
