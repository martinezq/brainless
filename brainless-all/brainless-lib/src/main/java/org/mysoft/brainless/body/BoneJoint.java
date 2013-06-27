package org.mysoft.brainless.body;

import org.jbox2d.dynamics.joints.RevoluteJoint;
import org.mysoft.brainless.body.util.Force;
import org.mysoft.brainless.neural.core.NetworkOutput;

public class BoneJoint implements NetworkOutput {


	protected RevoluteJoint revoluteJoint;
	protected double maxForce = Force.MAX;
	
	public final static BoneJoint create(RevoluteJoint revoluteJoint, double maxForce) {
		BoneJoint joint = new BoneJoint();
		joint.revoluteJoint = revoluteJoint;
		joint.maxForce = maxForce;
		return joint;
	}

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

	public void hold(double force) {
		revoluteJoint.setMotorSpeed(0);
		revoluteJoint.setMaxMotorTorque((float) force);
		revoluteJoint.enableMotor(true);
	}

	@Override
	public void perform(double value) {
		applyForce(2 * maxForce * value - maxForce);
	}
	
	public void setMaxForce(double maxForce) {
		this.maxForce = maxForce;
	}
}
