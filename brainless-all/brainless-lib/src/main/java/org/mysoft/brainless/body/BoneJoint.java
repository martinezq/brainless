package org.mysoft.brainless.body;

import org.jbox2d.dynamics.joints.RevoluteJoint;

public class BoneJoint {

	protected RevoluteJoint revoluteJoint;
	
	public final static BoneJoint create(RevoluteJoint revoluteJoint) {
		BoneJoint joint = new BoneJoint();
		joint.revoluteJoint = revoluteJoint;
		return joint;
	}
	
	public RevoluteJoint getRevoluteJoint() {
		return revoluteJoint;
	}
	
	public void applyForce(float force) {
		revoluteJoint.setMotorSpeed(Math.signum(force));
		revoluteJoint.setMaxMotorTorque(force);
		revoluteJoint.enableMotor(true);
	}
	
	public void removeForce() {
		revoluteJoint.enableMotor(false);
	}
	
	public void hold(float force) {
		revoluteJoint.setMotorSpeed(0);
		revoluteJoint.setMaxMotorTorque(force);
		revoluteJoint.enableMotor(true);
	}
}
