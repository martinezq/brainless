package org.mysoft.brainless.human;

import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;
import org.mysoft.brainless.genetics.core.IGeneticFitCalculator;

public class HumanWalkingFitCalculator implements IGeneticFitCalculator<NeuralNetworkChromosome> {

	@Override
	public double calculate(NeuralNetworkChromosome chromosome) {
		HumanSimulation simulation = HumanSimulation.create(chromosome.getNeuralNetwork());

		simulation.init();
		
		chromosome.getNeuralNetwork().getStorageLayer().reset();
		
		HumanCharacter character = simulation.getCharacter();
		
		character.reset();
		simulation.simulate();
		
		double endPosX = character.getBody().getMasterBone().getPhysicalBody().getPosition().x;


		return 1.0 / Math.abs(endPosX);
	}

}
