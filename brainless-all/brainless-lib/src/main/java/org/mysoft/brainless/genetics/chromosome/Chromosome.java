package org.mysoft.brainless.genetics.chromosome;

import org.mysoft.brainless.genetics.core.GeneticParameters;

public abstract class Chromosome {

	protected GeneticParameters parameters;
	
	
	public abstract Chromosome duplicate();
	
	public abstract Chromosome randomize();
	
	public void setParameters(GeneticParameters parameters) {
		this.parameters = parameters;
	}
	
	public GeneticParameters getParameters() {
		return parameters;
	}
}