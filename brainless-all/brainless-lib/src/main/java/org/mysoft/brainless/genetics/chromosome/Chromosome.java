package org.mysoft.brainless.genetics.chromosome;

import org.mysoft.brainless.genetics.core.GeneticParameters;

public abstract class Chromosome {

	protected GeneticParameters parameters;
	
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
