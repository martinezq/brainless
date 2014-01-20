package org.mysoft.test;

import org.jbox2d.testbed.framework.TestbedSettings;
import org.jbox2d.testbed.framework.TestbedTest;
import org.mysoft.brainless.creature.SimpleCreatureCharacter;
import org.mysoft.brainless.creature.SimpleCreatureSimulation;
import org.mysoft.brainless.neural.core.NeuralNetwork;

public class SimpleCreatureCharacterTest extends TestbedTest {

	static NeuralNetwork best = null;
	
	SimpleCreatureCharacter character;
	SimpleCreatureSimulation simulation = SimpleCreatureSimulation.create();

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
		return "Simple Creature Test";
	}
	
	private NeuralNetwork learn() {
		/*
		GeneticEngine<SimpleCreatureEvolvableNeuralNetworkGeneration, SimpleCreatureEvolvableNeuralNetwork> engine = 
				new GeneticEngine<>(new GeneticParameters());
		
		engine.start(SimpleCreatureEvolvableNeuralNetworkGeneration.createArrayOf(2));
		
		EvolvableNeuralNetwork best = engine.getBest();
		
		System.out.println("Best fit = " + best.getFit());
		
		long ts = System.currentTimeMillis();
		String filePath =  "../../../../../best_" + ts + ".nn";
		
		NeuralNetworkSerializer.serializeToFile(best.getChromosome().getNeuralNetwork(), filePath);
		
		return best.getChromosome().getNeuralNetwork();
		*/
		
		return null;
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
