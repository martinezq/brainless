package org.mysoft.brainless.neural.core;

public class StorageNeuron implements NeuronInput, NetworkOutput {

	double storedValue = 0.0;
	
	@Override
	public void perform(double value) {
		storeValue(value);
	}

	@Override
	public double calculatedOutput() {
		return readValue();
	}

	public void reset() {
		storeValue(0.0);
	}
	
	private void storeValue(double value) {
		this.storedValue = value;
		//System.out.println("stored " + value);
	}
	
	private double readValue() {
		return storedValue;
	}

	public static StorageNeuron create() {
		return new StorageNeuron();
	}

}
