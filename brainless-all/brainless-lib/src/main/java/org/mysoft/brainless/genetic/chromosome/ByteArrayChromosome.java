package org.mysoft.brainless.genetic.chromosome;

import org.mysoft.brainless.genetic.core.GeneticOperator;

public class ByteArrayChromosome extends Chromosome {

	public byte[] bytes;
	public int len;
	
	public ByteArrayChromosome(int size) {
		len = size;
		bytes = new byte[size];
		for(int i=0;i<size;i++)
			bytes[i] = (byte)(Math.random() * 256);
	}
	
	public ByteArrayChromosome(byte[] chromosome) {
		this.bytes = chromosome;
		len = chromosome.length;
	}
	
	@Override
	public Chromosome mutate() {
		for(int i = 0; i < len; i++) {
			try {
				if(Math.random() < parameters.getMutationProbability()) {
					int sign = (int)(Math.round(Math.random()) * 2 - 1);
					bytes[i] = (byte)(bytes[i] + sign);
				} else {
					bytes[i] = bytes[i];
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				bytes[i] = 0;
			}
		}
		return this;
	}

	@Override
	public ByteArrayChromosome crossover(Chromosome other) {
		ByteArrayChromosome child = new ByteArrayChromosome(GeneticOperator.crossover(bytes, ((ByteArrayChromosome)other).bytes));
		child.setParameters(parameters);
		return child;
	}
	
}
