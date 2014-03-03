package org.mysoft.brainless.character;

import org.mysoft.brainless.body.ComplexBody;
import org.mysoft.brainless.brain.Brain;

public abstract class Character {

	protected ComplexBody body;
	protected Brain brain;

	public void init(ComplexBody body, Brain brain) {
		this.body = body;
		this.brain = brain;
		this.brain.attachTo(body);
	}
	
	public void activate() {
		brain.activate();		
	}
	
	public ComplexBody getBody() {
		return body;
	}
	
	public Brain getBrain() {
		return brain;
	}
	
	public void step() {
		brain.step();
	}
	
	public void reset() {
		brain.reset();
	}
	
	public abstract void beforeStep();
	
	public abstract void afterStep();
}
