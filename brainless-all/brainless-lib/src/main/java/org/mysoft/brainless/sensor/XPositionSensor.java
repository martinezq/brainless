package org.mysoft.brainless.sensor;

import org.mysoft.brainless.body.ComplexBody;

public class XPositionSensor extends BoneXPositionSensor {

	public static XPositionSensor create(ComplexBody body) {
		XPositionSensor s = new XPositionSensor();
		s.setBody(body);
		return s;
	}
	
	@Override
	public void setBody(ComplexBody body) {
		super.setBody(body);
		bone = body.getMasterBone();
	}




}
