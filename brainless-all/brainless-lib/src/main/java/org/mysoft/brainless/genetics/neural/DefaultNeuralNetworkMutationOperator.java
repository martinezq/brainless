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

		int mutatedCount = 0;
		int totalCount = 0;
		
		for (NeuronLayer layer : neuralNetwork.getHiddenLayers()) {

			for (Neuron neuron : layer) {

				int count = neuron.getWeightsCount();

				for(int weightIndex = 0; weightIndex < count; weightIndex++) {
					double rand = Math.random();
					boolean change = Math.abs(rand) < probability;
					
					totalCount++;
					
					if (change) {
						InputWeight newWeight = InputWeight.random();
						neuron.setWeight(weightIndex, newWeight);
						mutatedCount++;
					}
				}

			}
		}
		
		double prc = (double)mutatedCount / (double)totalCount;
		
		//System.out.println("mutated genes: " + prc);
		return chromosome;
	}

}
