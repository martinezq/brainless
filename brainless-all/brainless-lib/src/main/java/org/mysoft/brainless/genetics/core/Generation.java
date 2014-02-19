package org.mysoft.brainless.genetics.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.mysoft.brainless.genetics.chromosome.Chromosome;
import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;

public abstract class Generation<T extends Chromosome> {

	GeneticParameters<T> parameters;

	List<T> individuals = new ArrayList<T>();
	List<ChromosomeFit<T>> fits = new ArrayList<>();

	Double averageFit = Double.MAX_VALUE;
	Double bestFit = Double.MAX_VALUE;
	T bestIndividual = null;

	boolean calculatedFits = false;

	public abstract T instantiate(GeneticParameters<T> parameters);

	public void init() {
		for (int i = 0; i < parameters.getGenerationSize(); i++) {
			T chromosome = instantiate(parameters);
			individuals.add(chromosome);
		}
	}

	public void calculateNext() {

		ICrossoverOperator<T> crossover = parameters.getCrossoverOperator();
		IMutationOperator<T> mutation = parameters.getMutationOperator();

		if (crossover == null) {
			throw new IllegalArgumentException("No crossover operator");
		}

		if (mutation == null) {
			throw new IllegalArgumentException("No mutation operator");
		}

		calculateFits();
		sort();

		int len = individuals.size();
		int parentSelectionIndexLimit = len / 4;
		int crossoverLimitIndex = len;

		T bestChromosome = individuals.get(0);

		Random randomizer = new Random(System.currentTimeMillis());

		List<T> newIndividuals = new ArrayList<T>(len);

		if (parameters.isCrossoverDisabled()) {
			for (T parent : individuals) {
				newIndividuals.add((T) parent.duplicate());
			}
		} else {
			for (int i = 0; i < crossoverLimitIndex; i++) {

				int parent1Index = randomizer.nextInt(parentSelectionIndexLimit);
				int parent2Index = randomizer.nextInt(parentSelectionIndexLimit);
				
				if(parentSelectionIndexLimit > 1) {
					while(parent1Index == parent2Index) {
						parent2Index = randomizer.nextInt(parentSelectionIndexLimit);
					}
				}

				T parent1 = individuals.get(parent1Index);
				T parent2 = individuals.get(parent2Index);

				T child = crossover.crossover(parent1, parent2);

				newIndividuals.add(child);

			}
		}

		individuals.clear();

		double prob = parameters.getMutationProbability() / 2.0;

		for (T child : newIndividuals) {
			T mutatedChild = mutation.mutate(child, prob);
			individuals.add(mutatedChild);

		}
		
		for(int i=crossoverLimitIndex; i < len; i++) {
			T randomIndividual = (T) bestIndividual.duplicate();
			randomIndividual.randomize();
			individuals.add(randomIndividual);
		}

		if (parameters.isBestImmortal()) {
			individuals.set(individuals.size() - 1, bestChromosome);
		}

		calculatedFits = false;
	}

	void calculateFits() {
		if (calculatedFits) {
			return;
		}

		fits.clear();
		averageFit = 0.0;
		
		Map<T, FitCalculatorWorker> workers = new HashMap<T, FitCalculatorWorker>();
		List<FitCalculatorWorker> workersToJoin = new LinkedList<FitCalculatorWorker>();

		for (int i = 0; i < individuals.size(); i++) {
			T chromosome = individuals.get(i);
			
			FitCalculatorWorker worker = new FitCalculatorWorker(parameters);
			
			workers.put(chromosome, worker);
			workersToJoin.add(worker);
			worker.start(chromosome);
			
			if(i % 4 == 0 || i == individuals.size() - 1) {
				for(FitCalculatorWorker workerToJoin: workersToJoin) {
					try {
						workerToJoin.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				workersToJoin.clear();
				
			}
		}
		
		for (int i = 0; i < individuals.size(); i++) {
			T chromosome = individuals.get(i);
			
			Double fit = workers.get(chromosome).getCalculatedFit();

			fits.add(i, new ChromosomeFit<T>(chromosome, fit));
			averageFit += fit;
		}

		averageFit /= getSize();

		sort();

		calculatedFits = true;

		if (fits.get(0).fit < bestFit) {
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

		for (ChromosomeFit<T> fit : fits) {
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
		for (T i : individuals) {
			s += "[" + i.toString() + "], ";
		}
		return s;

	}

	public int getSize() {
		return individuals.size();
	};

	public Double getAverageFit() {
		return averageFit;
	}

}
