package org.mysoft.brainless.test;

import org.jbox2d.testbed.framework.TestbedModel;
import org.mysoft.brainless.sim.Simulation;

public abstract class CharacterSimulationTestbedModel<T extends CharacterSimulationTest<?>, S extends Simulation> extends TestbedModel {

	protected T test;
	protected S simulation;
	
	public T getTest() {
		return test;
	}
		
	public void init() {
		addCategory("Default");

		this.simulation = createSimulation();
		this.test = createTest();
		
		addTest(test);

		getSettings().getSetting("Hz").value = simulation.getParameters().getHz();
		getSettings().getSetting("Vel Iters").value = simulation.getParameters().getVelIterations();
		getSettings().getSetting("Pos Iters").value = simulation.getParameters().getPosIterations();
	}
	
	abstract protected T createTest();
	
	abstract protected S createSimulation();
	
}
