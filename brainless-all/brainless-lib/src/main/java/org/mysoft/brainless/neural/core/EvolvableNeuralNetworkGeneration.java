package org.mysoft.brainless.neural.core;

import org.mysoft.brainless.genetics.core.Generation;
import org.mysoft.brainless.genetics.core.GeneticParameters;

public class EvolvableNeuralNetworkGeneration extends Generation<EvolvableNeuralNetwork> {

	public static EvolvableNeuralNetworkGeneration[] createArrayOf(int count) {
		EvolvableNeuralNetworkGeneration[] result = new EvolvableNeuralNetworkGeneration[count];

		for(int i=0; i<count; i++) {
			result[i] = EvolvableNeuralNetworkGeneration.create();
		}
		
		return result;
	}
	
	private static EvolvableNeuralNetworkGeneration create() {
		return new EvolvableNeuralNetworkGeneration();
	}

	@Override
	protected EvolvableNeuralNetwork instantiate(GeneticParameters parameters) {
		EvolvableNeuralNetwork enn = EvolvableNeuralNetwork.create();
		enn.setParameters(parameters);
		return enn;
	}



}
