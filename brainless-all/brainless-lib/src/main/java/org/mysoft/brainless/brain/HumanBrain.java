package org.mysoft.brainless.brain;

import org.mysoft.brainless.body.BoneJoint;
import org.mysoft.brainless.sensor.BodySensor;

public class HumanBrain extends Brain {

	@Override
	public void activate() {
		freeze();
	}

	@Override
	public void update() {
		BodySensor sensor = body.getBodySensors()[0];

		System.out.println(sensor.getValue());

		if (sensor.getValue() < -0.3f) {

			for (BoneJoint joint : body.getBodyJoints()) {
				joint.removeForce();
			}

		}

	}

	public void freeze() {
		for (BoneJoint joint : body.getBodyJoints()) {
			joint.hold(1000000f);
		}

	}

}
