package org.mysoft.brainless.genetics.core;

import org.mysoft.brainless.genetics.chromosome.Chromosome;


public class GeneticEngine<T extends Chromosome> {

	Thread[] threads;
	Generation<T>[] generations;
	
	GeneticParameters<T> parameters;
	
	public GeneticEngine(GeneticParameters<T> params) {
		this.parameters = params;
	}
	
	@SuppressWarnings("unchecked")
	public void start(Generation<T>... generations) {
		this.generations = generations; 
		threads = new Thread[generations.length];
		
		for(int i=0; i<threads.length; i++) {
			GeneticEngineTask<T> task = new GeneticEngineTask<T>(generations[i]);
			task.setParameters(parameters);
			
			threads[i] = new Thread(task);
		}		
		
		for(Thread t: threads) {
			t.start();
		}

		for(Thread t: threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
	public T getBest() {
		T best = null;
		double fit = Double.MAX_VALUE;
		
		for(Generation<T> generation: generations) {
			
			T generationBest = generation.calculateBest();
			double newFit = generation.calculateBestFit();
			
			if(newFit < fit) {
				best = generationBest;
				fit = newFit;
			}
		}

		if(best == null) {
			throw new IllegalStateException("Cannot find best individual, because every one is worst possible");
		}
		
		return best;
	}
	
	
}
