package org.mysoft.brainless.genetics.core;

public class GeneticParameters {

	public double getMutationProbability() {
		return 0.01;
	}
	
	// min 4
	public int getGenerationSize() {
		return 64;
	}
	
	public int getMaxGenerations() {
		return 20;
	}
	
}
