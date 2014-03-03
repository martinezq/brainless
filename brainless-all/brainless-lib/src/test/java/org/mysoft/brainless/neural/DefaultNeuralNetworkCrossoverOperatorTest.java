package org.mysoft.brainless.neural;

import org.junit.Assert;
import org.junit.Test;
import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;
import org.mysoft.brainless.genetics.core.ICrossoverOperator;
import org.mysoft.brainless.genetics.neural.DefaultNeuralNetworkCrossoverOperator;
import org.mysoft.brainless.genetics.neural.WeightLevelNeuralNetworkCrossoverOperator;
import org.mysoft.brainless.neural.core.NeuralNetwork;
import org.mysoft.brainless.neural.core.Neuron;
import org.mysoft.brainless.neural.core.NeuronLayer;

public class DefaultNeuralNetworkCrossoverOperatorTest {

	@Test
	public void test() {
		
		ICrossoverOperator<NeuralNetworkChromosome> op = new DefaultNeuralNetworkCrossoverOperator();
		
		NeuralNetwork nn1 = NeuralNetwork.create(2);
		NeuralNetwork nn2 = NeuralNetwork.create(2);
		
		nn1.randomizeWeights();
		nn2.randomizeWeights();
		
		NeuralNetworkChromosome chromosome1 = NeuralNetworkChromosome.create(nn1);
		NeuralNetworkChromosome chromosome2 = NeuralNetworkChromosome.create(nn2);
		
		NeuralNetworkChromosome r = op.crossover(chromosome1, chromosome2);
		
		Assert.assertTrue("Other chromosome instance expected", r != chromosome1 && r != chromosome2);
		
		NeuralNetwork rnn = r.getNeuralNetwork();
		
		Assert.assertTrue("Other neural network instance expected", rnn != nn1 && rnn != nn2);
		
		boolean notEqualWeights = !rnn.hasEqualWeights(nn1) && !rnn.hasEqualWeights(nn2);
		
		Assert.assertTrue("Neuron weights should differ", notEqualWeights);
		
		int hiddenLayers = rnn.getHiddenLayers().size();
		
		int count1 = 0;
		int count2 = 0;
		
		for(int i=0; i<hiddenLayers; i++) {
			NeuronLayer rl = rnn.getHiddenLayers().get(i);
			NeuronLayer n1l = nn1.getHiddenLayers().get(i);
			NeuronLayer n2l = nn2.getHiddenLayers().get(i);
			
			int neurons = rl.size();
			
			for(int j=0; j<neurons; j++) {
				Neuron rln = rl.get(j);
				Neuron n1ln = n1l.get(j);
				Neuron n2ln = n2l.get(j);
				
				boolean from1 = rln.hasEqualWeights(n1ln);
				boolean from2 = rln.hasEqualWeights(n2ln);
				
				if(from1) {
					count1++;
				}
				
				if(from2) {
					count2++;
				}
				
				Assert.assertTrue("Neuron not from any parent found", from1 || from2);
			}
		}
		
		System.out.println(count1 + " from first, " + count2 + " from second parent");
	}
	
}
