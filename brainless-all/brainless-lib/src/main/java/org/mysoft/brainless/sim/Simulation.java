package org.mysoft.brainless.sim;


public abstract class Simulation {
	
	
	protected SimulationParameters parameters;
	
	public void setParameters(SimulationParameters parameters) {
		this.parameters = parameters;
	}
	
	public SimulationParameters getParameters() {
		return parameters;
	}
	
	public abstract void init();
	
	public abstract void simulate();
	

	
}
