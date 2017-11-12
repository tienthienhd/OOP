package enity;

import java.awt.Rectangle;

public abstract class Entity {

	protected String name;
	protected int x, y;
	protected int width, height;
	protected Rectangle bound;
	
	public Entity(String name, int x, int y, int width, int height) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.bound = new Rectangle(0, 0, width, height);
	}
	
	public abstract void update();
	
}
