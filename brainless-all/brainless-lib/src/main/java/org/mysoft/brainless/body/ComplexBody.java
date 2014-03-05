package org.mysoft.brainless.body;

import org.mysoft.brainless.sensor.BodySensor;

public abstract class ComplexBody extends WorldObject implements IEnergyMonitor{

	protected Bone masterBone;

	protected double energyUsed = 0;
	
	public void init() {
		initBones();
		initSensors();
	}
	
	abstract protected void initBones();
	
	abstract protected void initSensors();
	
	abstract public BoneJoint[] getBodyJoints();
	
	abstract public BodySensor[] getBodySensors();
	
	public Bone getMasterBone() {
		return masterBone;
	}
	
	@Override
	public void energyUsed(double energy) {
		this.energyUsed += energy;
	}
	
	public double getEnergyUsed() {
		return energyUsed;
	}
	
}
