package org.mysoft.brainless.sensor;

public class XPositionSensor extends PositionSensor {

	@Override
	public double getValue() {
		return getPsychicalBodyPosition().x;
	}

}
