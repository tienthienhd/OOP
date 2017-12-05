package enity.creature;

import enity.Entity;

public abstract class Creature extends Entity {

	protected int hp, mp;
	protected int speed;
	protected int defense;
	protected int damage;
	protected int attackRadius;
	protected int dx, dy;
	protected int speedAttack;

	protected Direction direction;

	public Creature(String name, int x, int y, int width, int height, int hp, int mp, int speed, int defense,
			int damage, int attackRadius, int speedAttack) {
		super(name, x, y, width, height);
		this.hp = hp;
		this.mp = mp;
		this.speed = speed;
		this.defense = defense;
		this.damage = damage;
		this.attackRadius = attackRadius;
		this.direction = Direction.RIGHT;
		this.speedAttack = speedAttack;
	}

	public void move() {
		// TODO: xu ly va cham voi map
		if (this.dx > 0) {
			this.direction = Direction.RIGHT;
			if (Math.abs(this.dx) < this.speed) {
				this.x += this.dx;
				this.dx = 0;
				return;
			}
			this.x += this.speed;
			this.dx -= speed;
		} else if (this.dx < 0) {
			this.direction = Direction.LEFT;
			if (Math.abs(this.dx) < this.speed) {
				this.x += this.dx;
				this.dx = 0;
				return;
			}
			this.x -= this.speed;
			this.dx += this.speed;
		}

		if (this.dy > 0) {
			this.direction = Direction.DOWN;
			if (Math.abs(this.dy) < this.speed) {
				this.y += this.dy;
				this.dy = 0;
				return;
			}
			this.y += this.speed;
			this.dy -= speed;
		} else if (this.dy < 0) {
			this.direction = Direction.UP;
			if (Math.abs(this.dy) < this.speed) {
				this.y += this.dy;
				this.dy = 0;
				return;
			}
			this.y -= this.speed;
			this.dy += this.speed;
		}
	}

	@Override
	public void update() {
		move();
		lastTime = System.currentTimeMillis();
		delta += lastTime - begin;
		if (delta > 2 * this.speedAttack) {
			delta -= this.speedAttack;
		}
		begin = lastTime;
	}

	private long begin = System.currentTimeMillis();
	private long lastTime = 0;
	private long delta = 0;

	public void attack(Creature target) {
		// System.out.println(delta);
		if (delta > this.speedAttack) {
			delta -= this.speedAttack;
			if (this.mp > 0) {
				target.beHurted(this.damage);
				this.mp -= 5;
			} else {
				target.beHurted((int) (this.damage * 0.8d));
			}
		}
	}

	public void beHurted(int damage) {
		if (this.hp <= 0)
			return;
		if (this.hp < damage - this.defense) {
			this.hp = 0;
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

	public int getMp() {
		return this.mp;
	}

}
