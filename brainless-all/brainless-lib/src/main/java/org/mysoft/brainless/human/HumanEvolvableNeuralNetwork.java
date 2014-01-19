package org.mysoft.brainless.human;

import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;
import org.mysoft.brainless.neural.core.NeuralNetwork;

@Deprecated
public class HumanEvolvableNeuralNetwork {

	public static HumanEvolvableNeuralNetwork create() {
		HumanEvolvableNeuralNetwork evolvable = new HumanEvolvableNeuralNetwork();
		
		int outputs = 4;
		int storage = 1;
		
		NeuralNetwork neuralNetwork = NeuralNetwork.create(16, outputs + storage);
		neuralNetwork.addStorageNeurons(storage);
		neuralNetwork.randomizeWeights();
		//evolvable.setChromosome(NeuralNetworkChromosome.create(neuralNetwork));
		
		return evolvable;
	}
	
}
