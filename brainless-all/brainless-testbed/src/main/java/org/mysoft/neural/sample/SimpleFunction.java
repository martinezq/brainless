package org.mysoft.neural.sample;

import org.mysoft.brainless.genetics.chromosome.DoubleArrayChromosome;
import org.mysoft.brainless.genetics.core.Evolvable;

public class SimpleFunction extends Evolvable<DoubleArrayChromosome> {
	
	public SimpleFunction() {
		chromosome = new DoubleArrayChromosome(100);
	}
	
	@Override
	public double calculateFit() {
		double sum = 0;
		for(double c: chromosome.values)
			sum += c;
		return Math.abs(sum);
	}
	
	@Override
	public String toString() {
		String s = "";
		for(double c: chromosome.values)
			s += c + ", ";
		return s;
	}

}
