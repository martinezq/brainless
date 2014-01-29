package org.mysoft.brainless.creature;

import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;
import org.mysoft.brainless.genetics.core.IGeneticFitCalculator;

public class SimpleCreatureStandingFitCalculator implements IGeneticFitCalculator<NeuralNetworkChromosome> {

	@Override
	public double calculate(NeuralNetworkChromosome chromosome) {
		SimpleCreatureSimulation simulation = SimpleCreatureSimulation.create(chromosome.getNeuralNetwork());

		simulation.init();
		
		chromosome.getNeuralNetwork().getStorageLayer().reset();
		
		SimpleCreatureCharacter character = simulation.getCharacter();
		
		double startPosX = character.getBody().getMasterBone().getPhysicalBody().getPosition().x;
		
		simulation.simulate();
		
		double endPosX = character.getBody().getMasterBone().getPhysicalBody().getPosition().x;
		double endPosY = ((SimpleCreatureBody)character.getBody()).getHead().getPhysicalBody().getPosition().y;
		
		double deltaX = simulation.getCharacter().getDeltaX() + 1.0;
		double deltaY = simulation.getCharacter().getDeltaY() + 1.0;
		
		double minY = simulation.getCharacter().getMinY();
/*		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

*/

		double fit =  1.0 / Math.pow(2, endPosX);
		
		return fit;
		
	}

}
