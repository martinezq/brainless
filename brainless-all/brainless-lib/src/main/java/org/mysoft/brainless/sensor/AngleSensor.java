package org.mysoft.brainless.sensor;

import org.mysoft.brainless.body.ComplexBody;

public class AngleSensor extends BodySensor {

	public static AngleSensor create(ComplexBody body) {
		AngleSensor s = new AngleSensor();
		s.setBody(body);
		return s;
	}
	
	@Override
	public double getValue() {
		return getBody().getMasterBone().getPhysicalBody().getAngle();
	}

}
