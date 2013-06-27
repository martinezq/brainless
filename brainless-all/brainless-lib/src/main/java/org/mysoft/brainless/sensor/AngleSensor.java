package org.mysoft.brainless.sensor;

public class AngleSensor extends BodySensor {

	@Override
	public float getValue() {
		return getBody().getMasterBone().getPhysicalBody().getAngle();
	}

}
