package org.mysoft.brainless.genetics.core;

import org.mysoft.brainless.genetics.chromosome.Chromosome;

public class GeneticParameters<T extends Chromosome> {

	private IMutationOperator<T> mutationOperator;
	private ICrossoverOperator<T> crossoverOperator;

	private IGeneticFitCalculator<T> fitCalculator;

	private boolean bestImmortal = false;

	private double mutationProbability = 0.01;
	private int generationSize = 16;
	private int maxGenerations = 16;

	public IMutationOperator<T> getMutationOperator() {
		return mutationOperator;
	}

	public void setMutationOperator(IMutationOperator<T> mutationOperator) {
		this.mutationOperator = mutationOperator;
	}

	public ICrossoverOperator<T> getCrossoverOperator() {
		return crossoverOperator;
	}

	public void setCrossoverOperator(ICrossoverOperator<T> crossoverOperator) {
		this.crossoverOperator = crossoverOperator;
	}

	public IGeneticFitCalculator<T> getFitCalculator() {
		return fitCalculator;
	}

	public void setFitCalculator(IGeneticFitCalculator<T> fitCalculator) {
		this.fitCalculator = fitCalculator;
	}

	public boolean isBestImmortal() {
		return bestImmortal;
	}
	
	public void setBestImmortal(boolean bestImmortal) {
		this.bestImmortal = bestImmortal;
	}
	
	public double getMutationProbability() {
		return mutationProbability;
	}

	public void setMutationProbability(double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}

	public int getGenerationSize() {
		return generationSize;
	}

	public void setGenerationSize(int generationSize) {
		this.generationSize = generationSize;
	}

	public int getMaxGenerations() {
		return maxGenerations;
	}
	
	public void setMaxGenerations(int maxGenerations) {
		this.maxGenerations = maxGenerations;
	}
}
