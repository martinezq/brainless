package org.mysoft.brainless.creature;

import org.mysoft.brainless.neural.core.NeuralNetwork;

@Deprecated
public class SimpleCreatureEvolvableNeuralNetwork  {

	public static SimpleCreatureEvolvableNeuralNetwork create() {
		SimpleCreatureEvolvableNeuralNetwork evolvable = new SimpleCreatureEvolvableNeuralNetwork();
		
		int outputs = 4;
		int storage = 1;
		
		NeuralNetwork neuralNetwork = NeuralNetwork.create(24, 16, outputs + storage);
		neuralNetwork.addStorageNeurons(storage);
		neuralNetwork.randomizeWeights();
		//evolvable.setChromosome(NeuralNetworkChromosome.create(neuralNetwork));
		
		return evolvable;
	}
	
/*	
	public double calculateFit() {
		SimpleCreatureSimulation simulation = SimpleCreatureSimulation.create();

		simulation.init(chromosome.getNeuralNetwork());
		
		chromosome.getNeuralNetwork().getStorageLayer().reset();
		
		SimpleCreatureCharacter character = simulation.getCharacter();
		
		double startPosX = character.getBody().getMasterBone().getPhysicalBody().getPosition().x;
		
		simulation.simulate();
		
		double endPosX = character.getBody().getMasterBone().getPhysicalBody().getPosition().x;
		double endPosY = ((SimpleCreatureBody)character.getBody()).getHead().getPhysicalBody().getPosition().y;
		
		double deltaX = simulation.getCharacter().getDeltaX() + 1.0;
		double deltaY = simulation.getCharacter().getDeltaY() + 1.0;


		if(endPosX <= 0) {
			return Double.MAX_VALUE;
		}
		
		return 1.0 / Math.pow(2, Math.abs(endPosX));
		
	}
	*/
}
