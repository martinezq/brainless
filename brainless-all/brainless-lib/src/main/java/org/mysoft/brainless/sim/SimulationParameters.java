package org.mysoft.brainless.sim;

public class SimulationParameters {
	
	public final static int FRAMERATE = 30;
	
	private int hz = 15;
	private int durationSeconds = 5;
	private int iterations = -1;
	private float step = -1;
	private int posIterations = 64;
	private int velIterations = 64;
	
	public void setHz(int hz) {
		this.hz = hz;
		calculate();
	}
	
	public void setDurationSeconds(int durationSeconds) {
		this.durationSeconds = durationSeconds;
		calculate();
	}
	
	public void setPosIterations(int posIterations) {
		this.posIterations = posIterations;
	}
	
	public void setVelIterations(int velIterations) {
		this.velIterations = velIterations;
	}
	
	public int getHz() {
		return hz;
	}
	
	public int getDurationSeconds() {
		return durationSeconds;
	}
	
	public int getIterations() {
		return iterations;
	}

	public int getPosIterations() {
		return posIterations;
	}
	
	public int getVelIterations() {
		return velIterations;
	}
	
	public float getStep() {
		return step;
	}
	
	private void calculate() {
		this.iterations = durationSeconds * FRAMERATE;
		this.step = 1.0f / this.hz;
	}
}
