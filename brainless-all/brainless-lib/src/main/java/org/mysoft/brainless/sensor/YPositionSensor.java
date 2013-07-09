package org.mysoft.brainless.sensor;

import org.mysoft.brainless.body.ComplexBody;

public class YPositionSensor extends BoneYPositionSensor {

	public static YPositionSensor create(ComplexBody body) {
		YPositionSensor s = new YPositionSensor();
		s.setBody(body);
		return s;
	}
	
	@Override
	public void setBody(ComplexBody body) {
		super.setBody(body);
		bone = body.getMasterBone();
	}
}
