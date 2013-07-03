package org.mysoft.brainless.sim;

import org.mysoft.brainless.neural.core.NeuralNetwork;

public abstract class Simulation<T extends NeuralNetwork> {
	
	public abstract void init(T neuralNetwork);
	
	public abstract void simulate();
	
}
