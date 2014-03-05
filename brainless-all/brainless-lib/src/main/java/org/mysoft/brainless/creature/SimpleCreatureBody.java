package org.mysoft.brainless.creature;

import java.util.LinkedList;
import java.util.List;

import org.jbox2d.dynamics.World;
import org.mysoft.brainless.body.BodyFactory;
import org.mysoft.brainless.body.Bone;
import org.mysoft.brainless.body.BoneJoint;
import org.mysoft.brainless.body.ComplexBody;
import org.mysoft.brainless.body.IEnergyMonitor;
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

public class SimpleCreatureBody extends ComplexBody {

	BodyFactory bodyFactory;
	
	Bone bone1;
	Bone bone2;
	Bone bone3;
	Bone bone4;
	Bone bone5;
	Bone bone6;
	Bone head;
	

	BoneJoint joint1;
	BoneJoint joint2;
	BoneJoint joint3;
	BoneJoint joint4;
	BoneJoint joint5;
	BoneJoint joint6;
	
	public final static SimpleCreatureBody create(World world) {
		SimpleCreatureBody human = new SimpleCreatureBody();
		human.setWorld(world);
		human.bodyFactory = BodyFactory.create(world);
		human.init();
		return human;
	}

	public Bone[] getBones() {
		Bone[] bones = new Bone[] {
				bone1, bone2, bone3,
				bone4, bone5, bone6, head	
		};
		
		return bones;
	}
	
	@Override
	public BoneJoint[] getBodyJoints() {
		BoneJoint[] joints = new BoneJoint[] {
				joint1, joint2, joint3, joint4, joint5, joint6
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
		
		initMaxForces();
		
		masterBone = bone1;
	}
	
	private void initSpineAndHead() {
		head = bodyFactory.createSegment(1f, 0f, 2f, 0f, Density.HIGH);
		
		bone1 = bodyFactory.createSegment(2f, 0f, 4f, 0f, Density.HIGH);
		bone2 = bodyFactory.createSegment(4f, 0f, 6f, 0f, Density.HIGH);
		bone3 = bodyFactory.createSegment(6f, 0f, 8f, 0f, Density.HIGH);
		bone4 = bodyFactory.createSegment(8f, 0f, 10f, 0f, Density.HIGH);
		bone5 = bodyFactory.createSegment(10f, 0f, 12f, 0f, Density.HIGH);
		bone6 = bodyFactory.createSegment(12f, 0f, 14f, 0f, Density.HIGH);
		
		
		joint1 = bone1.connectAtStart(head, Angles.d2r(-120), Angles.d2r(120));
		joint2 = bone2.connectAtStart(bone1, Angles.d2r(-120), Angles.d2r(120));
		joint3 = bone3.connectAtStart(bone2,  Angles.d2r(-120), Angles.d2r(120));
		joint4 = bone4.connectAtStart(bone3, Angles.d2r(-120), Angles.d2r(120));		
		joint5 = bone5.connectAtStart(bone4, Angles.d2r(-120), Angles.d2r(120));
		joint6 = bone6.connectAtStart(bone5, Angles.d2r(-120), Angles.d2r(120));
		
		joint1.setEnergyMonitor(this);
		joint2.setEnergyMonitor(this);
		joint3.setEnergyMonitor(this);
		joint4.setEnergyMonitor(this);
		joint5.setEnergyMonitor(this);
		joint6.setEnergyMonitor(this);

	}
	
	private void initMaxForces() {
		joint1.setMaxForce(Force.LOW); 
		joint2.setMaxForce(Force.AVG);
		joint3.setMaxForce(Force.AVG);
		joint4.setMaxForce(Force.AVG);
		joint5.setMaxForce(Force.AVG);
		joint6.setMaxForce(Force.AVG);
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
