package org.mysoft.brainless.genetics.core;

import org.mysoft.brainless.genetics.chromosome.Chromosome;

public class ChromosomeFit<T extends Chromosome> implements Comparable<ChromosomeFit<T>>{

	T chromosome;
	Double fit;
	
	public ChromosomeFit(T chromosome, Double fit) {
		this.chromosome = chromosome;
		this.fit = fit;
	}
	
	public T getChromosome() {
		return chromosome;
	}
	
	public Double getFit() {
		return fit;
	}
	
	@Override
	public int compareTo(ChromosomeFit<T> o) {
		return fit.compareTo(o.fit);
	}
	
}
