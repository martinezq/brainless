package org.mysoft.brainless.neural.core;

import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;
import org.mysoft.brainless.genetics.core.Evolvable;
import org.mysoft.brainless.human.HumanCharacter;
import org.mysoft.brainless.sim.DefaultSimulation;

public class EvolvableNeuralNetwork extends Evolvable<NeuralNetworkChromosome> {

	public static EvolvableNeuralNetwork create() {
		EvolvableNeuralNetwork evolvable = new EvolvableNeuralNetwork();
		
		NeuralNetwork neuralNetwork = NeuralNetwork.create(3, 5, 12, 18);
		neuralNetwork.randomizeWeights();
		evolvable.chromosome = NeuralNetworkChromosome.create(neuralNetwork);
		
		return evolvable;
	}
	
	@Override
	public double calculateFit() {
		DefaultSimulation simulation = DefaultSimulation.create();

		simulation.init(chromosome.getNeuralNetwork());
		simulation.simulate();
		
		HumanCharacter character = simulation.getCharacter();
	
		return character.getSummaryDelta();
		
	}

	@Override
	public String toString() {
		return "Individual fit = " + calculateFit();
	}
	
}
