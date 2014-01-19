package org.mysoft.brainless.genetics.core;

import org.mysoft.brainless.genetics.chromosome.Chromosome;


public class GeneticEngineTask<T extends Chromosome> implements Runnable {

	Generation<T> generation;
	
	GeneticParameters<T> parameters;
	
	public void setParameters(GeneticParameters<T> params) {
		this.parameters = params;
		this.generation.setParameters(parameters);
	}
	
	public GeneticEngineTask(Generation<T> generation) {
		this.generation = generation;
	}
	
	@Override
	public void run() {
		
		T best;
		Double bestFit;
		
		try {
			int count = 0;
			generation.init();
			do {
				count++;
				generation.calculateNext();
				best = generation.calculateBest();
				bestFit = generation.calculateBestFit();
				
				System.out.println("generation " + count + ": " + best);
				Thread.sleep(10);
			} while (bestFit > 0 && count < parameters.getMaxGenerations());
			System.out.println("Computed in " + count + " generations");
		} catch (Exception e) {
			e.printStackTrace();
		} 		
		
	}

}
