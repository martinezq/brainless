package org.mysoft.brainless.genetics.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mysoft.brainless.genetics.chromosome.Chromosome;
import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;

public abstract class Generation<T extends Chromosome> {

	GeneticParameters<T> parameters;
	
	List<T> individuals = new ArrayList<T>();
	List<ChromosomeFit<T>> fits = new ArrayList<>();
	
	Double bestFit = Double.MAX_VALUE;
	T bestIndividual = null;
	
	boolean calculatedFits = false;
	
	protected abstract T instantiate(GeneticParameters<T> parameters);
	
	public void init() {
		for(int i=0;i<parameters.getGenerationSize();i++) {
			T chromosome = instantiate(parameters);
			individuals.add(chromosome);
		}
	}

	public void calculateNext() {
		
		ICrossoverOperator<T> crossover = parameters.getCrossoverOperator();
		IMutationOperator<T> mutation = parameters.getMutationOperator();
		
		if(crossover == null) {
			throw new IllegalArgumentException("No crossover operator");
		}
		
		if(mutation == null) {
			throw new IllegalArgumentException("No mutation operator");
		}
		
		calculateFits();
		sort();
		
		int len = individuals.size();
		int halflen = len / 2;
		
		T bestChromosome = individuals.get(0);
		
		double prob = parameters.getMutationProbability() / 2.0;
	
		for(int i=0;i<halflen;i+=2) {
				
			T chromosome1 = individuals.get(i);
			T chromosome2 = individuals.get(i+1);
			
			T child1 = crossover.crossover(chromosome1, chromosome2);
			T child2 = crossover.crossover(chromosome1, chromosome2);
			T child3 = crossover.crossover(chromosome1, chromosome2);
			T child4 = crossover.crossover(chromosome1, chromosome2);

			child1 = mutation.mutate(child1, prob);
			child2 = mutation.mutate(child2, prob);
			child3 = mutation.mutate(child3, prob);
			child4 = mutation.mutate(child4, prob);
			
			individuals.set(i, child1); 
			individuals.set(i+1, child2); 
			individuals.set(halflen+i, child3); 
			individuals.set(halflen+i+1, child4);

		}
		
		if(parameters.isBestImmortal()) {
			individuals.set(individuals.size() - 1, bestChromosome);
		}
						
		calculatedFits = false;
	}

	void calculateFits() {
		if(calculatedFits) {
			return;
		}
		
		fits.clear();
		
		for(int i=0; i<individuals.size(); i++) {
			T chromosome = individuals.get(i);
			Double fit = parameters.getFitCalculator().calculate(chromosome);
			fits.add(i, new ChromosomeFit<T>(chromosome, fit));
		}
		
		sort();
		
		calculatedFits = true;
		
		if(fits.get(0).fit < bestFit) {
			bestFit = fits.get(0).fit;
			bestIndividual = fits.get(0).chromosome;
		}
	}

	public T calculateBest() {
		calculateFits();
		return bestIndividual;
	}
	
	public double calculateBestFit() {
		calculateFits();
		return bestFit;
	}
	
	private void sort() {
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
		
	}

	public int getSize() {
		return individuals.size();
	};
	
}
