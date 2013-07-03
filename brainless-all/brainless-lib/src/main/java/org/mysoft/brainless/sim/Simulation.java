package org.mysoft.brainless.sim;

import org.mysoft.brainless.genetics.chromosome.Chromosome;

public abstract class Simulation<T extends Chromosome> {
	
	public abstract void init(T chromosome);
	
	public abstract void simulate();
	
}
