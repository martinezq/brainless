package org.mysoft.neural.sample;

import org.mysoft.brainless.genetics.core.GeneticEngine;
import org.mysoft.brainless.genetics.core.GeneticParameters;

public class Main {

	public static void main(String[] args) {
		
		GeneticEngine<EvolvableTextGeneration, EvolvableText> engine = new GeneticEngine<>(new GeneticParameters());
		
		engine.start(new EvolvableTextGeneration(), new EvolvableTextGeneration());
		
		EvolvableText best = engine.getBest();
		
		System.out.println("Best: " + best);
	}

	public static void main1(String[] args) {
		
		GeneticEngine<SimpleFunctionGeneration, SimpleFunction> engine = new GeneticEngine<>(new GeneticParameters());
		
		engine.start(new SimpleFunctionGeneration(), new SimpleFunctionGeneration());
		
		SimpleFunction best = engine.getBest();
		
		System.out.println("Best: " + best + " (" + best.getFit() + ")");
	}

}
