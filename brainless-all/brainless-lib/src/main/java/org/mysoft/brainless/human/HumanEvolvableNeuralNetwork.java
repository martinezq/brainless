package org.mysoft.brainless.human;

import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;
import org.mysoft.brainless.neural.core.EvolvableNeuralNetwork;
import org.mysoft.brainless.neural.core.NeuralNetwork;

public class HumanEvolvableNeuralNetwork extends EvolvableNeuralNetwork {

	public static HumanEvolvableNeuralNetwork create() {
		HumanEvolvableNeuralNetwork evolvable = new HumanEvolvableNeuralNetwork();
		
		int outputs = 4;
		int storage = 1;
		
		NeuralNetwork neuralNetwork = NeuralNetwork.create(16, outputs + storage);
		neuralNetwork.addStorageNeurons(storage);
		neuralNetwork.randomizeWeights();
		evolvable.setChromosome(NeuralNetworkChromosome.create(neuralNetwork));
		
		return evolvable;
	}
	
	@Override
	public double calculateFit() {
		HumanSimulation simulation = HumanSimulation.create();

		simulation.init(chromosome.getNeuralNetwork());
		
		chromosome.getNeuralNetwork().getStorageLayer().reset();
		
		HumanCharacter character = simulation.getCharacter();
		
		double startPosX = character.getBody().getMasterBone().getPhysicalBody().getPosition().x;
		
		simulation.simulate();
		
		double endPosX = character.getBody().getMasterBone().getPhysicalBody().getPosition().x;
		double endPosY = ((HumanBody)character.getBody()).getHead().getPhysicalBody().getPosition().y;
		
		double deltaX = simulation.getCharacter().getDeltaX() + 1.0;
		double deltaY = simulation.getCharacter().getDeltaY() + 1.0;
/*		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
*/

		return Math.pow(2, deltaX) + Math.pow(8, deltaY);
		
	}
	
}
