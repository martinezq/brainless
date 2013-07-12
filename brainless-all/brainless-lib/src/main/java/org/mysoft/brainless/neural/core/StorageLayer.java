package org.mysoft.brainless.neural.core;

import java.util.ArrayList;

public class StorageLayer extends ArrayList<StorageNeuron> {

	private static final long serialVersionUID = 1L;

	public static StorageLayer create() {
		return new StorageLayer();
	}

	public void reset() {
		for(StorageNeuron neuron: this) {
			neuron.reset();
		}
	}
	
}
