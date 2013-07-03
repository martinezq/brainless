package org.mysoft.test;

import org.jbox2d.testbed.framework.TestbedSettings;
import org.jbox2d.testbed.framework.TestbedTest;
import org.mysoft.brainless.genetics.core.GeneticEngine;
import org.mysoft.brainless.genetics.core.GeneticParameters;
import org.mysoft.brainless.human.HumanCharacter;
import org.mysoft.brainless.neural.core.EvolvableNeuralNetwork;
import org.mysoft.brainless.neural.core.EvolvableNeuralNetworkGeneration;
import org.mysoft.brainless.neural.core.NeuralNetwork;
import org.mysoft.brainless.sim.DefaultSimulation;

public class HumanCharacterTest extends TestbedTest {

	HumanCharacter character;
	DefaultSimulation simulation = DefaultSimulation.create();

	@Override
	public void initTest(boolean argDeserialized) {
		setTitle(getTestName());
		
		NeuralNetwork neuralNetwork = learn();
		
		simulation.initWorld(getWorld());
		character = simulation.initActor(getWorld(), neuralNetwork);
	
	}

	@Override
	public String getTestName() {
		return "Human Character Test";
	}
	
	private NeuralNetwork learn() {
		GeneticEngine<EvolvableNeuralNetworkGeneration, EvolvableNeuralNetwork> engine = 
				new GeneticEngine<>(new GeneticParameters());
		
		engine.start(new EvolvableNeuralNetworkGeneration(), new EvolvableNeuralNetworkGeneration());
		
		return engine.getBest().getChromosome().getNeuralNetwork();
	}
	
	@Override
	public synchronized void step(TestbedSettings settings) {
		super.step(settings);
		character.getBrain().update();
		
		double pos = character.getBody().getMasterBone().getPhysicalBody().getPosition().y;
		//System.err.println(pos);

	}
	
	
}
