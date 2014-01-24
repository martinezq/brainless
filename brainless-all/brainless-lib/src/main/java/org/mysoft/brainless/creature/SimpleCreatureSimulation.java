package org.mysoft.brainless.creature;

import org.mysoft.brainless.neural.core.NeuralNetwork;
import org.mysoft.brainless.sim.CharacterSimulation;
import org.mysoft.brainless.sim.SimulationParameters;

public class SimpleCreatureSimulation extends CharacterSimulation<SimpleCreatureCharacter> {

	private NeuralNetwork neuralNetwork;
	
	public final static SimpleCreatureSimulation create(NeuralNetwork nn) {
		SimpleCreatureSimulation s = new SimpleCreatureSimulation();
		s.setParameters(new SimulationParameters());
		s.neuralNetwork = nn;
		return s;
	}
	
	@Override
	public SimpleCreatureCharacter initCharacter() {
		SimpleCreatureBody body = SimpleCreatureBody.create(world);
		SimpleCreatureBrain brain = SimpleCreatureBrain.create(neuralNetwork);
		SimpleCreatureCharacter character = SimpleCreatureCharacter.create(body, brain);
		
		return character;
	}

}
