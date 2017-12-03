package enity.creature;

import java.awt.Point;

public class Monster extends Creature {

	private static final int WIDTH = 48;
	private static final int HEIGHT = 48;

	public static final int HP_MAX = 50;

	private static final int DEFAULT_SPEED = 3;
	private static final int DEFAULT_DAMAGE = 10;
	private static final int DEFAULT_DEFENSE = 5;
	private static final int DEFAULT_ATTACK_RADIUS = 180;
	private static final int DEFAULT_SPEED_ATTACK = 300;

	private final int xOrigin;
	private final int yOrigin;

	public Monster(String name, int x, int y) {
		super(name, x, y, WIDTH, HEIGHT, HP_MAX, DEFAULT_SPEED, DEFAULT_DEFENSE, DEFAULT_DAMAGE, DEFAULT_ATTACK_RADIUS, DEFAULT_SPEED_ATTACK );
		this.xOrigin = x;
		this.yOrigin = y;
	}

	@Override
	public void update() {
		if(new Point(this.x, this.y).distance(new Point(xOrigin, yOrigin)) > this.attackRadius) {
			this.dx = xOrigin - this.x;
			this.dy = yOrigin - this.y;
		}
//		move();
		super.update();
	}

	public synchronized void playerHasMoved(int x, int y) {
		if(new Point(this.x, this.y).distance(new Point(x, y)) > this.attackRadius) return;
		this.dx = x - this.x;
		this.dy = y - this.y;
	}
	
}
