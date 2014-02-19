package org.mysoft.brainless.sensor;

import org.mysoft.brainless.body.Bone;
import org.mysoft.brainless.body.ComplexBody;

public class BoneXLinearVelocitySensor extends BodySensor {

	Bone bone;
	
	public static BoneXLinearVelocitySensor create(ComplexBody body) {
		return create(body, body.getMasterBone());
	}
	
	public static BoneXLinearVelocitySensor create(ComplexBody body, Bone bone) {
		BoneXLinearVelocitySensor s = new BoneXLinearVelocitySensor();
		s.setBody(body);
		s.bone = bone;
		return s;
	}
	
	@Override
	public double getValue() {
		return bone.getPhysicalBody().getLinearVelocity().x;
	}

}
