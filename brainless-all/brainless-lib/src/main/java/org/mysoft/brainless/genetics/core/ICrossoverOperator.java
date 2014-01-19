package org.mysoft.brainless.genetics.core;

import org.mysoft.brainless.genetics.chromosome.Chromosome;

public interface ICrossoverOperator<T extends Chromosome> extends IGeneticOperator<T> {

	public T crossover(T chromosome1, T chromosome2);
	
}
