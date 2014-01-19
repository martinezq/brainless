package org.mysoft.brainless.genetics.chromosome;

import org.mysoft.brainless.neural.core.NeuralNetwork;

public class NeuralNetworkChromosome extends Chromosome {

	protected NeuralNetwork neuralNetwork;
	
	public static NeuralNetworkChromosome create(NeuralNetwork neuralNetwork) {
		NeuralNetworkChromosome chromosome = new NeuralNetworkChromosome();
		chromosome.neuralNetwork = neuralNetwork;
		return chromosome;
	}
	
	public NeuralNetwork getNeuralNetwork() {
		return neuralNetwork;
	}
	
	@Override
	public Chromosome duplicate() {
		NeuralNetworkChromosome chromosome = NeuralNetworkChromosome.create(neuralNetwork.duplicate());
		chromosome.setParameters(parameters);
		return chromosome;
	}
	
	@Override
	public Chromosome randomize() {
		NeuralNetwork nn = neuralNetwork.duplicate();
		nn.randomizeWeights();
		
		NeuralNetworkChromosome chromosome = NeuralNetworkChromosome.create(nn);
		chromosome.setParameters(parameters);
		return chromosome;
	}

}
