package org.mysoft.brainless.creature;

import java.util.LinkedList;
import java.util.List;

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
import org.mysoft.brainless.sensor.BoneXPositionSensor;
import org.mysoft.brainless.sensor.BoneYPositionSensor;

public class SimpleCreatureBody extends ComplexBody {

	BodyFactory bodyFactory;
	
	Bone spineLower;
	Bone spineMiddle;
	Bone spineUpper;
	Bone neck;
	Bone head;
	

	BoneJoint spineMiddleJoint;
	BoneJoint spineUpperJoint;
	BoneJoint neckJoint;
	BoneJoint headJoint;

	
	public final static SimpleCreatureBody create(World world) {
		SimpleCreatureBody human = new SimpleCreatureBody();
		human.setWorld(world);
		human.bodyFactory = BodyFactory.create(world);
		human.init();
		return human;
	}

	public Bone[] getBones() {
		Bone[] bones = new Bone[] {
				spineLower, spineMiddle, spineUpper,
				neck, head	
		};
		
		return bones;
	}
	
	@Override
	public BoneJoint[] getBodyJoints() {
		BoneJoint[] joints = new BoneJoint[] {
				spineMiddleJoint, spineUpperJoint, neckJoint, headJoint
		};
		
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
			
			sensors.add(x);
			sensors.add(y);
			sensors.add(a);
			sensors.add(v);
			sensors.add(c);
		}
		
		return sensors.toArray(new BodySensor[sensors.size()]);
	}
	
	@Override
	protected void initBones() {
	
		initSpineAndHead();
		
		initMaxForces();
		
		masterBone = spineLower;
	}
	
	private void initSpineAndHead() {
		spineLower = bodyFactory.createSegment(0f, 0f, 2f, 0f, Density.HIGH);
		spineMiddle = bodyFactory.createSegment(2f, 0f, 4f, 0f, Density.HIGH);
		spineUpper = bodyFactory.createSegment(4f, 0f, 6f, 0f, Density.HIGH);
		neck = bodyFactory.createSegment(6f, 0f, 8f, 0f, Density.LOW);
		head = bodyFactory.createSegment(8f, 0f, 10f, 0f, Density.HIGH);
		
		spineMiddleJoint = spineLower.connectAtEnd(spineMiddle, Angles.d2r(-120), Angles.d2r(120));
		spineUpperJoint = spineMiddle.connectAtEnd(spineUpper, Angles.d2r(-120), Angles.d2r(120));
		neckJoint = spineUpper.connectAtEnd(neck,  Angles.d2r(-120), Angles.d2r(120));
		headJoint = neck.connectAtEnd(head, Angles.d2r(-120), Angles.d2r(120));		

	}
	
	private void initMaxForces() {
		headJoint.setMaxForce(Force.MAX);
		neckJoint.setMaxForce(Force.MAX);
		
		spineMiddleJoint.setMaxForce(Force.MAX); 
		spineUpperJoint.setMaxForce(Force.MAX);
		
	}
	
	@Override
	protected void initSensors() {
	}
	
	public Bone getHead() {
		return head;
	}
	
	@Override
	public Bone getMasterBone() {
		return getHead();
	}

}
