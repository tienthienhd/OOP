package enity.creature;

import java.awt.Point;

import enity.Entity;
import enity.item.Blood;
import enity.item.Clothes;
import enity.item.Inventory;
import enity.item.Item;
import enity.item.ItemType;
import enity.item.Mana;
import enity.item.Weapon;

public class Player extends Creature {

	private static final int WIDTH = 48;
	private static final int HEIGHT = 96;


	public static final int HP_MAX = 1000;
	// public static final int MP_MAX = 100;

	private static final int DEFAULT_SPEED = 10;
	private static final int DEFAULT_DAMAGE = 15;
	private static final int DEFAULT_DEFENSE = 10;
	private static final int DEFAULT_ATTACK_RADIUS = 120;
	private static final int DEFAULT_SPEED_ATTACK = 700;

	// private int mp;
	private Inventory inventory;
	private Entity target;

	public Player(String name, int x, int y) {
		super(name, x, y, WIDTH, HEIGHT, HP_MAX, DEFAULT_SPEED, DEFAULT_DEFENSE, 
				DEFAULT_DAMAGE, DEFAULT_ATTACK_RADIUS, DEFAULT_SPEED_ATTACK);
		this.inventory = new Inventory();
	}

	@Override
	public void update() {
		super.update();
//		move();

		if (this.target instanceof Monster && ((Monster) this.target).getHp() <= 0) {
			this.target = null;
		}
	}

	public void attack() {
		if (target != null
				&& new Point(target.getX(), target.getY()).distance(this.x + 24, this.y + 48) > attackRadius) {
			return;
		}
		if (target instanceof Creature) {
			super.attack((Creature) target);
		}

	}

	public void getItem(Item item) {
		this.inventory.added(item);
	}

	public void setTarget(Entity target) {
		this.target = target;
		// System.out.println("muc tieu cua tao la co ve dep");
	}

	public Entity getTarget() {
		return this.target;
	}

	public Inventory getInventory() {
		return this.inventory;
	}
	
	//TODO: lam tiep mp
	public void useItem(Item item) {
		ItemType type = item.getType();
		if(type == ItemType.BLOOD) {
			if(this.hp + Blood.HP > this.HP_MAX) {
				this.hp = this.HP_MAX;
			}else {
				this.hp = this.hp + Blood.HP;
			}
		}
		if(type == ItemType.MANA) {
			//TODO
		}
		if(type == ItemType.CLOTHES) {
			this.defense += ((Clothes)item).getDefense();
		}
		if(type == ItemType.WEAPON) {
			this.damage += ((Weapon)item).getDamage();
		}
	}
}
