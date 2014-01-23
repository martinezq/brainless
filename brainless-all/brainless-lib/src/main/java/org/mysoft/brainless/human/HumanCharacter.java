package org.mysoft.brainless.human;

import org.mysoft.brainless.character.Character;

public class HumanCharacter extends Character {

	double deltaX = 0;
	double deltaY = 0;
	double deltaAngle = 0;
	
	double minY = Double.MAX_VALUE;
	
	double positionX = 0;
	double positionY = 0;
	double angle = 0;
	
	public static HumanCharacter create(HumanBody body, HumanBrain brain) {
		HumanCharacter character = new HumanCharacter();
		character.init(body, brain);
		return character;
	}

	public void storePosition() {
		positionX = body.getMasterBone().getPhysicalBody().getPosition().x;
		positionY = body.getMasterBone().getPhysicalBody().getPosition().y;
		angle = body.getMasterBone().getPhysicalBody().getAngle();
		
		if(positionY < minY) {
			minY = positionY;
		}
		
	}

	public void calculateDeltas() {
		double actPositionX = body.getMasterBone().getPhysicalBody().getPosition().x;
		double actPositionY = body.getMasterBone().getPhysicalBody().getPosition().y;
		double actAngle = body.getMasterBone().getPhysicalBody().getAngle();
		
		deltaX += Math.abs(actPositionX - positionX);
		deltaY += Math.abs(actPositionY - positionY);
		deltaAngle += 10.0 * Math.abs(actAngle - angle);
		
	}
	
	public double getSummaryDelta() {
		return deltaX + deltaY + 100.0 * deltaAngle;
	}

	public double getDeltaY() {
		return deltaY;
	}

	public double getDeltaX() {
		return deltaX;
	}

	
	public double getDeltaAngle() {
		return deltaAngle;
	}
	
	public void setSummaryDelta(double value) {
		deltaX = value;
	}
	
	public double getMinY() {
		return minY;
	}

}
