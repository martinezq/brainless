package org.mysoft.brainless.human;

import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;
import org.mysoft.brainless.genetics.core.IGeneticFitCalculator;

public class HumanStandingFitCalculator implements IGeneticFitCalculator<NeuralNetworkChromosome> {

	@Override
	public double calculate(NeuralNetworkChromosome chromosome) {
		HumanSimulation simulation = HumanSimulation.create(chromosome.getNeuralNetwork());

		simulation.init();
		
		chromosome.getNeuralNetwork().getStorageLayer().reset();
		
		HumanCharacter character = simulation.getCharacter();
		
		double startPosX = character.getBody().getMasterBone().getPhysicalBody().getPosition().x;
		
		simulation.simulate();
		
		double endPosX = character.getBody().getMasterBone().getPhysicalBody().getPosition().x;
		double endPosY = ((HumanBody)character.getBody()).getHead().getPhysicalBody().getPosition().y;
		
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

		return 1.0 / (minY + 2.0);
	}

}
