package org.mysoft.brainless.sensor;

import org.jbox2d.common.Vec2;

public abstract class PositionSensor extends BodySensor {

	protected Vec2 getPsychicalBodyPosition() {
		return body.getMasterBone().getPhysicalBody().getPosition();
	}

}
