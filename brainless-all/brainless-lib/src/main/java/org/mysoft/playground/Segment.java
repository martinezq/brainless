package org.mysoft.playground;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Filter;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.Joint;
import org.jbox2d.dynamics.joints.RevoluteJoint;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

public class Segment {
	
	Body body;
	float length;
	World world;
	
	Vec2 startPoint;
	Vec2 endPoint;
	
	Joint parentJoint;
	
	Segment(World world, float x1, float y1, float x2, float y2) {
		this.world = world;
		
		startPoint = new Vec2(x1, y1);
		endPoint = new Vec2(x2, y2);

		length = MathUtils.distance(startPoint, endPoint);

		Vec2 sub = endPoint.sub(startPoint);
		Vec2 middle = startPoint.add(endPoint).mul(0.5f);
		
		float angle = -MathUtils.atan2(sub.x, sub.y);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(0.3f, length / 2);

		BodyDef boneDefinition = new BodyDef();
		boneDefinition.type = BodyType.DYNAMIC;
		boneDefinition.position.set(middle.x, middle.y);
		boneDefinition.angle = angle;
		boneDefinition.allowSleep = false;

		body = world.createBody(boneDefinition);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 20.f;
		fixtureDef.filter.groupIndex = -1;
		fixtureDef.friction = 1f;
		
		Fixture f = body.createFixture(fixtureDef);
		
		
	}
	
	public void connectAtEnd(Segment connectedSegment, float lowerAngle, float upperAngle) {
		connectAt(connectedSegment, endPoint, lowerAngle, upperAngle);
	}
	
	public void connectAtStart(Segment connectedSegment, float lowerAngle, float upperAngle) {
		connectAt(connectedSegment, startPoint, lowerAngle, upperAngle);
	}
	
	private void connectAt(Segment connectedSegment, Vec2 anchor, float lowerAngle, float upperAngle) {
		RevoluteJointDef jdef = new RevoluteJointDef();

		jdef.initialize(body, connectedSegment.body, anchor);

		jdef.collideConnected = false;
		jdef.enableLimit = true;
		jdef.lowerAngle = lowerAngle;
		jdef.upperAngle = upperAngle;

		parentJoint = world.createJoint(jdef);
		
		((RevoluteJoint)parentJoint).setMotorSpeed((float)(Math.random()*1f)-0.5f);
		((RevoluteJoint)parentJoint).setMaxMotorTorque(10000f);
		((RevoluteJoint)parentJoint).enableMotor(true);
		
	}
	
}
