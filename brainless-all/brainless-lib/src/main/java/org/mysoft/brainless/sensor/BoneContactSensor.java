package org.mysoft.brainless.sensor;

import org.mysoft.brainless.body.Bone;
import org.mysoft.brainless.body.ComplexBody;

public class BoneContactSensor extends BodySensor {

	Bone bone;
	
	public static final BoneContactSensor create(ComplexBody body, Bone bone) {
		BoneContactSensor s = new BoneContactSensor();
		s.setBody(body);
		s.bone = bone;
		return s;
	}
	
	@Override
	public double getValue() {
		if(bone.getPhysicalBody().getContactList() != null) {
			return 1.0;
		} else {
			return 0.0;
		}
	}

}
