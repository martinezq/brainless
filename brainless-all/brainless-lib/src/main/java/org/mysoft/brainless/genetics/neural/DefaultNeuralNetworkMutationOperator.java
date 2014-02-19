package org.mysoft.brainless.genetics.neural;

import java.util.Random;

import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;
import org.mysoft.brainless.genetics.core.IMutationOperator;
import org.mysoft.brainless.neural.core.InputWeight;
import org.mysoft.brainless.neural.core.NeuralNetwork;
import org.mysoft.brainless.neural.core.Neuron;
import org.mysoft.brainless.neural.core.NeuronLayer;

public class DefaultNeuralNetworkMutationOperator implements IMutationOperator<NeuralNetworkChromosome> {

	@Override
	public NeuralNetworkChromosome mutate(NeuralNetworkChromosome chromosome, double probability) {
		chromosome = chromosome.duplicate();
		NeuralNetwork neuralNetwork = chromosome.getNeuralNetwork();
		Random randomizer = new Random();

		for (NeuronLayer layer : neuralNetwork.getHiddenLayers()) {

			for (Neuron neuron : layer) {

				int count = neuron.getWeightsCount();

				double rand = Math.random();
				boolean change = rand < probability;

				if (change) {
					int weightIndex = randomizer.nextInt(count);
					InputWeight oldWeight = neuron.getWeight(weightIndex);
					double f = Math.random() * 0.2 - 0.1;
					InputWeight newWeight = InputWeight.create(f + oldWeight.getValue());
					newWeight.normalize();
					neuron.setWeight(weightIndex, newWeight);
				}
			}
		}
		return chromosome;
	}

}
