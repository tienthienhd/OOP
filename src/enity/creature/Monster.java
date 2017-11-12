package enity.creature;

import java.awt.Point;
import java.util.LinkedList;

public class Monster extends Creature {
	
	private static final int WIDTH = 48;
	private static final int HEIGHT = 48;
	
	public static final int HP_MAX = 50;

	private static final int DEFAULT_SPEED = 5;
	private static final int DEFAULT_DAMAGE = 10;
	private static final int DEFAULT_DEFENSE = 5;
	private static final int DEFAULT_ATTACK_RADIUS = 50;
	
	private LinkedList<Point> path;

	public Monster(String name, int x, int y) {
		super(name, x, y, WIDTH, HEIGHT,
				HP_MAX, DEFAULT_SPEED, DEFAULT_DEFENSE, DEFAULT_DAMAGE, DEFAULT_ATTACK_RADIUS);
		
	}

	@Override
	public void update() {
		Point p = path.removeFirst();
		dx = p.x - this.x;
		dy = p.y - this.y;
		move();
	}
	

	private void calcNewPath(Point playerLoc) {
		path = AStarSearch.findPathAStar(new Point(this.x / this.speed, this.y / this.speed), playerLoc);
	}
}
