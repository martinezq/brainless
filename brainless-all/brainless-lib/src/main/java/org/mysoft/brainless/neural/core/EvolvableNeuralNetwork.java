package org.mysoft.brainless.neural.core;

import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;
import org.mysoft.brainless.genetics.core.Evolvable;
import org.mysoft.brainless.human.HumanCharacter;
import org.mysoft.brainless.sim.DefaultSimulation;

public class EvolvableNeuralNetwork extends Evolvable<NeuralNetworkChromosome> {

	public static EvolvableNeuralNetwork create() {
		EvolvableNeuralNetwork evolvable = new EvolvableNeuralNetwork();
		
		NeuralNetwork neuralNetwork = NeuralNetwork.create(3, 10, 10, 18, 18);
		neuralNetwork.randomizeWeights();
		evolvable.chromosome = NeuralNetworkChromosome.create(neuralNetwork);
		
		return evolvable;
	}
	
	@Override
	public double calculateFit() {
		DefaultSimulation simulation = DefaultSimulation.create();

		simulation.init(chromosome.getNeuralNetwork());
		
		HumanCharacter character = simulation.getCharacter();
		
		double startPos = character.getBody().getMasterBone().getPhysicalBody().getPosition().x;
		
		simulation.simulate();
		
		double endPos = character.getBody().getMasterBone().getPhysicalBody().getPosition().x;
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		double delta = startPos - endPos;
		
		if(delta < 0) {
			return Double.MAX_VALUE;
		} else {
			return 100.0 / delta;
		}
		
	}

	@Override
	public String toString() {
		return "Individual fit = " + calculateFit();
	}
	
}
