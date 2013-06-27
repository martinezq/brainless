package org.mysoft.brainless.body;

import org.mysoft.brainless.sensor.BodySensor;

public abstract class ComplexBody extends WorldObject {

	protected Bone masterBone;
	
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
	
	
}
