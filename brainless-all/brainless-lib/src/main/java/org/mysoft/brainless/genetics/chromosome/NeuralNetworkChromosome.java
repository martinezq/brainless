package org.mysoft.brainless.genetics.chromosome;

import java.util.Random;

import org.mysoft.brainless.neural.core.InputWeight;
import org.mysoft.brainless.neural.core.NeuralNetwork;
import org.mysoft.brainless.neural.core.Neuron;
import org.mysoft.brainless.neural.core.NeuronLayer;

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
	public Chromosome mutate() {
		double prob = parameters.getMutationProbability() / 2.0;
		Random randomizer = new Random();
		
		for(NeuronLayer layer: neuralNetwork.getHiddenLayers()) {

			for(Neuron neuron: layer) {
			
				int count = neuron.getWeightsCount();
				
				for(int i=0; i<count; i++) {
					
					double rand = Math.random() - 0.5;
					
					if(rand < prob && rand > -prob) {
						double oldValue = neuron.getWeight(i).getValue();
						double newValue = oldValue;
						
						boolean doRandomize = randomizer.nextBoolean();
						
						if(doRandomize) {
							//neuron.setWeight(i, InputWeight.random());
							neuron.setWeight(i, InputWeight.create(0));
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
		return this;
	}

	@Override
	public Chromosome crossover(Chromosome other) {
		NeuralNetwork otherNetwork = ((NeuralNetworkChromosome)other).getNeuralNetwork();
		
		NeuralNetwork newNetwork = otherNetwork.duplicate();
		
		Random randomizer = new Random();
		
		for(int li=0; li<newNetwork.getHiddenLayers().size(); li++) {
			NeuronLayer newLayer = newNetwork.getHiddenLayers().get(li);
			NeuronLayer otherLayer = otherNetwork.getHiddenLayers().get(li);
			
			for(int ni=0; ni<newLayer.size(); ni++) {
				Neuron newNeuron = newLayer.get(ni);
				Neuron otherNeuron = otherLayer.get(ni);
				
				int weightsCount = newNeuron.getWeightsCount();
				
				boolean fromOther = randomizer.nextBoolean();
				
				if(fromOther) {
					for(int ii=0; ii<weightsCount; ii++) {
						InputWeight otherWeight = otherNeuron.getWeight(ii);
						newNeuron.setWeight(ii, otherWeight.duplicate());
					}
				}
			}
			
		}

		NeuralNetworkChromosome childChromosome = NeuralNetworkChromosome.create(newNetwork);
		childChromosome.setParameters(parameters);
		
		return childChromosome;
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
