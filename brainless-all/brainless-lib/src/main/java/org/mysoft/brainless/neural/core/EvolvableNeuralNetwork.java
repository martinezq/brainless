package org.mysoft.brainless.neural.core;

import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;
import org.mysoft.brainless.genetics.core.Evolvable;

public abstract class EvolvableNeuralNetwork extends Evolvable<NeuralNetworkChromosome> {

	@Override
	public String toString() {
		return "Individual fit = " + getFit();
	}
	
}
