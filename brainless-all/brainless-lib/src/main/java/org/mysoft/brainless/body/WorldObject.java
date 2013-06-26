package org.mysoft.brainless.body;

import org.jbox2d.dynamics.World;

public abstract class WorldObject {

	protected World world;
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public World getWorld() {
		return world;
	}
	
}
