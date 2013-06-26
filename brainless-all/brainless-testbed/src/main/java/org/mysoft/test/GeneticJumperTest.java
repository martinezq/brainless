package org.mysoft.test;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.framework.TestbedTest;
import org.mysoft.brainless.body.BodyFactory;
import org.mysoft.brainless.body.HumanBody;
import org.mysoft.brainless.brain.Brain;
import org.mysoft.brainless.brain.HumanBrain;
import org.mysoft.brainless.character.HumanCharacter;

public class GeneticJumperTest extends TestbedTest {

	@Override
	public void initTest(boolean argDeserialized) {
		setTitle(getTestName());
		BodyFactory.init(getWorld());
		
		initWorld();
		initActor();
	
	}

	@Override
	public String getTestName() {
		return "Human Test";
	}
	
	
	private void initWorld() {
		final float earthGravity = -9.8f;
		final Vec2 gravity = new Vec2(0, earthGravity);
		final boolean allowSleep = false;
		
		final World world = getWorld();
		
		world.setGravity(gravity);
		world.setAllowSleep(allowSleep);
		
		PolygonShape groundShape = new PolygonShape();
		groundShape.setAsEdge(new Vec2(-100,  -1), new Vec2(100, -1));
		
		BodyDef groundDefinition = new BodyDef();
		groundDefinition.type = BodyType.STATIC;
		groundDefinition.position.set(0, 0);
		groundDefinition.allowSleep = false;
		
		Body groundBody = getWorld().createBody(groundDefinition);
		groundBody.createFixture(groundShape, 5.0f).setFriction(5f);
		
	}
	
	private void initActor() {
		HumanBody body = HumanBody.create(getWorld());
		HumanBrain brain = new HumanBrain();
		HumanCharacter character = new HumanCharacter();
		character.init(body, brain);
		character.activate();
		
	}
	
	
}
