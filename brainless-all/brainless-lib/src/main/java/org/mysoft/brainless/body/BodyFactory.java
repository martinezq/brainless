package org.mysoft.brainless.body;

import org.jbox2d.dynamics.World;

public class BodyFactory {

	private  World world;
	
	public final static BodyFactory create(World world) {
		BodyFactory f = new BodyFactory();
		f.world = world;
		return f;
	}
	
	public final Bone createSegment(float x1, float y1, float x2, float y2, float density) {
		return new Bone(world, x1, y1, x2, y2, density);
	}
	
}
