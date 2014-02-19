package org.mysoft.brainless.sensor;

import org.mysoft.brainless.body.Bone;
import org.mysoft.brainless.body.ComplexBody;

public class BoneYLinearVelocitySensor extends BodySensor {

	Bone bone;
	
	public static BoneYLinearVelocitySensor create(ComplexBody body) {
		return create(body, body.getMasterBone());
	}
	
	public static BoneYLinearVelocitySensor create(ComplexBody body, Bone bone) {
		BoneYLinearVelocitySensor s = new BoneYLinearVelocitySensor();
		s.setBody(body);
		s.bone = bone;
		return s;
	}
	
	@Override
	public double getValue() {
		return bone.getPhysicalBody().getLinearVelocity().y;
	}

}
