package org.mysoft.brainless.genetics.core;

import org.mysoft.brainless.genetics.chromosome.Chromosome;
@SuppressWarnings("rawtypes")
public class FitCalculatorWorker extends Thread {

	Double fit = Double.MIN_VALUE;
	GeneticParameters parameters;
	Chromosome chromosome;
	boolean isCalculated = false;
	
	boolean busy = false;
	
	public FitCalculatorWorker(GeneticParameters parameters) {
		this.parameters = parameters;
	}
	
	public synchronized void start(Chromosome chromosome) {
		this.fit = Double.MIN_VALUE;
		this.chromosome = chromosome;
		this.isCalculated = false;
		super.start();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		busy = true;
		for (int j = 0; j < parameters.getFitCalculationRepeats(); j++) {
			Double newFit = parameters.getFitCalculator().calculate(chromosome);
			boolean isWorst = newFit > fit;
			if (isWorst) {
				fit = newFit;
			}
		}
		busy = false;
		isCalculated = true;
	}
	
	public Double getCalculatedFit() {
		if(!isCalculated) {
			throw new IllegalStateException("Fit is not calculated yet");
		}
		
		return fit;
	}
	
	public synchronized boolean isBusy() {
		return busy;
	}
	
}
