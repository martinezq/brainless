package org.mysoft.brainless.test;

import org.jbox2d.testbed.framework.TestbedSettings;
import org.jbox2d.testbed.framework.TestbedTest;
import org.mysoft.brainless.character.Character;
import org.mysoft.brainless.sim.CharacterSimulation;

public class CharacterSimulationTest<T extends CharacterSimulation<?>> extends TestbedTest {

	Character character;
	T simulation;
	int iterationsLeft;
	
	public CharacterSimulationTest(T simulation) {
		this.simulation = simulation;
	}
	
	@Override
	public void initTest(boolean argDeserialized) {
		setTitle(getTestName());

		simulation.setWorld(getWorld());
		simulation.initWorld();
		character = simulation.initCharacter();

		iterationsLeft = simulation.getParameters().getIterations();
	}

	@Override
	public String getTestName() {
		return "Character simulation";
	}
	
	@Override
	public synchronized void step(TestbedSettings settings) {
		if (iterationsLeft-- == 0) {
			settings.pause = true;
		}

		super.step(settings);
		character.getBrain().step();

		double posx = character.getBody().getMasterBone().getPhysicalBody().getPosition().x;
		double posy = character.getBody().getMasterBone().getPhysicalBody().getPosition().y;

		// System.out.println(posx + ", " + posy);

	}

	@Override
	public void reset() {
		iterationsLeft = simulation.getParameters().getIterations();
		super.getModel().getSettings().pause = false;
		super.reset();
	}

}
