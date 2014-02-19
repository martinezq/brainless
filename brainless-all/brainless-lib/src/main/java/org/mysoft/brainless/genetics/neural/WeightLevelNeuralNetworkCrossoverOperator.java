package org.mysoft.brainless.genetics.neural;

import java.util.Random;

import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;
import org.mysoft.brainless.genetics.core.ICrossoverOperator;
import org.mysoft.brainless.neural.core.InputWeight;
import org.mysoft.brainless.neural.core.NeuralNetwork;
import org.mysoft.brainless.neural.core.Neuron;
import org.mysoft.brainless.neural.core.NeuronLayer;

public class WeightLevelNeuralNetworkCrossoverOperator implements ICrossoverOperator<NeuralNetworkChromosome> {

	@Override
	public NeuralNetworkChromosome crossover(NeuralNetworkChromosome chromosome1, NeuralNetworkChromosome chromosome2) {
		NeuralNetwork network1 = chromosome1.getNeuralNetwork();
		NeuralNetwork network2 = chromosome2.getNeuralNetwork();

		NeuralNetwork newNetwork = network1.duplicate();

		Random randomizer = new Random();

		for (int li = 0; li < newNetwork.getHiddenLayers().size(); li++) {
			NeuronLayer newLayer = newNetwork.getHiddenLayers().get(li);
			NeuronLayer otherLayer = network2.getHiddenLayers().get(li);

			for (int ni = 0; ni < newLayer.size(); ni++) {
				Neuron newNeuron = newLayer.get(ni);
				Neuron otherNeuron = otherLayer.get(ni);

				int weightsCount = newNeuron.getWeightsCount();

				for (int ii = 0; ii < weightsCount; ii++) {
					boolean fromOther = randomizer.nextBoolean();
					if (fromOther) {
						InputWeight otherWeight = otherNeuron.getWeight(ii);
						newNeuron.setWeight(ii, otherWeight.duplicate());
					}
				}
			}

		}

		NeuralNetworkChromosome childChromosome = NeuralNetworkChromosome.create(newNetwork);
		childChromosome.setParameters(chromosome1.getParameters());

		return childChromosome;
	}

}
