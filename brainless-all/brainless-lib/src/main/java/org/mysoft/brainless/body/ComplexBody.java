package org.mysoft.brainless.body;

public abstract class ComplexBody extends WorldObject {

	protected Bone masterBone;
	
	abstract protected void initBones();
	
	abstract public BoneJoint[] getBodyJoints();
	
	public Bone getMasterBone() {
		return masterBone;
	}
	
	
}
