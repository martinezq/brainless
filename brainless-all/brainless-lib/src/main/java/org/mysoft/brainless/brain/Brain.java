package org.mysoft.brainless.brain;

import org.mysoft.brainless.body.ComplexBody;

public abstract class Brain {

	protected ComplexBody body;
	
	public abstract void activate();
	
	public void attachTo(ComplexBody complexBody) {
		this.body = complexBody;
	}
	
	public abstract void update();
}
