package org.mysoft.brainless.body;

import org.jbox2d.dynamics.joints.RevoluteJoint;
import org.mysoft.brainless.neural.core.NetworkOutput;

public class BoneJoint implements NetworkOutput {

	public final double FORCE_MAX = 1000;
	
	protected RevoluteJoint revoluteJoint;
	
	public final static BoneJoint create(RevoluteJoint revoluteJoint) {
		BoneJoint joint = new BoneJoint();
		joint.revoluteJoint = revoluteJoint;
		return joint;
	}
	
	public RevoluteJoint getRevoluteJoint() {
		return revoluteJoint;
	}
	
	public void applyForce(double force) {
		revoluteJoint.setMotorSpeed((float) Math.signum(force));
		revoluteJoint.setMaxMotorTorque((float) force);
		revoluteJoint.enableMotor(true);
	}
	
	public void removeForce() {
		hold(10f);
	}
	
	public void hold(float force) {
		revoluteJoint.setMotorSpeed(0);
		revoluteJoint.setMaxMotorTorque(force);
		revoluteJoint.enableMotor(true);
	}
	
	@Override
	public void perform(double value) {
		applyForce(2 * FORCE_MAX * value - FORCE_MAX / 2);
	}
}
