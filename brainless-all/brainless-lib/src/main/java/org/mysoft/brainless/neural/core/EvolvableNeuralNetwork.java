package org.mysoft.brainless.neural.core;

import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;
import org.mysoft.brainless.genetics.core.Evolvable;
import org.mysoft.brainless.human.HumanCharacter;
import org.mysoft.brainless.sim.DefaultSimulation;

public class EvolvableNeuralNetwork extends Evolvable<NeuralNetworkChromosome> {

	public static EvolvableNeuralNetwork create() {
		EvolvableNeuralNetwork evolvable = new EvolvableNeuralNetwork();
		
		NeuralNetwork neuralNetwork = NeuralNetwork.createDefault();
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
		
		double endPosition = character.getBody().getMasterBone().getPhysicalBody().getPosition().y;
		
		fit = 100 - endPosition;
	
		return fit;
		
	}

	@Override
	public String toString() {
		return "Individual fit = " + calculateFit();
	}
	
}
