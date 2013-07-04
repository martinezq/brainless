package org.mysoft.brainless.sim;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;
import org.mysoft.brainless.human.HumanBody;
import org.mysoft.brainless.human.HumanBrain;
import org.mysoft.brainless.human.HumanCharacter;
import org.mysoft.brainless.neural.core.NeuralNetwork;

public class DefaultSimulation extends Simulation<NeuralNetwork> {

	final static float DEFAULT_STEP = 1.0f / 30.0f;
	final static float SIMULATION_SECONDS = 4.0f;
	
	World world;
	HumanCharacter character;
	
	public final static DefaultSimulation create() {
		return new DefaultSimulation();
	}
	
	@Override
	public void simulate() {
		character.activate();
		
		int iterations = (int)(SIMULATION_SECONDS / DEFAULT_STEP);
		
		for(int i=0; i<iterations; i++) {
			character.getBrain().step();
			character.storePosition();
			world.step(DEFAULT_STEP, 3, 8);
			character.calculateDeltas();
			/*
			if(character.getSummaryDelta() > 500) {
				character.setSummaryDelta(character.getSummaryDelta() * 100);
				break;
			}
			*/
		}
	}

	@Override
	public void init(NeuralNetwork neuralNetwork) {
		world = createWorld();
		character = initActor(world, neuralNetwork);
	}

	public World createWorld() {
		final float earthGravity = -9.8f;
		final Vec2 gravity = new Vec2(0, earthGravity);
		final boolean allowSleep = false;
		
		World world = new World(gravity, allowSleep);
		
		initWorld(world);
		
		return world;
	}
	
	public void initWorld(World world) {
		
		final float earthGravity = -9.8f;
		final Vec2 gravity = new Vec2(0, earthGravity);
		final boolean allowSleep = false;
		
		world.setGravity(gravity);
		world.setAllowSleep(allowSleep);
		
		PolygonShape groundShape = new PolygonShape();
		groundShape.setAsEdge(new Vec2(-100,  -1), new Vec2(100, -1));
		
		BodyDef groundDefinition = new BodyDef();
		groundDefinition.type = BodyType.STATIC;
		groundDefinition.position.set(0, 0);
		groundDefinition.allowSleep = false;
		
		Body groundBody = world.createBody(groundDefinition);
		groundBody.createFixture(groundShape, 5.0f).setFriction(5f);
		
	}
	
	public HumanCharacter initActor(World world, NeuralNetwork neuralNetwork) {
		HumanBody body = HumanBody.create(world);
		HumanBrain brain = HumanBrain.create(neuralNetwork);
		HumanCharacter character = HumanCharacter.create(body, brain);
		
		return character;
	}
	
	public HumanCharacter getCharacter() {
		return character;
	}
	
	public World getWorld() {
		return world;
	}
}
