package org.mysoft.brainless.human;

import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;
import org.mysoft.brainless.genetics.core.Generation;
import org.mysoft.brainless.genetics.core.GeneticParameters;

@Deprecated
public class HumanEvolvableNeuralNetworkGeneration extends Generation<NeuralNetworkChromosome> {

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
	protected NeuralNetworkChromosome instantiate(GeneticParameters<NeuralNetworkChromosome> parameters) {
		return null;
	}


}
