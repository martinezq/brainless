package org.mysoft.brainless.sensor;

import org.mysoft.brainless.body.Bone;
import org.mysoft.brainless.body.ComplexBody;

public class AngularVelocitySensor extends BodySensor {

	Bone bone;
	
	public static AngularVelocitySensor create(ComplexBody body) {
		return create(body, body.getMasterBone());
	}
	
	public static AngularVelocitySensor create(ComplexBody body, Bone bone) {
		AngularVelocitySensor s = new AngularVelocitySensor();
		s.setBody(body);
		s.bone = bone;
		return s;
	}
	
	@Override
	public double getValue() {
		return bone.getPhysicalBody().getAngularVelocity();
	}

}
