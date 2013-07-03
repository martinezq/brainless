package org.mysoft.brainless.genetics.core;


public class GeneticEngineTask<T extends Generation<?>> implements Runnable {

	T generation;
	Evolvable<?> best;
	
	GeneticParameters parameters;
	
	public void setParameters(GeneticParameters params) {
		this.parameters = params;
		this.generation.setParameters(parameters);
	}
	
	public GeneticEngineTask(T generation) {
		this.generation = generation;
	}
	
	@Override
	public void run() {
		try {
			int count = 0;
			generation.init();
			do {
				count++;
				generation.calculateNext();
				best = generation.calculateBest();
				
				System.out.println(best);
			} while (best.getFit() > 0 && count < parameters.getMaxGenerations());
			System.out.println("Computed in " + count + " generations");
		} catch (Exception e) {
			e.printStackTrace();
		} 		
		
	}

}
