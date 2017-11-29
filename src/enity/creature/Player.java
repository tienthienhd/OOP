package enity.creature;

import java.awt.Point;

import enity.Entity;
import enity.item.Inventory;
import enity.item.Item;

public class Player extends Creature {

	private static final int WIDTH = 48;
	private static final int HEIGHT = 96;

	public static final int HP_MAX = 10000;
	// public static final int MP_MAX = 100;

	private static final int DEFAULT_SPEED = 10;
	private static final int DEFAULT_DAMAGE = 20;
	private static final int DEFAULT_DEFENSE = 10;
	private static final int DEFAULT_ATTACK_RADIUS = 96;

	// private int mp;
	private Inventory inventory;
	private Entity target;

	public Player(String name, int x, int y) {
		super(name, x, y, WIDTH, HEIGHT, HP_MAX, DEFAULT_SPEED, DEFAULT_DEFENSE, DEFAULT_DAMAGE, DEFAULT_ATTACK_RADIUS);
		this.inventory = new Inventory();
	}

	@Override
	public void update() {
		move();
		if (target != null && new Point(target.getX(), target.getY()).distance(this.x + 24, this.y + 48) > attackRadius) {
			this.target = null;
		}
	}

	public void attack() {
		if (target instanceof Creature) {
			super.attack((Creature) target);
		}
	}

	public void getItem(Item item) {
		this.inventory.isAdded(item);
	}

	public void setTarget(Entity target) {
		this.target = target;
//		 System.out.println("muc tieu cua tao la co ve dep");
	}
}
