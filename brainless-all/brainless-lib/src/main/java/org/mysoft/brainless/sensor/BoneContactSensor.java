package org.mysoft.brainless.sensor;

import org.jbox2d.dynamics.BodyType;
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
			boolean static1 = bone.getPhysicalBody().getContactList().contact.getFixtureA().getBody().getType() == BodyType.STATIC;
			boolean static2 = bone.getPhysicalBody().getContactList().contact.getFixtureB().getBody().getType() == BodyType.STATIC;
			
			if(static1 || static2) {
				return 1.0;
			} else {
				return -1.0;
			}
			
		} else {
			return 0.0;
		}
	}

}
