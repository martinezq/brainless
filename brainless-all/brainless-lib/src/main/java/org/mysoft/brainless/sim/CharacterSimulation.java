package org.mysoft.brainless.sim;

import org.mysoft.brainless.character.Character;

public abstract class CharacterSimulation<T extends Character> extends WorldSimulation {

	protected T character;
	
	public T getCharacter() {
		return character;
	}
	
	@Override
	public void init() {
		super.init();
		this.character = initCharacter();
	}
	
	@Override
	public void beforeWorldStep() {
		character.getBrain().step();
		character.beforeStep();
	}
	
	@Override
	public void afterWorldStep() {
		character.afterStep();
	}
	
	public abstract T initCharacter();
	
}
