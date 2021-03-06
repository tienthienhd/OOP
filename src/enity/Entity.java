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
		this.bound = new Rectangle(x, y, width, height);
	}
	
	public abstract void update();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Rectangle getBound() {
		return this.bound;
	}
	
	public void setBound(int x, int y) {
		this.bound.x = x;
		this.bound.y = y;
	}
}
