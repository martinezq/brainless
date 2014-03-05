package org.mysoft.brainless.body;

import org.jbox2d.dynamics.joints.RevoluteJoint;
import org.mysoft.brainless.body.util.Force;
import org.mysoft.brainless.neural.core.NetworkOutput;

public class BoneJoint implements NetworkOutput {

	protected RevoluteJoint revoluteJoint;
	protected double maxForce = Force.MAX;

	protected double maxSpeed = 3.0;

	protected double energyLeft = 1000.0;
	
	protected IEnergyMonitor energyMonitor = null;

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

	public void applySpeed(double speed) {
		revoluteJoint.setMotorSpeed((float) speed);
		revoluteJoint.setMaxMotorTorque((float) maxForce);
		revoluteJoint.enableMotor(true);
	}

	public void applyTorque(double torque) {
		revoluteJoint.setMaxMotorTorque((float) (maxForce * Math.abs(torque)));
		revoluteJoint.setMotorSpeed((float) (maxSpeed * torque));
		revoluteJoint.enableMotor(true);
	}

	public void hold() {
		hold(maxForce);
	}

	public void hold(double force) {
		revoluteJoint.setMotorSpeed(0);
		revoluteJoint.setMaxMotorTorque((float) force);
		revoluteJoint.enableMotor(true);
	}

	@Override
	public void perform(double value) {

		double energy = Math.abs(revoluteJoint.m_motorImpulse);
		
		if(energyMonitor != null) {
			energyMonitor.energyUsed(energy);
		}

		if (energyLeft < 0) {
			hold(0.0);
		} else {

			if (value == 0.0) {
				hold();
			} else {
				applyTorque(value);
			}
		}

	}

	public void setMaxForce(double maxForce) {
		this.maxForce = maxForce;
	}

	public void setEnergyMonitor(IEnergyMonitor energyMonitor) {
		this.energyMonitor = energyMonitor;
	}

}
