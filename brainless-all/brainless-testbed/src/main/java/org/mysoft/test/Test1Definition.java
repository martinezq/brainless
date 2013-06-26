package org.mysoft.test;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.testbed.framework.TestbedTest;

public class Test1Definition extends TestbedTest {

	  @Override
	  public void initTest(boolean argDeserialized) {
	    setTitle("Couple of Things Test");

	    getWorld().setGravity(new Vec2(0, 0));

	    for (int i = 0; i < 3; i++) {
	      PolygonShape polygonShape = new PolygonShape();
	      polygonShape.setAsBox(1, 1);
	      
	      BodyDef bodyDef = new BodyDef();
	      bodyDef.type = BodyType.DYNAMIC;
	      bodyDef.position.set(5 * i, 0);
	      bodyDef.angle = (float) (Math.PI / 4 * i);
	      bodyDef.allowSleep = false;
	      Body body = getWorld().createBody(bodyDef);
	      body.createFixture(polygonShape, 5.0f);

	      body.applyForce(new Vec2(-100 * (i - 1), 0), new Vec2());
	    }
	  }

	  @Override
	  public String getTestName() {
	    return "Couple of Things";
	  }
	
}
