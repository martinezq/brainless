package org.mysoft.brainless.creature;

import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;
import org.mysoft.brainless.genetics.core.IGeneticFitCalculator;

public class SimpleCreatureWalkingFitCalculator implements IGeneticFitCalculator<NeuralNetworkChromosome> {

	@Override
	public double calculate(NeuralNetworkChromosome chromosome) {
		SimpleCreatureSimulation simulation = SimpleCreatureSimulation.create(chromosome.getNeuralNetwork());

		simulation.init();
		
		chromosome.getNeuralNetwork().getStorageLayer().reset();
		
		SimpleCreatureCharacter character = simulation.getCharacter();
		
		simulation.simulate();
		
		double endPosX = character.getBody().getMasterBone().getPhysicalBody().getPosition().x;

		double fit =  1.0 / Math.abs(endPosX);
		
		return fit;
		
	}

}
