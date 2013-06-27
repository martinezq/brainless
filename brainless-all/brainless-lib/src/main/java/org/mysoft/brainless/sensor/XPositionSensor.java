package org.mysoft.brainless.sensor;

public class XPositionSensor extends PositionSensor {

	@Override
	public float getValue() {
		return getPsychicalBodyPosition().x;
	}

}
