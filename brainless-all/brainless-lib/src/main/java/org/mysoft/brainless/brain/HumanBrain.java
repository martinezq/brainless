package org.mysoft.brainless.brain;

import org.mysoft.brainless.body.BoneJoint;

public class HumanBrain extends Brain {

	@Override
	public void activate() {
		freeze();
	}
	
	public void freeze() {
		for(BoneJoint joint: body.getBodyJoints()) {
			joint.hold(1000f);
		}
		
	}
	
}
