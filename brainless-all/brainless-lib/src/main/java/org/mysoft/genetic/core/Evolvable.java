package org.mysoft.genetic.core;

import org.mysoft.genetic.chromosome.Chromosome;

public abstract class Evolvable<T extends Chromosome> {

	protected T chromosome;
	protected double fit;
	
	public abstract double calculateFit();
	
	public T getChromosome() {
		return chromosome;
	}
	
	public void setChromosome(T chromosome) {
		this.chromosome = chromosome;
		this.fit = calculateFit();
	}
	
	public double getFit() {
		return fit;
	}

	public void setParameters(GeneticParameters params) {
		chromosome.setParameters(params);
	}
	
}
