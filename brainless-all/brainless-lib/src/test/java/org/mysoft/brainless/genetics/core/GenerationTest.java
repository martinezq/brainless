package org.mysoft.brainless.genetics.core;

import org.junit.Assert;
import org.junit.Test;
import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;
import org.mysoft.brainless.genetics.neural.DefaultNeuralNetworkCrossoverOperator;
import org.mysoft.brainless.genetics.neural.DefaultNeuralNetworkMutationOperator;
import org.mysoft.brainless.genetics.neural.RandomNeuralNetworkFitCalculator;
import org.mysoft.brainless.neural.core.NeuralNetwork;

public class GenerationTest {
	
	@Test
	public void test_sort() {
		Generation<NeuralNetworkChromosome> gen = prepare();
		
		gen.calculateFits();
		
		double last = Double.MIN_VALUE;
		
		for(ChromosomeFit<NeuralNetworkChromosome> chfit: gen.fits) {
			double fit = chfit.fit;
			Assert.assertTrue("Fits are not sorted ascending", fit >= last);
			last = fit;
		}
	}
	
	@Test
	public void test_best() {
		Generation<NeuralNetworkChromosome> gen = prepare();
		
		gen.calculateFits();
		
		NeuralNetworkChromosome best = gen.bestIndividual;
		gen.bestFit = 0.0;
		
		gen.calculateNext();
		
		NeuralNetworkChromosome newBest = gen.bestIndividual;
		
		Assert.assertTrue("Best individual should be kept", best == newBest);
		
		gen.bestFit = Double.MAX_VALUE;
		
		gen.calculateNext();
		
		NeuralNetworkChromosome newBest2 = gen.bestIndividual;
		
		Assert.assertTrue("Not best individual should not be kept", best != newBest2);
	}
	
	private Generation<NeuralNetworkChromosome> prepare() {
		Generation<NeuralNetworkChromosome> gen = new Generation<NeuralNetworkChromosome>() {
			@Override
			public NeuralNetworkChromosome instantiate(GeneticParameters<NeuralNetworkChromosome> parameters) {
				return NeuralNetworkChromosome.create(NeuralNetwork.createDefault());
			}
		};
		
		GeneticParameters<NeuralNetworkChromosome> par = new GeneticParameters<NeuralNetworkChromosome>();
		par.setFitCalculator(new RandomNeuralNetworkFitCalculator());
		par.setCrossoverOperator(new DefaultNeuralNetworkCrossoverOperator());
		par.setMutationOperator(new DefaultNeuralNetworkMutationOperator());
		
		gen.setParameters(par);
		
		gen.init();
		
		return gen;
	}
	
}
