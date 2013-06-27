package org.mysoft.brainless.sensor;

public class YPositionSensor extends PositionSensor {

	@Override
	public float getValue() {
		return getPsychicalBodyPosition().y;
	}

}
