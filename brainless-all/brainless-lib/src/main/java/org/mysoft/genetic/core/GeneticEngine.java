package org.mysoft.genetic.core;


public class GeneticEngine<T extends Generation<?>, E extends Evolvable<?>> {

	Thread[] threads;
	T[] generations;
	
	GeneticParameters parameters;
	
	public GeneticEngine(GeneticParameters params) {
		this.parameters = params;
	}
	
	public void start(T... generations) {
		this.generations = generations; 
		threads = new Thread[generations.length];
		
		for(int i=0; i<threads.length; i++) {
			GeneticEngineTask<Generation<?>> task = new GeneticEngineTask<Generation<?>>(generations[i]);
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
	
	public E getBest() {
		E best = null;
		double fit = Double.MAX_VALUE;
		for(T generation: generations) {
			Evolvable generationBest = generation.calculateBest();
			double newFit = generationBest.getFit();
			if(newFit < fit) {
				best = (E)generationBest;
			}
		}
		return best;
	}
	
	
}
