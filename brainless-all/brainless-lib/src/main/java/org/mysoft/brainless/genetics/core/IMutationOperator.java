package org.mysoft.brainless.genetics.core;

import org.mysoft.brainless.genetics.chromosome.Chromosome;

public interface IMutationOperator<T extends Chromosome> extends IGeneticOperator<T> {

	public T mutate(T chromosome);
	
}
