package org.mysoft.brainless.sensor;

import org.mysoft.brainless.body.Bone;
import org.mysoft.brainless.body.ComplexBody;

public class BoneYPositionSensor extends BodySensor {

	Bone bone;
	
	public static BoneYPositionSensor create(ComplexBody body, Bone bone) {
		BoneYPositionSensor s = new BoneYPositionSensor();
		s.setBody(body);
		s.bone = bone;
		return s;
	}
	
	@Override
	public double getValue() {
		double yPos= body.getMasterBone().getPhysicalBody().getPosition().y;
		double boneYPos = bone.getPhysicalBody().getPosition().y;
		return boneYPos - yPos;
	}

}
