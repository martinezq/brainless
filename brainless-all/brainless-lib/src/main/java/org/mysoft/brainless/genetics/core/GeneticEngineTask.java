package org.mysoft.brainless.genetics.core;

import java.awt.SystemColor;

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
		Double bestFit = Double.MAX_VALUE;
		int generationsWithNoProgress = 0;
		
		try {
			int count = 0;
			generation.init();
			do {
				count++;
				long startTime = System.currentTimeMillis();
				generation.calculateNext();
				best = generation.calculateBest();
				Double fit = generation.calculateBestFit();
				long endTime = System.currentTimeMillis();
				System.out.println("generation " + count + ", best fit = " + bestFit + ", size = " + generation.getSize() + ", avg fit = " + generation.getAverageFit() + ", time = " + (endTime - startTime));
				
				if(bestFit <= fit) {
					generationsWithNoProgress++;
				} else {
					generationsWithNoProgress = 0;
				}
				
				bestFit = fit;
				
			} while (bestFit > 0 && count < parameters.getMaxGenerations() && generationsWithNoProgress < parameters.getMaxGenerationsWithNoProgress());
			System.out.println("Computed in " + count + " generations");
		} catch (Exception e) {
			e.printStackTrace();
		} 		
		
	}

}
