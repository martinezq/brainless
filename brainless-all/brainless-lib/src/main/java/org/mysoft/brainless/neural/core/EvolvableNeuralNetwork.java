package org.mysoft.brainless.neural.core;

import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;
import org.mysoft.brainless.genetics.core.Evolvable;
import org.mysoft.brainless.human.HumanBody;
import org.mysoft.brainless.human.HumanCharacter;
import org.mysoft.brainless.sim.DefaultSimulation;

public class EvolvableNeuralNetwork extends Evolvable<NeuralNetworkChromosome> {

	public static EvolvableNeuralNetwork create() {
		EvolvableNeuralNetwork evolvable = new EvolvableNeuralNetwork();
		
		NeuralNetwork neuralNetwork = NeuralNetwork.create(10, 12, 16, 18);
		neuralNetwork.randomizeWeights();
		evolvable.chromosome = NeuralNetworkChromosome.create(neuralNetwork);
		
		return evolvable;
	}
	
	@Override
	public double calculateFit() {
		DefaultSimulation simulation = DefaultSimulation.create();

		simulation.init(chromosome.getNeuralNetwork());
		
		HumanCharacter character = simulation.getCharacter();
		
		double startPosX = character.getBody().getMasterBone().getPhysicalBody().getPosition().x;
		
		simulation.simulate();
		
		double endPosX = character.getBody().getMasterBone().getPhysicalBody().getPosition().x;
		double endPosY = ((HumanBody)character.getBody()).getHead().getPhysicalBody().getPosition().y;
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		//double delta = -endPosX;
		
		endPosX = -endPosX;
		
		if(endPosX < 0 || endPosY < 0) {
			return Double.MAX_VALUE;
		} else {
			return 1.0 * (100.0 / (1.0 + endPosX)) + 3.0 * (13.0 / (1.0 + endPosY));
		}
		
	}

	@Override
	public String toString() {
		return "Individual fit = " + getFit();
	}
	
}
