package org.mysoft.playground;

import org.jbox2d.dynamics.World;

public class BodyFactory {

	private static World world;
	
	public final static void init(World world) {
		BodyFactory.world = world;
	}
	
	public final static Segment createSegment(float x1, float y1, float x2, float y2) {
		return new Segment(world, x1, y1, x2, y2);
	}
	
}
