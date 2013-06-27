package org.mysoft.brainless.sensor;

public class AngleSensor extends BodySensor {

	@Override
	public double getValue() {
		return getBody().getMasterBone().getPhysicalBody().getAngle();
	}

}
