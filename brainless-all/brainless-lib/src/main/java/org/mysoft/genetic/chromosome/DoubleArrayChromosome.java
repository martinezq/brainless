package org.mysoft.genetic.chromosome;

public class DoubleArrayChromosome extends Chromosome {

	public int size;
	public double[] values;

	public DoubleArrayChromosome(int size) {
		this.size = size;
		values = new double[size];
		for(int i=0;i<size;i++)
			values[i] = (Math.random() * 2 - 1) * 999999;
	}
	
	public DoubleArrayChromosome(double[] values) {
		this.values = values;
		this.size = values.length;
	}
	
	@Override
	public Chromosome mutate() {
		for(int i=0; i<size; i++) {
			if(Math.random() < parameters.getMutationProbability()) {
				values[i] += (Math.random() * 2 - 1) * 999;
			}
		}
		return this;
	}

	@Override
	public Chromosome crossover(Chromosome other) {
		DoubleArrayChromosome out = new DoubleArrayChromosome(size);
		for(int i=0; i<size; i++) {
			if(Math.random() < 0.5) {
				out.values[i] = values[i];
			} else {
				out.values[i] = ((DoubleArrayChromosome)other).values[i];
			}
		}
		out.setParameters(parameters);
		return out;
	}
}
