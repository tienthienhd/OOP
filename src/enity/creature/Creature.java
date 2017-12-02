package enity.creature;

import enity.Entity;

public abstract class Creature extends Entity {

	protected int hp;
	protected int speed;
	protected int defense;
	protected int damage;
	protected int attackRadius;
	protected int dx, dy;

	protected Direction direction;

	public Creature(String name, int x, int y, int width, int height, int hp, int speed, int defense, int damage,
			int attackRadius) {
		super(name, x, y, width, height);
		this.hp = hp;
		this.speed = speed;
		this.defense = defense;
		this.damage = damage;
		this.attackRadius = attackRadius;
		this.direction = Direction.RIGHT;
	}

	public void move() {
		// TODO: xu ly va cham voi map
		if (this.dx > 0) {
			this.direction = Direction.RIGHT;
			if(Math.abs(this.dx) < this.speed) {
				this.x += this.dx;
				this.dx = 0;
				return;
			}
			this.x += this.speed;
			this.dx -= speed;
		} else if (this.dx < 0) {
			this.direction = Direction.LEFT;
			if(Math.abs(this.dx) < this.speed) {
				this.x += this.dx;
				this.dx = 0;
				return;
			}
			this.x -= this.speed;
			this.dx += this.speed;
		}

		if (this.dy > 0) {
			this.direction = Direction.DOWN;
			if(Math.abs(this.dy) < this.speed) {
				this.y += this.dy;
				this.dy = 0;
				return;
			}
			this.y += this.speed;
			this.dy -= speed;
		} else if (this.dy < 0) {
			this.direction = Direction.UP;
			if(Math.abs(this.dy) < this.speed) {
				this.y += this.dy;
				this.dy = 0;
				return;
			}
			this.y -= this.speed;
			this.dy += this.speed;
		}
	}

	public void attack(Creature target) {
		target.beHurted(this.damage);
	}

	public void beHurted(int damage) {
		if(this.hp <= 0) {
			return;
		}
		if (damage <= this.defense) {
			this.hp -= 1;
		} else {
			this.hp -= (damage - this.defense);
		}
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}
	
	public int getSpeed() {
		return this.speed;
	}

	public int getHp() {
		return hp;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	
}
