package org.mysoft.playground;

import org.jbox2d.dynamics.World;

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
	
		Segment spineLower = BodyFactory.createSegment(0f, 7f, 0f, 8f);
		Segment spineMiddle = BodyFactory.createSegment(0f, 8f, 0f, 10f);
		Segment spineUpper = BodyFactory.createSegment(0f, 10f, 0f, 12f);
		Segment head = BodyFactory.createSegment(0f, 12f, 0f, 15f);
		
		spineLower.connectAtEnd(spineMiddle, Angles.d2r(-30), Angles.d2r(60));
		spineMiddle.connectAtEnd(spineUpper, Angles.d2r(-30), Angles.d2r(60));
		spineUpper.connectAtEnd(head,  Angles.d2r(-60), Angles.d2r(60));
		
		Segment leftLegUpper = BodyFactory.createSegment(0f, 7f, 0f, 4f);
		Segment leftLegLower = BodyFactory.createSegment(0f, 4f, 0f, 0f);
		Segment leftFeet = BodyFactory.createSegment(-1f, 0f, 0f, 0f);
		Segment leftFeet2 = BodyFactory.createSegment(-2f, 0f, -1f, 0f);
		
		spineLower.connectAtStart(leftLegUpper, Angles.d2r(-360-135), Angles.d2r(-300));
		leftLegUpper.connectAtEnd(leftLegLower, Angles.d2r(0), Angles.d2r(150));
		leftLegLower.connectAtEnd(leftFeet, Angles.d2r(135), Angles.d2r(210));
		leftFeet.connectAtStart(leftFeet2, Angles.d2r(-60), Angles.d2r(60));

		Segment rightLegUpper = BodyFactory.createSegment(0f, 7f, 0f, 4f);
		Segment rightLegLower = BodyFactory.createSegment(0f, 4f, 0f, 0f);
		Segment rightFeet = BodyFactory.createSegment(-1f, 0f, 0f, 0f);
		Segment rightFeet2 = BodyFactory.createSegment(-2f, 0f, -1f, 0f);
		
		spineLower.connectAtStart(rightLegUpper, Angles.d2r(-360-135), Angles.d2r(-300));
		rightLegUpper.connectAtEnd(rightLegLower, Angles.d2r(0), Angles.d2r(150));
		rightLegLower.connectAtEnd(rightFeet, Angles.d2r(135), Angles.d2r(210));
		rightFeet.connectAtStart(rightFeet2, Angles.d2r(-60), Angles.d2r(60));

		Segment leftHandUpper = BodyFactory.createSegment(0f, 12f, 0f, 9);
		Segment leftHandMiddle = BodyFactory.createSegment(0f, 9f, 0f, 6f);
		Segment leftHandLower = BodyFactory.createSegment(0f, 6f, 0f, 5f);
		
		leftHandUpper.connectAtStart(spineUpper, Angles.d2r(Float.NEGATIVE_INFINITY), Angles.d2r(Float.POSITIVE_INFINITY));
		leftHandMiddle.connectAtStart(leftHandUpper, Angles.d2r(0), Angles.d2r(150));
		leftHandLower.connectAtStart(leftHandMiddle, Angles.d2r(-90), Angles.d2r(90));
		
		Segment rightHandUpper = BodyFactory.createSegment(0f, 12f, 0f, 9);
		Segment rightHandMiddle = BodyFactory.createSegment(0f, 9f, 0f, 6f);
		Segment rightHandLower = BodyFactory.createSegment(0f, 6f, 0f, 5f);
		
		rightHandUpper.connectAtStart(spineUpper, Angles.d2r(Float.NEGATIVE_INFINITY), Angles.d2r(Float.POSITIVE_INFINITY));
		rightHandMiddle.connectAtStart(rightHandUpper, Angles.d2r(0), Angles.d2r(150));
		rightHandLower.connectAtStart(rightHandMiddle, Angles.d2r(-90), Angles.d2r(90));
		
	}



}
