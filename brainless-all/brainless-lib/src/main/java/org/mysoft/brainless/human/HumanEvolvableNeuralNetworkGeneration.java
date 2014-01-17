package org.mysoft.brainless.human;

import org.mysoft.brainless.genetics.core.Generation;
import org.mysoft.brainless.genetics.core.GeneticParameters;

public class HumanEvolvableNeuralNetworkGeneration extends Generation<HumanEvolvableNeuralNetwork> {

	public static HumanEvolvableNeuralNetworkGeneration[] createArrayOf(int count) {
		HumanEvolvableNeuralNetworkGeneration[] result = new HumanEvolvableNeuralNetworkGeneration[count];

		for(int i=0; i<count; i++) {
			result[i] = HumanEvolvableNeuralNetworkGeneration.create();
		}
		
		return result;
	}
	
	private static HumanEvolvableNeuralNetworkGeneration create() {
		return new HumanEvolvableNeuralNetworkGeneration();
	}

	@Override
	protected HumanEvolvableNeuralNetwork instantiate(GeneticParameters parameters) {
		HumanEvolvableNeuralNetwork enn = HumanEvolvableNeuralNetwork.create();
		enn.setParameters(parameters);
		return enn;
	}



}
