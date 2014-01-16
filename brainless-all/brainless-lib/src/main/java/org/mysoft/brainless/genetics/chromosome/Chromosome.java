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
	
	public abstract Chromosome duplicate();
	
	public abstract Chromosome randomize();
	
	public void setParameters(GeneticParameters parameters) {
		this.parameters = parameters;
	}
}
