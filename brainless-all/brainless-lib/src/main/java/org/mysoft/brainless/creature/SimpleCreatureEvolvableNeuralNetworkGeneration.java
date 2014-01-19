package org.mysoft.brainless.creature;

import org.mysoft.brainless.genetics.core.GeneticParameters;

@Deprecated
public class SimpleCreatureEvolvableNeuralNetworkGeneration  {

	public static SimpleCreatureEvolvableNeuralNetworkGeneration[] createArrayOf(int count) {
		SimpleCreatureEvolvableNeuralNetworkGeneration[] result = new SimpleCreatureEvolvableNeuralNetworkGeneration[count];

		for(int i=0; i<count; i++) {
			result[i] = SimpleCreatureEvolvableNeuralNetworkGeneration.create();
		}
		
		return result;
	}
	
	private static SimpleCreatureEvolvableNeuralNetworkGeneration create() {
		return new SimpleCreatureEvolvableNeuralNetworkGeneration();
	}

	//@Override
	protected SimpleCreatureEvolvableNeuralNetwork instantiate(GeneticParameters parameters) {
		SimpleCreatureEvolvableNeuralNetwork enn = SimpleCreatureEvolvableNeuralNetwork.create();
		//enn.setParameters(parameters);
		return enn;
	}



}
