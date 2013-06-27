package org.mysoft.brainless.sensor;

import org.mysoft.brainless.body.ComplexBody;

public abstract class BodySensor {

	protected ComplexBody body;
	
	public abstract float getValue();
	
	public void setBody(ComplexBody body) {
		this.body = body;
	}
	
	public ComplexBody getBody() {
		return body;
	}
	
}
