package org.mysoft.brainless.sensor;

import org.mysoft.brainless.body.ComplexBody;
import org.mysoft.brainless.neural.core.NeuronInput;

public abstract class BodySensor implements NeuronInput {

	protected ComplexBody body;
	
	public abstract double getValue();
	
	public void setBody(ComplexBody body) {
		this.body = body;
	}
	
	public ComplexBody getBody() {
		return body;
	}
	
	@Override
	public double calculatedOutput() {
		return getValue();
	}
	
}
