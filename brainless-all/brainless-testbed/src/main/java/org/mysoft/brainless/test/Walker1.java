package org.mysoft.brainless.test;

import org.jbox2d.dynamics.World;
import org.mysoft.brainless.body.BodyFactory;
import org.mysoft.brainless.body.BodySegment;
import org.mysoft.brainless.body.util.Angles;

public class Walker1 {

	World world;

	public Walker1(World world) {
		this.world = world;
		init();
	}

	private void init() {
		initBones(world);
	}

	private void initBones(World world) {
	
		BodySegment spineLower = BodyFactory.createSegment(0f, 7f, 0f, 8f);
		BodySegment spineMiddle = BodyFactory.createSegment(0f, 8f, 0f, 10f);
		BodySegment spineUpper = BodyFactory.createSegment(0f, 10f, 0f, 12f);
		BodySegment head = BodyFactory.createSegment(0f, 12f, 0f, 15f);
		
		spineLower.connectAtEnd(spineMiddle, Angles.d2r(-30), Angles.d2r(60));
		spineMiddle.connectAtEnd(spineUpper, Angles.d2r(-30), Angles.d2r(60));
		spineUpper.connectAtEnd(head,  Angles.d2r(-60), Angles.d2r(60));
		
		BodySegment leftLegUpper = BodyFactory.createSegment(0f, 7f, 0f, 4f);
		BodySegment leftLegLower = BodyFactory.createSegment(0f, 4f, 0f, 0f);
		BodySegment leftFeet = BodyFactory.createSegment(-1f, 0f, 0f, 0f);
		BodySegment leftFeet2 = BodyFactory.createSegment(-2f, 0f, -1f, 0f);
		
		spineLower.connectAtStart(leftLegUpper, Angles.d2r(-360-135), Angles.d2r(-300));
		leftLegUpper.connectAtEnd(leftLegLower, Angles.d2r(0), Angles.d2r(150));
		leftLegLower.connectAtEnd(leftFeet, Angles.d2r(135), Angles.d2r(210));
		leftFeet.connectAtStart(leftFeet2, Angles.d2r(-60), Angles.d2r(60));

		BodySegment rightLegUpper = BodyFactory.createSegment(0f, 7f, 0f, 4f);
		BodySegment rightLegLower = BodyFactory.createSegment(0f, 4f, 0f, 0f);
		BodySegment rightFeet = BodyFactory.createSegment(-1f, 0f, 0f, 0f);
		BodySegment rightFeet2 = BodyFactory.createSegment(-2f, 0f, -1f, 0f);
		
		spineLower.connectAtStart(rightLegUpper, Angles.d2r(-360-135), Angles.d2r(-300));
		rightLegUpper.connectAtEnd(rightLegLower, Angles.d2r(0), Angles.d2r(150));
		rightLegLower.connectAtEnd(rightFeet, Angles.d2r(135), Angles.d2r(210));
		rightFeet.connectAtStart(rightFeet2, Angles.d2r(-60), Angles.d2r(60));

		BodySegment leftHandUpper = BodyFactory.createSegment(0f, 12f, 0f, 9);
		BodySegment leftHandMiddle = BodyFactory.createSegment(0f, 9f, 0f, 6f);
		BodySegment leftHandLower = BodyFactory.createSegment(0f, 6f, 0f, 5f);
		
		leftHandUpper.connectAtStart(spineUpper, Angles.d2r(Float.NEGATIVE_INFINITY), Angles.d2r(Float.POSITIVE_INFINITY));
		leftHandMiddle.connectAtStart(leftHandUpper, Angles.d2r(0), Angles.d2r(150));
		leftHandLower.connectAtStart(leftHandMiddle, Angles.d2r(-90), Angles.d2r(90));
		
		BodySegment rightHandUpper = BodyFactory.createSegment(0f, 12f, 0f, 9);
		BodySegment rightHandMiddle = BodyFactory.createSegment(0f, 9f, 0f, 6f);
		BodySegment rightHandLower = BodyFactory.createSegment(0f, 6f, 0f, 5f);
		
		rightHandUpper.connectAtStart(spineUpper, Angles.d2r(Float.NEGATIVE_INFINITY), Angles.d2r(Float.POSITIVE_INFINITY));
		rightHandMiddle.connectAtStart(rightHandUpper, Angles.d2r(0), Angles.d2r(150));
		rightHandLower.connectAtStart(rightHandMiddle, Angles.d2r(-90), Angles.d2r(90));
		
	}



}
