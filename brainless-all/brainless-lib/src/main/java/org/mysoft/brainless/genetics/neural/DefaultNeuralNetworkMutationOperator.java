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
		
		for(NeuronLayer layer: neuralNetwork.getHiddenLayers()) {

			for(Neuron neuron: layer) {
			
				int count = neuron.getWeightsCount();
				
				double rand = Math.random();
				boolean change = rand < probability;
				
				for(int i=0; i<count; i++) {
					
					if(change) {
						double oldValue = neuron.getWeight(i).getValue();
						double newValue = oldValue;
						
						boolean doRandomize = randomizer.nextBoolean();
						
						if(doRandomize) {
							neuron.setWeight(i, InputWeight.random());
						} else {
							boolean doAdd = randomizer.nextBoolean();
							boolean doMult = randomizer.nextBoolean();
							boolean doRev = randomizer.nextBoolean();
							
							if(doMult) {
								newValue = newValue * (Math.random() + 0.5);
							} 
							
							if(doAdd) {
								newValue += 0.2 * Math.random() - 0.1;
							} 
							
							if(!doAdd && !doMult && doRev) {
								newValue = -newValue;
							}
							
							neuron.setWeight(i, InputWeight.create(newValue));
						}
					}
				}
			}
		}
		return chromosome;
	}

}
