package org.mysoft.brainless.sensor;

import org.mysoft.brainless.body.Bone;
import org.mysoft.brainless.body.ComplexBody;

public class BoneXPositionSensor extends BodySensor {

	Bone bone;
	
	public static BoneXPositionSensor create(ComplexBody body, Bone bone) {
		BoneXPositionSensor s = new BoneXPositionSensor();
		s.setBody(body);
		s.bone = bone;
		return s;
	}
	
	@Override
	public double getValue() {
		double xPos= body.getMasterBone().getPhysicalBody().getPosition().x;
		double boneXPos = bone.getPhysicalBody().getPosition().x;
		return boneXPos - xPos;
	}

}
