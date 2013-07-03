package org.mysoft.brainless.genetics.core;

public class GeneticParameters {

	public double getMutationProbability() {
		return 0.05;
	}
	
	// min 4
	public int getGenerationSize() {
		return 16;
	}
	
	public int getMaxGenerations() {
		return 10;
	}
	
}
