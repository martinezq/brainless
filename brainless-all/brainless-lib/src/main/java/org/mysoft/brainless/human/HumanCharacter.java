package org.mysoft.brainless.human;

import org.mysoft.brainless.character.Character;

public class HumanCharacter extends Character {

	public static HumanCharacter create(HumanBody body, HumanBrain brain) {
		HumanCharacter character = new HumanCharacter();
		character.init(body, brain);
		return character;
	}

}
