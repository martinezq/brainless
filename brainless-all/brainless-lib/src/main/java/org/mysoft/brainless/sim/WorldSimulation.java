package org.mysoft.brainless.sim;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

public class WorldSimulation extends Simulation {

	protected World world;
	
	@Override
	public void init() {
		if(world == null) {
			world = createWorld();
		}
		initWorld();
	}

	public void beforeWorldStep() {
		
	}

	public void afterWorldStep() {
		
	}

	
	@Override
	public void simulate() {
		int iterations = parameters.getIterations();
		float step = parameters.getStep();
		int velIterations = parameters.getVelIterations();
		int posIterations = parameters.getPosIterations();
		
		for(int i=0; i<iterations; i++) {
			beforeWorldStep();
			world.step(step, velIterations, posIterations);
			afterWorldStep();
		}
	}
	
	protected World createWorld() {
		final float earthGravity = (-9.8f);
		final Vec2 gravity = new Vec2(0, earthGravity);
		final boolean allowSleep = false;
		
		World world = new World(gravity, allowSleep);
		
		return world;
	}
	
	public void initWorld() {
		
		final float earthGravity = -9.8f;
		final Vec2 gravity = new Vec2(0, earthGravity);
		final boolean allowSleep = false;
		
		world.setGravity(gravity);
		world.setAllowSleep(allowSleep);
		
		PolygonShape groundShape = new PolygonShape();
		groundShape.setAsEdge(new Vec2(-1000,  -1), new Vec2(1000, -1));
		
		BodyDef groundDefinition = new BodyDef();
		groundDefinition.type = BodyType.STATIC;
		groundDefinition.position.set(0, 0);
		groundDefinition.allowSleep = false;
		
		Body groundBody = world.createBody(groundDefinition);
		groundBody.createFixture(groundShape, 5.0f).setFriction(5f);
	/*	
		PolygonShape groundShape2 = new PolygonShape();
		groundShape2.setAsBox(1f, 1f);
		
		BodyDef groundDefinition2 = new BodyDef();
		groundDefinition2.type = BodyType.STATIC;
		groundDefinition2.position.set(30f, 1f);
		groundDefinition2.allowSleep = false;
		
		Body groundBody2 = world.createBody(groundDefinition2);
		groundBody2.createFixture(groundShape2, 5.0f).setFriction(5f);
	*/	
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public World getWorld() {
		return world;
	}

}
