package org.mysoft.brainless.human;

import org.mysoft.brainless.neural.core.NeuralNetwork;
import org.mysoft.brainless.sim.CharacterSimulation;
import org.mysoft.brainless.sim.SimulationParameters;

public class HumanSimulation extends CharacterSimulation<HumanCharacter> {
	
	private NeuralNetwork neuralNetwork;
	
	public final static HumanSimulation create(NeuralNetwork nn) {
		HumanSimulation s = new HumanSimulation();
		SimulationParameters p = new SimulationParameters();
		p.setDurationSeconds(6);
		s.setParameters(p);
		s.neuralNetwork = nn;
		return s;
	}
	
	
	@Override
	public HumanCharacter initCharacter() {
		HumanBody body = HumanBody.create(world);
		HumanBrain brain = HumanBrain.create(neuralNetwork);
		HumanCharacter character = HumanCharacter.create(body, brain);
		
		return character;
	}
	

}
