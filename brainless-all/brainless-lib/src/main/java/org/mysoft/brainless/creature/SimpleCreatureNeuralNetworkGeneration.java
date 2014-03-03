package org.mysoft.brainless.creature;

import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;
import org.mysoft.brainless.genetics.core.Generation;
import org.mysoft.brainless.genetics.core.GeneticParameters;
import org.mysoft.brainless.neural.core.NeuralNetwork;


public class SimpleCreatureNeuralNetworkGeneration extends Generation<NeuralNetworkChromosome> {

	public static SimpleCreatureNeuralNetworkGeneration[] createArrayOf(int count) {
		SimpleCreatureNeuralNetworkGeneration[] result = new SimpleCreatureNeuralNetworkGeneration[count];

		for(int i=0; i<count; i++) {
			result[i] = SimpleCreatureNeuralNetworkGeneration.create();
		}
		
		return result;
	}
	
	private static SimpleCreatureNeuralNetworkGeneration create() {
		return new SimpleCreatureNeuralNetworkGeneration();
	}

	@Override
	public NeuralNetworkChromosome instantiate(GeneticParameters<NeuralNetworkChromosome> parameters) {
		int outputs = 6;
		int storage = 16;
		
		NeuralNetwork neuralNetwork = NeuralNetwork.create(32, 32, 32, 32, outputs + storage);
		neuralNetwork.addStorageNeurons(storage);
		neuralNetwork.randomizeWeights();

		return NeuralNetworkChromosome.create(neuralNetwork);
	}


}
