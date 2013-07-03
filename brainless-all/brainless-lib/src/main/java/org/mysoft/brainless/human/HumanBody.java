package org.mysoft.brainless.human;

import org.jbox2d.dynamics.World;
import org.mysoft.brainless.body.BodyFactory;
import org.mysoft.brainless.body.Bone;
import org.mysoft.brainless.body.BoneJoint;
import org.mysoft.brainless.body.ComplexBody;
import org.mysoft.brainless.body.util.Angles;
import org.mysoft.brainless.body.util.Force;
import org.mysoft.brainless.sensor.AngleSensor;
import org.mysoft.brainless.sensor.BodySensor;
import org.mysoft.brainless.sensor.XPositionSensor;
import org.mysoft.brainless.sensor.YPositionSensor;

public class HumanBody extends ComplexBody {

	BodyFactory bodyFactory;
	
	Bone spineLower;
	Bone spineMiddle;
	Bone spineUpper;
	Bone neck;
	Bone head;
	
	Bone leftLegUpper;
	Bone leftLegLower;
	Bone leftFeet;
	Bone leftFeet2;
	
	Bone rightLegUpper;
	Bone rightLegLower;
	Bone rightFeet;
	Bone rightFeet2;
	
	Bone leftHandUpper;
	Bone leftHandMiddle;
	Bone leftHandLower;
	
	Bone rightHandUpper;
	Bone rightHandMiddle;
	Bone rightHandLower;

	BoneJoint spineMiddleJoint;
	BoneJoint spineUpperJoint;
	BoneJoint neckJoint;
	BoneJoint headJoint;

	BoneJoint leftLegUpperJoint;
	BoneJoint leftLegLowerJoint;
	BoneJoint leftFeetJoint;
	BoneJoint leftFeet2Joint;	
	
	BoneJoint rightLegUpperJoint;
	BoneJoint rightLegLowerJoint;
	BoneJoint rightFeetJoint;
	BoneJoint rightFeet2Joint;	
	
	BoneJoint leftHandUpperJoint;
	BoneJoint leftHandMiddleJoint;
	BoneJoint leftHandLowerJoint;
	
	BoneJoint rightHandUpperJoint;
	BoneJoint rightHandMiddleJoint;
	BoneJoint rightHandLowerJoint;	
	
	BodySensor xPositionSensor;
	BodySensor yPositionSensor;
	BodySensor angleSensor;
	
	public final static HumanBody create(World world) {
		HumanBody human = new HumanBody();
		human.setWorld(world);
		human.bodyFactory = BodyFactory.create(world);
		human.init();
		return human;
	}

	@Override
	public BoneJoint[] getBodyJoints() {
		BoneJoint[] joints = new BoneJoint[] {
				spineMiddleJoint, spineUpperJoint, neckJoint, headJoint,
				leftLegUpperJoint, leftLegLowerJoint, leftFeetJoint, leftFeet2Joint,
				rightLegUpperJoint, rightLegLowerJoint, rightFeetJoint, rightFeet2Joint,
				leftHandUpperJoint, leftHandMiddleJoint, leftHandLowerJoint,
				rightHandUpperJoint, rightHandMiddleJoint, rightHandLowerJoint
		};
		
		return joints;
	}
	
	@Override
	public BodySensor[] getBodySensors() {
		BodySensor[] sensors = new BodySensor[] {
				angleSensor, xPositionSensor, yPositionSensor 
		};
		
		return sensors;
	}
	
	@Override
	protected void initBones() {
	
		initSpineAndHead();
		
		initLeftLeg();
		initRightLeg();
		
		initLeftHand();
		initRightHand();
		
		initMaxForces();
		
		masterBone = spineLower;
	}
	
	private void initSpineAndHead() {
		spineLower = bodyFactory.createSegment(0f, 7f, 0f, 8f);
		spineMiddle = bodyFactory.createSegment(0f, 8f, 0f, 10f);
		spineUpper = bodyFactory.createSegment(0f, 10f, 0f, 12f);
		neck = bodyFactory.createSegment(0f, 12f, 0f, 12.5f);
		head = bodyFactory.createSegment(0f, 12.5f, 0f, 14f);
		
		spineMiddleJoint = spineLower.connectAtEnd(spineMiddle, Angles.d2r(-30), Angles.d2r(60));
		spineUpperJoint = spineMiddle.connectAtEnd(spineUpper, Angles.d2r(-30), Angles.d2r(60));
		neckJoint = spineUpper.connectAtEnd(neck,  Angles.d2r(45), Angles.d2r(45));
		headJoint = neck.connectAtEnd(head, Angles.d2r(45), Angles.d2r(-45));
		

	}
	
	private void initLeftLeg() {
		leftLegUpper = bodyFactory.createSegment(0f, 7f, 0f, 4f);
		leftLegLower = bodyFactory.createSegment(0f, 4f, 0f, 0f);
		leftFeet = bodyFactory.createSegment(-1f, 0f, 0f, 0f);
		leftFeet2 = bodyFactory.createSegment(-2f, 0f, -1f, 0f);
		
		leftLegUpperJoint = spineLower.connectAtStart(leftLegUpper, Angles.d2r(-360-135), Angles.d2r(-300));
		leftLegLowerJoint = leftLegUpper.connectAtEnd(leftLegLower, Angles.d2r(0), Angles.d2r(150));
		leftFeetJoint = leftLegLower.connectAtEnd(leftFeet, Angles.d2r(135), Angles.d2r(210));
		leftFeet2Joint = leftFeet.connectAtStart(leftFeet2, Angles.d2r(-60), Angles.d2r(60));
	}
	
	private void initRightLeg() {
		rightLegUpper = bodyFactory.createSegment(0f, 7f, 0f, 4f);
		rightLegLower = bodyFactory.createSegment(0f, 4f, 0f, 0f);
		rightFeet = bodyFactory.createSegment(-1f, 0f, 0f, 0f);
		rightFeet2 = bodyFactory.createSegment(-2f, 0f, -1f, 0f);
		
		rightLegUpperJoint = spineLower.connectAtStart(rightLegUpper, Angles.d2r(-360-135), Angles.d2r(-300));
		rightLegLowerJoint = rightLegUpper.connectAtEnd(rightLegLower, Angles.d2r(0), Angles.d2r(150));
		rightFeetJoint = rightLegLower.connectAtEnd(rightFeet, Angles.d2r(135), Angles.d2r(210));
		rightFeet2Joint = rightFeet.connectAtStart(rightFeet2, Angles.d2r(-60), Angles.d2r(60));
	}
	
	private void initLeftHand() {
		leftHandUpper = bodyFactory.createSegment(0f, 12f, 0f, 9);
		leftHandMiddle = bodyFactory.createSegment(0f, 9f, 0f, 6f);
		leftHandLower = bodyFactory.createSegment(0f, 6f, 0f, 5f);
		
		leftHandUpperJoint = leftHandUpper.connectAtStart(spineUpper, Angles.d2r(Float.NEGATIVE_INFINITY), Angles.d2r(Float.POSITIVE_INFINITY));
		leftHandMiddleJoint = leftHandMiddle.connectAtStart(leftHandUpper, Angles.d2r(0), Angles.d2r(150));
		leftHandLowerJoint = leftHandLower.connectAtStart(leftHandMiddle, Angles.d2r(-90), Angles.d2r(90));
	}
	
	private void initRightHand() {
		rightHandUpper = bodyFactory.createSegment(0f, 12f, 0f, 9);
		rightHandMiddle = bodyFactory.createSegment(0f, 9f, 0f, 6f);
		rightHandLower = bodyFactory.createSegment(0f, 6f, 0f, 5f);
		
		rightHandUpperJoint = rightHandUpper.connectAtStart(spineUpper, Angles.d2r(Float.NEGATIVE_INFINITY), Angles.d2r(Float.POSITIVE_INFINITY));
		rightHandMiddleJoint = rightHandMiddle.connectAtStart(rightHandUpper, Angles.d2r(0), Angles.d2r(150));
		rightHandLowerJoint = rightHandLower.connectAtStart(rightHandMiddle, Angles.d2r(-90), Angles.d2r(90));
	}
	
	private void initMaxForces() {
		headJoint.setMaxForce(Force.LOW);
		neckJoint.setMaxForce(Force.LOW);
		
		spineMiddleJoint.setMaxForce(Force.HIGH); 
		spineUpperJoint.setMaxForce(Force.HIGH);
		
		leftLegUpperJoint.setMaxForce(Force.HIGH); 
		leftLegLowerJoint.setMaxForce(Force.HIGH);
		leftFeetJoint.setMaxForce(Force.HIGH);
		leftFeet2Joint.setMaxForce(Force.AVG);
		
		rightLegUpperJoint.setMaxForce(Force.HIGH);
		rightLegLowerJoint.setMaxForce(Force.HIGH);
		rightFeetJoint.setMaxForce(Force.HIGH);
		rightFeet2Joint.setMaxForce(Force.AVG);
		
		leftHandUpperJoint.setMaxForce(Force.AVG);
		leftHandMiddleJoint.setMaxForce(Force.AVG);
		leftHandLowerJoint.setMaxForce(Force.LOW);
		
		rightHandUpperJoint.setMaxForce(Force.AVG);
		rightHandMiddleJoint.setMaxForce(Force.AVG);
		rightHandLowerJoint.setMaxForce(Force.LOW);
	}
	
	@Override
	protected void initSensors() {
		xPositionSensor = new XPositionSensor();
		xPositionSensor.setBody(this);
		
		yPositionSensor = new YPositionSensor();
		yPositionSensor.setBody(this);
		
		angleSensor = new AngleSensor();
		angleSensor.setBody(this);
		
	}

}
