package org.mysoft.brainless.sensor;

import org.mysoft.brainless.body.Bone;
import org.mysoft.brainless.body.ComplexBody;

public class AngleSensor extends BodySensor {

	Bone bone;
	
	public static AngleSensor create(ComplexBody body) {
		return create(body, body.getMasterBone());
	}
	
	public static AngleSensor create(ComplexBody body, Bone bone) {
		AngleSensor s = new AngleSensor();
		s.setBody(body);
		s.bone = bone;
		return s;
	}
	
	@Override
	public double getValue() {
		return bone.getPhysicalBody().getAngle();
	}

}
