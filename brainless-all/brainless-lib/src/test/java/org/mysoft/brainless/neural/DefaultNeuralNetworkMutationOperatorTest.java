package org.mysoft.brainless.neural;

import org.junit.Assert;
import org.junit.Test;
import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;
import org.mysoft.brainless.genetics.neural.DefaultNeuralNetworkMutationOperator;
import org.mysoft.brainless.neural.core.NeuralNetwork;
import org.mysoft.brainless.neural.core.Neuron;
import org.mysoft.brainless.neural.core.NeuronLayer;

public class DefaultNeuralNetworkMutationOperatorTest {

	@Test
	public void test() {
		
		DefaultNeuralNetworkMutationOperator op = new DefaultNeuralNetworkMutationOperator();
		
		NeuralNetwork nn = NeuralNetwork.create(64, 64, 64, 64);
		nn.randomizeWeights();
		
		NeuralNetworkChromosome chromosome = NeuralNetworkChromosome.create(nn);
		
		NeuralNetworkChromosome r = op.mutate(chromosome, 0.1);
		
		Assert.assertTrue("Other chromosome instance expected", r != chromosome);
		
		NeuralNetwork rnn = r.getNeuralNetwork();
		
		Assert.assertTrue("Other neural network instance expected", rnn != nn);
		
		boolean notEqualWeights = !rnn.hasEqualWeights(nn);
		
		Assert.assertTrue("Neuron weights should differ", notEqualWeights);
		
		int hiddenLayers = rnn.getHiddenLayers().size();
		
		int count = 0;
		int total = 0;
		
		for(int i=0; i<hiddenLayers; i++) {
			NeuronLayer rl = rnn.getHiddenLayers().get(i);
			NeuronLayer nl = nn.getHiddenLayers().get(i);
			
			int neurons = rl.size();
			
			for(int j=0; j<neurons; j++) {
				Neuron rln = rl.get(j);
				Neuron nln = nl.get(j);
				
				boolean randomized = !rln.hasEqualWeights(nln);

				total++;
				
				if(randomized) {
					count++;
				}
			}
		}
		
		double perc = 100.0 * count / total;
		
		System.out.println(perc + "% of randomized neurons");
	}

	
}
