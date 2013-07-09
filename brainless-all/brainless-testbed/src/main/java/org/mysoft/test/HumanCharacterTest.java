package org.mysoft.test;

import org.jbox2d.testbed.framework.TestbedSettings;
import org.jbox2d.testbed.framework.TestbedTest;
import org.mysoft.brainless.genetics.core.GeneticEngine;
import org.mysoft.brainless.genetics.core.GeneticParameters;
import org.mysoft.brainless.human.HumanCharacter;
import org.mysoft.brainless.neural.core.EvolvableNeuralNetwork;
import org.mysoft.brainless.neural.core.EvolvableNeuralNetworkGeneration;
import org.mysoft.brainless.neural.core.NeuralNetwork;
import org.mysoft.brainless.neural.util.NeuralNetworkSerializer;
import org.mysoft.brainless.sim.DefaultSimulation;

public class HumanCharacterTest extends TestbedTest {

	static NeuralNetwork best = null;
	
	HumanCharacter character;
	DefaultSimulation simulation = DefaultSimulation.create();

	@Override
	public void initTest(boolean argDeserialized) {
		
		setTitle(getTestName());
		
		if(best == null) {
			best = learn();
		}
		
		simulation.initWorld(getWorld());
		character = simulation.initActor(getWorld(), best);
	
	}

	@Override
	public String getTestName() {
		return "Human Character Test";
	}
	
	private NeuralNetwork learn() {
		GeneticEngine<EvolvableNeuralNetworkGeneration, EvolvableNeuralNetwork> engine = 
				new GeneticEngine<>(new GeneticParameters());
		
		engine.start(EvolvableNeuralNetworkGeneration.createArrayOf(2));
		
		EvolvableNeuralNetwork best = engine.getBest();
		
		System.out.println("Best fit = " + best.getFit());
		
		long ts = System.currentTimeMillis();
		String filePath =  "../../../../../best_" + ts + ".nn";
		
		NeuralNetworkSerializer.serializeToFile(best.getChromosome().getNeuralNetwork(), filePath);
		
		return best.getChromosome().getNeuralNetwork();
	}
	
	@Override
	public synchronized void step(TestbedSettings settings) {
		super.step(settings);
		character.getBrain().step();
		
		double posx = character.getBody().getMasterBone().getPhysicalBody().getPosition().x;
		double posy = character.getBody().getMasterBone().getPhysicalBody().getPosition().y;
		
		//System.out.println(posx + ", " + posy);
	}
	
	
}
