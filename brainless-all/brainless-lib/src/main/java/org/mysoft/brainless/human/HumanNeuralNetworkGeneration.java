package org.mysoft.brainless.human;

import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;
import org.mysoft.brainless.genetics.core.Generation;
import org.mysoft.brainless.genetics.core.GeneticParameters;
import org.mysoft.brainless.neural.core.NeuralNetwork;


public class HumanNeuralNetworkGeneration extends Generation<NeuralNetworkChromosome> {

	public static HumanNeuralNetworkGeneration[] createArrayOf(int count) {
		HumanNeuralNetworkGeneration[] result = new HumanNeuralNetworkGeneration[count];

		for(int i=0; i<count; i++) {
			result[i] = HumanNeuralNetworkGeneration.create();
		}
		
		return result;
	}
	
	private static HumanNeuralNetworkGeneration create() {
		return new HumanNeuralNetworkGeneration();
	}

	@Override
	protected NeuralNetworkChromosome instantiate(GeneticParameters<NeuralNetworkChromosome> parameters) {
		int outputs = 18;
		int storage = 2;
		
		NeuralNetwork neuralNetwork = NeuralNetwork.create(16, 16, outputs + storage);
		neuralNetwork.addStorageNeurons(storage);
		neuralNetwork.randomizeWeights();

		return NeuralNetworkChromosome.create(neuralNetwork);
	}


}
