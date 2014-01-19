package org.mysoft.brainless.genetics.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.mysoft.brainless.genetics.chromosome.Chromosome;

public abstract class Generation<T extends Chromosome> {

	GeneticParameters<T> parameters;
	
	private List<T> individuals = new ArrayList<T>();
	private List<ChromosomeFit<T>> fits = new ArrayList<>();
	
	private boolean calculatedFits = false;
	
	protected abstract T instantiate(GeneticParameters<T> parameters);
	
	public void init() {
		for(int i=0;i<individuals.size();i++) {
			T chromosome = instantiate(parameters);
			individuals.set(i, chromosome);
		}
	}

	public void calculateNext() {
		calculateFits();
		sort();
		
		int len = individuals.size();
		int halflen = len / 2;
		
		T bestChromosome = individuals.get(0);
		
		double prob = parameters.getMutationProbability() / 2.0;
	
		for(int i=0;i<halflen;i+=2) {
				
			T chromosome1 = individuals.get(i);
			T chromosome2 = individuals.get(i+1);
			
			T child1 = parameters.getCrossoverOperator().crossover(chromosome1, chromosome2);
			T child2 = parameters.getCrossoverOperator().crossover(chromosome1, chromosome2);
			T child3 = parameters.getCrossoverOperator().crossover(chromosome1, chromosome2);
			T child4 = parameters.getCrossoverOperator().crossover(chromosome1, chromosome2);

			child1 = parameters.getMutationOperator().mutate(child1, prob);
			child2 = parameters.getMutationOperator().mutate(child2, prob);
			child3 = parameters.getMutationOperator().mutate(child3, prob);
			child4 = parameters.getMutationOperator().mutate(child4, prob);
			
			individuals.set(i, child1); 
			individuals.set(i+1, child2); 
			individuals.set(halflen+i, child3); 
			individuals.set(halflen+i+1, child4);

		}
		
		if(parameters.isKeepBest()) {
			individuals.set(individuals.size() - 1, bestChromosome);
		}
						
		calculatedFits = false;
	}

	private void calculateFits() {
		if(calculatedFits) {
			return;
		}
		
		for(int i=0; i<individuals.size(); i++) {
			T chromosome = individuals.get(i);
			Double fit = parameters.getFitCalculator().calculate(chromosome);
			fits.add(i, new ChromosomeFit<T>(chromosome, fit));
		}
		
		sort();
		
		calculatedFits = true;
	}

	public T calculateBest() {
		calculateFits();
		return fits.get(0).getChromosome();
	}
	
	public double calculateBestFit() {
		calculateFits();
		return fits.get(0).getFit();
	}
	
	public void sort() {
		if(!calculatedFits) {
			throw new IllegalStateException("Calculate fits first");
		}
		
		individuals.clear();
		
		for(ChromosomeFit<T> fit: fits) {
			individuals.add(fit.getChromosome());
		}
		
		Collections.sort(fits);
	}

	public void setParameters(GeneticParameters<T> parameters) {
		this.parameters = parameters;
	}
	
	@Override
	public String toString() {
		String s = "";
		for(T i: individuals) {
			s += "[" + i.toString() + "], ";
		}
		return s;
		
	};
	
}
