package org.mysoft.brainless.genetics.core;

public class GeneticParameters {

	public double getMutationProbability() {
		return 0.005;
	}
	
	// min 4
	public int getGenerationSize() {
		return 256;
	}
	
	public int getMaxGenerations() {
		return 500;
	}
	
}
