package org.mysoft.brainless.genetics.core;

import org.mysoft.brainless.genetics.chromosome.Chromosome;

public interface IGeneticFitCalculator<T extends Chromosome> {

	public double calculate(T chromosome);
	
}
