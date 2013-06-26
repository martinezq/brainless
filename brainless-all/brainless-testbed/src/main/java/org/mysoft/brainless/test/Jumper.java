package org.mysoft.brainless.test;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.PrismaticJointDef;

public class Jumper {

	World world;
	
	Body lowerBone;
	Body upperBone;

	public Jumper(World world) {
		this.world = world;
		init();
	}
	
	private void init() {
		initBones(world);
	}

	private void initBones(World world) {

		PolygonShape boneShape = new PolygonShape();
		boneShape.setAsBox(1, 3);
				
		BodyDef boneDefinition = new BodyDef();
		boneDefinition.type = BodyType.DYNAMIC;
		boneDefinition.position.set(0, 20);
		boneDefinition.allowSleep = false;
				
		lowerBone = world.createBody(boneDefinition);
		lowerBone.createFixture(boneShape, 20.0f);
		
		boneDefinition.position.set(0, 20);
		
		upperBone = world.createBody(boneDefinition);
		upperBone.createFixture(boneShape, 20.0f).setFriction(1f);
		
		PrismaticJointDef jointDefinition = new PrismaticJointDef();
		jointDefinition.bodyA = upperBone;
		jointDefinition.bodyB = lowerBone;
		jointDefinition.collideConnected = false;
		jointDefinition.localAxis1.set(0, 1).normalize();
		jointDefinition.enableLimit = true;
		jointDefinition.lowerTranslation = 1;
		jointDefinition.upperTranslation = 5;
		
		world.createJoint(jointDefinition);
		
		lowerBone.applyForce(new Vec2(0, 300000f), new Vec2(0,0));
		upperBone.applyForce(new Vec2(0, -300000f), new Vec2(0,0));

	}

}
