package org.mysoft.brainless.creature;

import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;
import org.mysoft.brainless.genetics.core.IGeneticFitCalculator;

public class SimpleCreatureWalkingFitCalculator implements IGeneticFitCalculator<NeuralNetworkChromosome> {

	@Override
	public double calculate(NeuralNetworkChromosome chromosome) {
		SimpleCreatureSimulation simulation = SimpleCreatureSimulation.create(chromosome.getNeuralNetwork());

		simulation.init();
		
		SimpleCreatureCharacter character = simulation.getCharacter();

		character.reset();
		simulation.simulate();
		
		double endPosX = character.getBody().getMasterBone().getPhysicalBody().getPosition().x;
		double energyUsed = character.getBody().getEnergyUsed() * 1000.0;
		
		double fit =  energyUsed / Math.abs(endPosX);
		
		return fit;
		
	}

}
