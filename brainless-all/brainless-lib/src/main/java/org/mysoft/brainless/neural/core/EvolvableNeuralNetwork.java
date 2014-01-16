package org.mysoft.brainless.neural.core;

import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;
import org.mysoft.brainless.genetics.core.Evolvable;
import org.mysoft.brainless.human.HumanBody;
import org.mysoft.brainless.human.HumanCharacter;
import org.mysoft.brainless.sim.DefaultSimulation;

public class EvolvableNeuralNetwork extends Evolvable<NeuralNetworkChromosome> {

	public static EvolvableNeuralNetwork create() {
		EvolvableNeuralNetwork evolvable = new EvolvableNeuralNetwork();
		
		int outputs = 18;
		int storage = 4;
		
		NeuralNetwork neuralNetwork = NeuralNetwork.create(16, 16, 16, outputs + storage);
		neuralNetwork.addStorageNeurons(storage);
		neuralNetwork.randomizeWeights();
		evolvable.chromosome = NeuralNetworkChromosome.create(neuralNetwork);
		
		return evolvable;
	}
	
	@Override
	public double calculateFit() {
		DefaultSimulation simulation = DefaultSimulation.create();

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

	@Override
	public String toString() {
		return "Individual fit = " + getFit();
	}
	
}
