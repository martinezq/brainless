package org.mysoft.brainless.sensor;

public class YPositionSensor extends PositionSensor {

	@Override
	public double getValue() {
		return getPsychicalBodyPosition().y;
	}

}
