package org.mysoft.brainless.genetics.chromosome;

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
		for(NeuronLayer layer: neuralNetwork.getHiddenLayers()) {
			for(Neuron neuron: layer) {
				int count = neuron.getWeightsCount();
				for(int i=0; i<count; i++) {
					if(Math.random() < parameters.getMutationProbability()) {
						double oldValue = neuron.getWeight(i).getValue();
						double newValue = oldValue * (0.2 * Math.random() - 0.1);
						neuron.setWeight(i, InputWeight.create(newValue));
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
		
		
		for(int li=0; li<newNetwork.getHiddenLayers().size(); li++) {
			NeuronLayer newLayer = newNetwork.getHiddenLayers().get(li);
			NeuronLayer otherLayer = otherNetwork.getHiddenLayers().get(li);
			
			for(int ni=0; ni<newLayer.size(); ni++) {
				Neuron newNeuron = newLayer.get(ni);
				Neuron otherNeuron = otherLayer.get(ni);
				
				int weightsCount = newNeuron.getWeightsCount();
				
				for(int ii=0; ii<weightsCount; ii++) {
					if(Math.random() < 0.5) {
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

}
