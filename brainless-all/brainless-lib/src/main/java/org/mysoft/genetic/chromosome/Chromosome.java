package org.mysoft.genetic.chromosome;

import org.mysoft.genetic.core.GeneticParameters;

public abstract class Chromosome {

	GeneticParameters parameters;
	
	/**
	 * Mutation
	 * @return Should return itself
	 */
	public abstract Chromosome mutate();
	
	public abstract Chromosome crossover(Chromosome other);
	
	public void setParameters(GeneticParameters parameters) {
		this.parameters = parameters;
	}
}
