package manager;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import enity.Entity;
import enity.creature.Creature;
import enity.creature.Direction;
import enity.creature.Monster;
import enity.creature.Player;
import enity.item.Blood;
import enity.item.Clothes;
import enity.item.Item;
import enity.item.ItemType;
import enity.item.Mana;
import enity.item.Weapon;
import gfx.Assets;
import gfx.GameCamera;

public class EntityManager extends Manager implements IEntityManager, InputHandler {

	
	private Player player;
	private int xPlayerLast, yPlayerLast; // storage old position of player after move
	private boolean isPlayerAttacking;
	
	private ArrayList<Monster> monsters;

//	private ArrayList<Weapon> weapons;
//	private ArrayList<Clothes> clothes;
//	private ArrayList<Mana> manas;
//	private ArrayList<Blood> bloods;
	
	private ArrayList<Item> items;

	private ArrayList<Integer> xMonsterLast; // storage old x position of monster after move
	private ArrayList<Integer> yMonsterLast; // storage old y position of monster after move

	private GameCamera gameCamera;

	private IMapManager map;

	public EntityManager(IMapManager map) {
		this.map = map;
		player = new Player("", 0, 0);
		this.isPlayerAttacking = false;
		monsters = new ArrayList<>();
		xMonsterLast = new ArrayList<>();
		yMonsterLast = new ArrayList<>();
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			Monster m = new Monster("", r.nextInt(2400), r.nextInt(1344));
			monsters.add(m);
			this.xMonsterLast.add(m.getX());
			this.yMonsterLast.add(m.getY());
		}

//		weapons = new ArrayList<>();
//		clothes = new ArrayList<>();
//		manas = new ArrayList<>();
//		bloods = new ArrayList<>();
		
		items = new ArrayList<>();
	}

	long begin = System.currentTimeMillis();
	long lastTime = 0;
	long delta = 0;
	@Override
	public void update() {
		lastTime = System.currentTimeMillis();
		delta += lastTime - begin;
		if (player.getHp() <= 0) {
			player.setDx(0);
			player.setDy(0);
			player.setDamage(0);
		}
		this.checkCollisionWithTile(player);
		player.update();
		if(isPlayerAttacking && delta > 300) {
			delta -= 300;
			this.player.attack();
		}
		gameCamera.centerOnEntity(player);
		for (int i = 0; i < monsters.size(); i++) {
			Monster m = monsters.get(i);
			if (m.getHp() <= 0) {
				// Mana mana = new Mana("", m.getX(), m.getY());
				// manas.add(mana);
				Item item = getRandomItem(m);
				if(item != null) {
					items.add(item);
//					System.out.println("ok");
//					System.out.println(item.getId());
				}
				monsters.remove(m);
			}
			this.checkCollisionWithTile(m);
			if (checkCollisionWithEntity(player, m)) {
				m.setDx(0);
				m.setDy(0);
				m.attack(player);
				// System.out.println("m attack : player's hp: " + player.getHp());
			}

			m.update();

		}
		begin = lastTime;

	}

	private Item getRandomItem(Monster m) {
		int itemType = (int) (Math.random() * 5);
		double rateDrop = Math.random();
		if (itemType == 4 && rateDrop < 0.3d) {
			return new Weapon(m.getX(), m.getY(), 6);
		} else if (itemType == 3 && rateDrop < 0.3d) {
			return new Clothes(m.getX(), m.getY(), 6);
		} else if (itemType == 1 && rateDrop < 0.5d) {
			return new Blood(m.getX(), m.getY());
		} else if (itemType == 2 && rateDrop < 0.5d) {
			return new Mana(m.getX(), m.getY());
		} else {
			return null;
		}
	}

	@Override
	public void switchMap() {
		if (map.switchNextMap(player.getX(), player.getY())) {
			player.setX(map.getXStart());
			player.setY(map.getYStart());
		} else if (map.switchPrevMap(player.getX(), player.getY())) {
			player.setX(map.getXEnd());
			player.setY(map.getYEnd());
		}
	}

	private boolean checkCollisionWithEntity(Entity a, Entity b) {
		Rectangle ra = getBound(a);
		Rectangle rb = getBound(b);
		return rb.intersects(ra);
	}

	private void checkCollisionWithTile(Creature creature) {
		int dx = creature.getDx();
		int dy = creature.getDy();
		int x = creature.getX();
		int y = creature.getY();

		if (creature instanceof Player) {
			if (dy < 0) {// Up
				int ty = (y + 72) / Assets.HEIGHT_TILE;

				if (collisionWithTile((x + 17) / Assets.HEIGHT_TILE, ty)
						|| collisionWithTile((x + 30) / Assets.HEIGHT_TILE, ty) || y <= 0) {
					creature.setDy(0);
				}
			} else if (dy > 0) {// Down
				int ty = (y + 90) / Assets.HEIGHT_TILE;

				if (collisionWithTile((x + 17) / Assets.WIDTH_TILE, ty)
						|| collisionWithTile((x + 30) / Assets.HEIGHT_TILE, ty)) {
					creature.setDy(0);
				}
			}

			if (dx > 0) {// moving right
				int tx = (x + 36) / Assets.WIDTH_TILE;
				if (collisionWithTile(tx, (y + 72) / Assets.HEIGHT_TILE)
						|| collisionWithTile(tx, (y + 79) / Assets.HEIGHT_TILE)) {
					creature.setDx(0);
				}
			} else if (dx < 0) { // Moving left
				int tx = (x) / Assets.WIDTH_TILE;
				if (collisionWithTile(tx, (y + 72) / Assets.HEIGHT_TILE)
						|| collisionWithTile(tx, (y + 79) / Assets.HEIGHT_TILE) || x <= 0) {
					creature.setDx(0);
				}
			}
		} else {
			if (dy < 0) {// Up
				int ty = (y + 30) / Assets.HEIGHT_TILE;

				if (collisionWithTile((x + 17) / Assets.HEIGHT_TILE, ty)
						|| collisionWithTile((x + 30) / Assets.HEIGHT_TILE, ty) || y <= 0) {
					creature.setDy(0);
				}
			} else if (dy > 0) {// Down
				int ty = (y + 48) / Assets.HEIGHT_TILE;

				if (collisionWithTile((x + 17) / Assets.WIDTH_TILE, ty)
						|| collisionWithTile((x + 30) / Assets.HEIGHT_TILE, ty)) {
					creature.setDy(0);
				}
			}

			if (dx > 0) {// moving right
				int tx = (x + 36) / Assets.WIDTH_TILE;
				if (collisionWithTile(tx, (y + 72) / Assets.HEIGHT_TILE)
						|| collisionWithTile(tx, (y + 79) / Assets.HEIGHT_TILE)) {
					creature.setDx(0);
				}
			} else if (dx < 0) { // Moving left
				int tx = (x) / Assets.WIDTH_TILE;
				if (collisionWithTile(tx, (y + 72) / Assets.HEIGHT_TILE)
						|| collisionWithTile(tx, (y + 79) / Assets.HEIGHT_TILE) || x <= 0) {
					creature.setDx(0);
				}
			}
		}
	}

	private boolean collisionWithTile(int x, int y) {
		return map.isSolid(x, y);
	}

	@Override
	public CreatureState getPlayerState() {
		CreatureState state = new CreatureState(player.getX(), player.getY(), player.getDirection(), player.getHp());
		return state;
	}

	@Override
	public ArrayList<CreatureState> getMonsterState() {
		ArrayList<CreatureState> states = new ArrayList<>();
		for (Monster m : monsters) {
			states.add(new CreatureState(m.getX(), m.getY(), m.getDirection(), m.getHp()));
		}
		return states;
	}
	
	@Override
	public ArrayList<ItemState> getItemState() {
		ArrayList<ItemState> states = new ArrayList<>();
		for(Item item : items) {
			states.add(new ItemState(item.getX(), item.getY(), item.getType()));
		}
		return states;
	}


	@Override
	public void PlayerMove(Direction dir) {
		this.player.setDx(0);
		this.player.setDy(0);
		this.player.setDirection(dir);
		switch (dir) {
		case DOWN:
			this.player.setDy(this.player.getDy() + this.player.getSpeed());
			break;
		case UP:
			this.player.setDy(this.player.getDy() - this.player.getSpeed());
			break;
		case LEFT:
			this.player.setDx(this.player.getDx() - this.player.getSpeed());
			break;
		case RIGHT:
			this.player.setDx(this.player.getDx() + this.player.getSpeed());
			break;
		}
		for (Monster m : monsters) {
			m.playerHasMoved(player.getX(), player.getY());
		}
		// System.out.println("Move" + this.player.getDx() + ":" + this.player.getDy());
	}

	@Override
	public void playerAttack(boolean isAttacking) {
//		for (Monster m : monsters) {
//			if (checkCollisionWithEntity(player, m)) {
//				player.attack(m);
//				// System.out.println("player attack : monster's hp: " + m.getHp());
//			}
//		}
		this.isPlayerAttacking = isAttacking;
	}

	public static Rectangle getBound(Entity entity) {
		Rectangle r = new Rectangle();
		if (entity instanceof Player) {
			r.x = entity.getX() + 10;
			r.y = entity.getY() + 20;
			r.width = entity.getWidth() - 20;
			r.height = entity.getHeight() - 22;
		} else if (entity instanceof Monster) {
			r.x = entity.getX();
			r.y = entity.getY();
			r.width = entity.getWidth();
			r.height = entity.getHeight();
		}
		return r;
	}

	@Override
	public boolean isPlayerMoving() {
		if (xPlayerLast == player.getX() && yPlayerLast == player.getY()) {
			return false;
		} else {
			xPlayerLast = player.getX();
			yPlayerLast = player.getY();
			return true;
		}
	}

	@Override
	public ArrayList<Boolean> isMonstersMoving() {
		ArrayList<Boolean> isMonstersMoving = new ArrayList<>();
		for (int i = 0; i < monsters.size(); i++) {
			Monster m = monsters.get(i);
			if (m.getX() == xMonsterLast.get(i) && m.getY() == yMonsterLast.get(i)) {
				isMonstersMoving.add(false);
			} else {
				xMonsterLast.set(i, m.getX());
				yMonsterLast.set(i, m.getY());
				isMonstersMoving.add(true);

			}
		}
		return isMonstersMoving;
	}

	public void setGameCamera(GameCamera gameCamera) {
		this.gameCamera = gameCamera;
	}

	public static class CreatureState extends EntityState{
		private Direction direction;
		private int hp;

		public CreatureState(int x, int y, Direction direction, int hp) {
			super(x, y);
			this.direction = direction;
			this.hp = hp;
		}


		public Direction getDirection() {
			return direction;
		}

		public void setDirection(Direction direction) {
			this.direction = direction;
		}

		public int getHp() {
			return hp;
		}

		public void setHp(int hp) {
			this.hp = hp;
		}

	}

	public static class EntityState {
		protected int x;
		protected int y;

		public EntityState(int x, int y) {
			this.x = x;
			this.y = y;
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
	}
	
	public static class ItemState extends EntityState {

		private ItemType type;
		
		public ItemState(int x, int y, ItemType type) {
			super(x, y);
			this.type = type;
		}

		public ItemType getType() {
			return type;
		}

		public void setType(ItemType type) {
			this.type = type;
		}
		
		
	}

	@Override
	public Entity chooseEntity(int x, int y) {
		for(Monster m: monsters) {
			if(new Point(m.getX() + 24 - gameCamera.getxOffset(), 
					m.getY() + 24 - gameCamera.getyOffset()).distance(new Point(x, y)) < 24) {
				this.player.setTarget(m);
				return m;
			}
		}
		
		for(Item i : items) {
			if(new Point(i.getX() + 18 - gameCamera.getxOffset(), 
					i.getY() + 18 - gameCamera.getyOffset()).distance(new Point(x, y)) < 24) {//FIXME: fix item
				this.player.setTarget(i);
				return i;
			}
		}
		return null;
	}

	@Override
	public boolean isPlayerAttacking() {
		return this.isPlayerAttacking;
	}
}
