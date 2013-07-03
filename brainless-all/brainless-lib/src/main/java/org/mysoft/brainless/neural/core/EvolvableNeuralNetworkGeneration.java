package org.mysoft.brainless.neural.core;

import org.mysoft.brainless.genetics.core.Generation;
import org.mysoft.brainless.genetics.core.GeneticParameters;

public class EvolvableNeuralNetworkGeneration extends Generation<EvolvableNeuralNetwork> {

	@Override
	protected EvolvableNeuralNetwork instantiate(GeneticParameters parameters) {
		EvolvableNeuralNetwork enn = EvolvableNeuralNetwork.create();
		enn.setParameters(parameters);
		return enn;
	}

}
