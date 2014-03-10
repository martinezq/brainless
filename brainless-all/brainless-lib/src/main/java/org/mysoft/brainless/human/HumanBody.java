package org.mysoft.brainless.human;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.mysoft.brainless.body.BodyFactory;
import org.mysoft.brainless.body.Bone;
import org.mysoft.brainless.body.BoneJoint;
import org.mysoft.brainless.body.ComplexBody;
import org.mysoft.brainless.body.util.Angles;
import org.mysoft.brainless.body.util.Density;
import org.mysoft.brainless.body.util.Force;
import org.mysoft.brainless.sensor.AngleSensor;
import org.mysoft.brainless.sensor.AngularVelocitySensor;
import org.mysoft.brainless.sensor.BodySensor;
import org.mysoft.brainless.sensor.BoneContactSensor;
import org.mysoft.brainless.sensor.BoneXLinearVelocitySensor;
import org.mysoft.brainless.sensor.BoneXPositionSensor;
import org.mysoft.brainless.sensor.BoneYLinearVelocitySensor;
import org.mysoft.brainless.sensor.BoneYPositionSensor;
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
	
	
	BoneXPositionSensor leftLegXPosition;
	BoneYPositionSensor leftLegYPosition;
	BoneXPositionSensor rightLegXPosition;
	BoneYPositionSensor rightLegYPosition;

	BoneXPositionSensor leftHandXPosition;
	BoneYPositionSensor leftHandYPosition;
	BoneXPositionSensor rightHandXPosition;
	BoneYPositionSensor rightHandYPosition;
	
	BoneXPositionSensor headXPosition;
	BoneYPositionSensor headYPosition;
	
	BoneContactSensor leftLegContact;
	BoneContactSensor rightLegContact;
	
	BodySensor spineAngleSensor;
	BodySensor masterAngleSensor;

	AngularVelocitySensor spineAngularVelocitySensor;
	AngularVelocitySensor masterAngularVelocitySensor;
	
	public final static HumanBody create(World world) {
		HumanBody human = new HumanBody();
		human.setWorld(world);
		human.bodyFactory = BodyFactory.create(world);
		human.init();
		return human;
	}

	public Bone[] getBones() {
		Bone[] bones = new Bone[] {
				spineLower, spineMiddle, spineUpper,
				neck, head, 
				leftLegUpper, leftLegLower,	leftFeet, leftFeet2,
				rightLegUpper, rightLegLower, rightFeet, rightFeet2,
				leftHandUpper, leftHandMiddle, leftHandLower,
				rightHandUpper, rightHandMiddle, rightHandLower	
		};
		
		return bones;
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
		
		for(BoneJoint j: joints) {
			j.setEnergyMonitor(this);
		}
		
		return joints;
	}
	
	@Override
	public BodySensor[] getBodySensors() {
		Bone[] bones = getBones();
		
		List<BodySensor> sensors = new LinkedList<BodySensor>();
		
		for(Bone bone: bones) {
			BoneXPositionSensor x = BoneXPositionSensor.create(this, bone);
			BoneYPositionSensor y = BoneYPositionSensor.create(this, bone);
			AngleSensor a = AngleSensor.create(this, bone);
			AngularVelocitySensor v = AngularVelocitySensor.create(this, bone);
			BoneContactSensor c = BoneContactSensor.create(this, bone);
			BoneXLinearVelocitySensor lx = BoneXLinearVelocitySensor.create(this, bone);
			BoneYLinearVelocitySensor ly = BoneYLinearVelocitySensor.create(this, bone);
			
			//sensors.add(x);
			sensors.add(y);
			sensors.add(a);
			sensors.add(v);
			sensors.add(c);
			sensors.add(lx);
			sensors.add(ly);
		}
		
		return sensors.toArray(new BodySensor[sensors.size()]);
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
		spineLower = bodyFactory.createSegment(0f, 7f, 0f, 8f, Density.HIGH);
		spineMiddle = bodyFactory.createSegment(0f, 8f, 0f, 10f, Density.HIGH);
		spineUpper = bodyFactory.createSegment(0f, 10f, 0f, 12f, Density.HIGH);
		neck = bodyFactory.createSegment(0f, 12f, 0f, 12.5f, Density.LOW);
		head = bodyFactory.createSegment(0f, 12.5f, 0f, 14f, Density.HIGH);
		
		spineMiddleJoint = spineLower.connectAtEnd(spineMiddle, Angles.d2r(-30), Angles.d2r(60));
		spineUpperJoint = spineMiddle.connectAtEnd(spineUpper, Angles.d2r(-30), Angles.d2r(60));
		neckJoint = spineUpper.connectAtEnd(neck,  Angles.d2r(45), Angles.d2r(45));
		headJoint = neck.connectAtEnd(head, Angles.d2r(45), Angles.d2r(-45));
		

	}
	
	private void initLeftLeg() {
		leftLegUpper = bodyFactory.createSegment(0f, 7f, 0f, 4f, Density.HIGH);
		leftLegLower = bodyFactory.createSegment(0f, 4f, 1f, 0f, Density.HIGH);
		leftFeet = bodyFactory.createSegment(0f, 0f, 1f, 0f, Density.AVG);
		leftFeet2 = bodyFactory.createSegment(-1f, 0f, 0f, 0f, Density.AVG);
		
		leftLegUpperJoint = spineLower.connectAtStart(leftLegUpper, Angles.d2r(-360-135), Angles.d2r(-300));
		leftLegLowerJoint = leftLegUpper.connectAtEnd(leftLegLower, Angles.d2r(0), Angles.d2r(150));
		leftFeetJoint = leftLegLower.connectAtEnd(leftFeet, Angles.d2r(135), Angles.d2r(210));
		leftFeet2Joint = leftFeet.connectAtStart(leftFeet2, Angles.d2r(-60), Angles.d2r(60));
		
		//leftLegUpperJoint.applySpeed(10f);
	}
	
	private void initRightLeg() {
		rightLegUpper = bodyFactory.createSegment(0f, 7f, 0f, 4f, Density.HIGH);
		rightLegLower = bodyFactory.createSegment(0f, 4f, 1f, 0f, Density.HIGH);
		rightFeet = bodyFactory.createSegment(0f, 0f, 1f, 0f, Density.AVG);
		rightFeet2 = bodyFactory.createSegment(-1f, 0f, 0f, 0f, Density.AVG);
		
		rightLegUpperJoint = spineLower.connectAtStart(rightLegUpper, Angles.d2r(-360-135), Angles.d2r(-300));
		rightLegLowerJoint = rightLegUpper.connectAtEnd(rightLegLower, Angles.d2r(0), Angles.d2r(150));
		rightFeetJoint = rightLegLower.connectAtEnd(rightFeet, Angles.d2r(135), Angles.d2r(210));
		rightFeet2Joint = rightFeet.connectAtStart(rightFeet2, Angles.d2r(-60), Angles.d2r(60));
		
		//rightLegUpperJoint.applySpeed(-10f);
	}
	
	private void initLeftHand() {
		leftHandUpper = bodyFactory.createSegment(0f, 12f, 0f, 9, Density.AVG);
		leftHandMiddle = bodyFactory.createSegment(0f, 9f, 0f, 6f, Density.AVG);
		leftHandLower = bodyFactory.createSegment(0f, 6f, 0f, 5f, Density.LOW);
		
		leftHandUpperJoint = leftHandUpper.connectAtStart(spineUpper, Angles.d2r(Float.NEGATIVE_INFINITY), Angles.d2r(Float.POSITIVE_INFINITY));
		leftHandMiddleJoint = leftHandMiddle.connectAtStart(leftHandUpper, Angles.d2r(0), Angles.d2r(150));
		leftHandLowerJoint = leftHandLower.connectAtStart(leftHandMiddle, Angles.d2r(-90), Angles.d2r(90));
	}
	
	private void initRightHand() {
		rightHandUpper = bodyFactory.createSegment(0f, 12f, 0f, 9, Density.AVG);
		rightHandMiddle = bodyFactory.createSegment(0f, 9f, 0f, 6f, Density.AVG);
		rightHandLower = bodyFactory.createSegment(0f, 6f, 0f, 5f, Density.LOW);
		
		rightHandUpperJoint = rightHandUpper.connectAtStart(spineUpper, Angles.d2r(Float.NEGATIVE_INFINITY), Angles.d2r(Float.POSITIVE_INFINITY));
		rightHandMiddleJoint = rightHandMiddle.connectAtStart(rightHandUpper, Angles.d2r(0), Angles.d2r(150));
		rightHandLowerJoint = rightHandLower.connectAtStart(rightHandMiddle, Angles.d2r(-90), Angles.d2r(90));
	}
	
	private void initMaxForces() {
		headJoint.setMaxForce(Force.LOW);
		neckJoint.setMaxForce(Force.LOW);
		
		spineMiddleJoint.setMaxForce(Force.MAX); 
		spineUpperJoint.setMaxForce(Force.MAX);
		
		leftLegUpperJoint.setMaxForce(Force.MAX); 
		leftLegLowerJoint.setMaxForce(Force.MAX);
		leftFeetJoint.setMaxForce(Force.HIGH);
		leftFeet2Joint.setMaxForce(Force.AVG);
		
		rightLegUpperJoint.setMaxForce(Force.MAX);
		rightLegLowerJoint.setMaxForce(Force.MAX);
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
		xPositionSensor = XPositionSensor.create(this);
		yPositionSensor = YPositionSensor.create(this);
			
		leftLegXPosition = BoneXPositionSensor.create(this, leftFeet);
		leftLegYPosition = BoneYPositionSensor.create(this, leftFeet);

		rightLegXPosition = BoneXPositionSensor.create(this, rightFeet);
		rightLegYPosition = BoneYPositionSensor.create(this, rightFeet);

		leftHandXPosition = BoneXPositionSensor.create(this, leftHandLower);
		leftHandYPosition = BoneYPositionSensor.create(this, leftHandLower);

		rightHandXPosition = BoneXPositionSensor.create(this, rightHandLower);
		rightHandYPosition = BoneYPositionSensor.create(this, rightHandLower);

		
		headXPosition = BoneXPositionSensor.create(this, head);
		headYPosition = BoneYPositionSensor.create(this, head);
		
		leftLegContact = BoneContactSensor.create(this, leftFeet);
		rightLegContact = BoneContactSensor.create(this,  rightFeet);
		
		spineAngleSensor = AngleSensor.create(this, spineUpper);
		spineAngularVelocitySensor = AngularVelocitySensor.create(this, spineUpper);
		
		masterAngleSensor = AngleSensor.create(this);
		masterAngularVelocitySensor = AngularVelocitySensor.create(this);
	}
	
	public Bone getHead() {
		return head;
	}
	
	@Override
	public Bone getMasterBone() {
		return getHead();
	}

}
