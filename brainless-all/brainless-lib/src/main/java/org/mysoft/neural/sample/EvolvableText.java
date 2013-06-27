package org.mysoft.neural.sample;

import org.mysoft.brainless.genetic.chromosome.ByteArrayChromosome;
import org.mysoft.brainless.genetic.core.Evolvable;

public class EvolvableText extends Evolvable<ByteArrayChromosome> {

	private byte[] target;

	public EvolvableText(String target) {
		this.target = target.getBytes();
		
		chromosome = new ByteArrayChromosome(this.target.length);
		fit = calculateFit();
	}

	@Override
	public String toString() {
		return new String(chromosome.bytes);
	}

	@Override
	public double calculateFit() {
		int len = target.length;
		
		int f = 0;
		for(int i=0; i<len; i++) {
			f += Math.abs(chromosome.bytes[i] - target[i]);
		}
		return 1.0 * f;
	}
	

}