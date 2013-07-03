package org.mysoft.brainless.genetics.core;

import java.util.Arrays;
import java.util.Comparator;

import org.mysoft.brainless.genetics.chromosome.Chromosome;

@SuppressWarnings("rawtypes")
public abstract class Generation<E extends Evolvable> {

	GeneticParameters parameters;
	
	private Evolvable[] individuals;
	
	protected abstract E instantiate(GeneticParameters parameters);
	
	public void init() {
		this.individuals = new Evolvable[parameters.getGenerationSize()];
		for(int i=0;i<individuals.length;i++)
			individuals[i] = instantiate(parameters);
	}
	

	@SuppressWarnings("unchecked")
	public void calculateNext() {
		sort();
		
		int len = individuals.length;
		int halflen = len / 2;
		
		for(int i=0;i<halflen;i+=2) {
			Chromosome chromosome1 = individuals[i].getChromosome();
			Chromosome chromosome2 = individuals[i+1].getChromosome();
			
			individuals[i].setChromosome(chromosome1.crossover(chromosome2).mutate()); 
			individuals[i+1].setChromosome(chromosome1.crossover(chromosome2).mutate());
			individuals[halflen+i].setChromosome(chromosome1.crossover(chromosome2).mutate()); 
			individuals[halflen+i+1].setChromosome(chromosome1.crossover(chromosome2).mutate());

		}
		
	}

	@SuppressWarnings("unchecked")
	public E calculateBest() {
		sort();
		return (E)individuals[0];
	}
	
	public void sort() {
		
		Arrays.sort(individuals, new Comparator<Evolvable>() {

			@Override
			public int compare(Evolvable o1, Evolvable o2) {
				return Double.compare(o1.getFit(), o2.getFit());
			}
		});
	}

	public void setParameters(GeneticParameters parameters) {
		this.parameters = parameters;
	}
	
	@Override
	public String toString() {
		String s = "";
		for(Evolvable i: individuals) {
			s += "[" + i.toString() + "], ";
		}
		return s;
		
	};
	
}
