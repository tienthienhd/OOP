package manager;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.FileReader;
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
import utils.Utils;

public class EntityManager extends Manager implements IEntityManager, InputHandler {

	private Player player;
	private int xPlayerLast, yPlayerLast; // storage old position of player after move
	private boolean isPlayerAttacking;

	private ArrayList<Monster> monsters;

	// private ArrayList<Weapon> weapons;
	// private ArrayList<Clothes> clothes;
	// private ArrayList<Mana> manas;
	// private ArrayList<Blood> bloods;

	private ArrayList<Item> items;

	private ArrayList<Integer> xMonsterLast; // storage old x position of monster after move
	private ArrayList<Integer> yMonsterLast; // storage old y position of monster after move

	private GameCamera gameCamera;

	private IMapManager map;
	
	private boolean isSwitchMap;

	public EntityManager(IMapManager map) {
		this.map = map;
		player = new Player("", 0, 0);
		this.isPlayerAttacking = false;
		setEntityNewMap();
		
		
	}
	
	public void setEntityNewMap() {
		monsters = new ArrayList<>();
		xMonsterLast = new ArrayList<>();
		yMonsterLast = new ArrayList<>();
		ArrayList<Point> monsterPoints = new ArrayList<>();
		int typeOfMoster = Utils.loadMonsterFromFile(map.getMonsterMap().get(map.getCurrentMapIndex()),monsterPoints);
		System.out.println(monsterPoints.size());
		for (int i = 0; i < monsterPoints.size(); i++) {
			Monster m = new Monster(typeOfMoster == 0 ? "dragon" : "zombie", monsterPoints.get(i).x, monsterPoints.get(i).y);
			monsters.add(m);
			this.xMonsterLast.add(m.getX());
			this.yMonsterLast.add(m.getY());
		}
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
			Assets.open.stop();
			Assets.gameover.start();
		}
		this.checkCollisionWithTile(player);
		player.update();
		if (player.getTarget() != null && player.getTarget() instanceof Item && new Point(player.getX(), player.getY())
				.distance(new Point(player.getTarget().getX(), player.getTarget().getY())) < 1) {
			player.getItem((Item) player.getTarget());
			items.remove(player.getTarget());
			player.setTarget(null);
		}

		if (isPlayerAttacking && delta > 500) {
			delta -= 500;
			this.player.attack();
		}
		gameCamera.centerOnEntity(player);
		for (int i = 0; i < monsters.size(); i++) {
			Monster m = monsters.get(i);
			if (m.getHp() <= 0) {
				// Mana mana = new Mana("", m.getX(), m.getY());
				// manas.add(mana);
				Item item = getRandomItem(m);
				if (item != null) {
					items.add(item);
					// System.out.println("ok");
					// System.out.println(item.getId());
				}
				monsters.remove(m);
				m = null;
				break;
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
		if (itemType == 4 && rateDrop < 0.5d) {
			return new Weapon(m.getX() + 10, m.getY() - 10, 6);
		} else if (itemType == 3 && rateDrop < 0.5d) {
			return new Clothes(m.getX() + 10, m.getY() - 10, 6);
		} else if (itemType == 1 && rateDrop < 0.7d) {
			return new Blood(m.getX() + 10, m.getY() - 10);
		} else if (itemType == 2 && rateDrop < 0.7d) {
			return new Mana(m.getX() + 10, m.getY() - 10);
		} else {
			return null;
		}
	}

	@Override
	public void switchMap() {
		if (map.switchNextMap(player.getX(), player.getY())) {
			player.setX(map.getXStart());
			player.setY(map.getYStart());
			setEntityNewMap(); 
			this.isSwitchMap = true;
		} else if (map.switchPrevMap(player.getX(), player.getY())) {
			player.setX(map.getXEnd());
			player.setY(map.getYEnd());
			setEntityNewMap();
			this.isSwitchMap = true;
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
		Entity target = this.player.getTarget();
		CreatureState state = null;
		if (target != null) {
			if (target instanceof Monster) {
				CreatureState targetState = new CreatureState(target.getName(), target.getX(), target.getY(),
						((Creature) target).getDirection(), ((Creature) target).getHp(), null);
				state = new CreatureState(player.getName(), player.getX(), player.getY(), player.getDirection(),
						player.getHp(), targetState);
			} else {
				ItemState targetState = new ItemState(target.getName(), target.getX(), target.getY(),
						((Item) target).getType());
				state = new CreatureState(player.getName(), player.getX(), player.getY(), player.getDirection(),
						player.getHp(), targetState);
			}
		} else {
			state = new CreatureState(player.getName(), player.getX(), player.getY(), player.getDirection(),
					player.getHp(), null);
		}
		return state;
	}

	@Override
	public ArrayList<CreatureState> getMonsterState() {
		ArrayList<CreatureState> states = new ArrayList<>();
		for (Monster m : monsters) {
			states.add(new CreatureState(m.getName(), m.getX(), m.getY(), m.getDirection(), m.getHp(),
					new EntityState(player.getName(), player.getX(), player.getY())));
		}
		return states;
	}

	@Override
	public ArrayList<ItemState> getItemState() {
		ArrayList<ItemState> states = new ArrayList<>();
		for (Item item : items) {
			states.add(new ItemState(item.getName(), item.getX(), item.getY(), item.getType()));
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
		// for (Monster m : monsters) {
		// if (checkCollisionWithEntity(player, m)) {
		// player.attack(m);
		// // System.out.println("player attack : monster's hp: " + m.getHp());
		// }
		// }
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

	public static class CreatureState extends EntityState {
		private Direction direction;
		private int hp;
		private EntityState target;

		public CreatureState(String name, int x, int y, Direction direction, int hp, EntityState target) {
			super(name, x, y);
			this.direction = direction;
			this.hp = hp;
			this.target = target;
		}

		public Direction getDirection() {
			return direction;
		}

		public int getHp() {
			return hp;
		}

		public EntityState getTarget() {
			return this.target;
		}

	}

	public static class EntityState {
		protected int x;
		protected int y;
		protected String name;

		public EntityState(String name, int x, int y) {
			this.x = x;
			this.y = y;
			this.name = name;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public String getName() {
			return this.name;
		}
	}

	public static class ItemState extends EntityState {

		private ItemType type;

		public ItemState(String name, int x, int y, ItemType type) {
			super(name, x, y);
			this.type = type;
		}

		public ItemType getType() {
			return type;
		}

	}

	@Override
	public Entity chooseEntity(int x, int y) {
		for (Monster m : monsters) {
			if (new Point(m.getX() + 24 - gameCamera.getxOffset(), m.getY() + 24 - gameCamera.getyOffset())
					.distance(new Point(x, y)) < 24) {
				this.player.setTarget(m);
				this.player.setDx(m.getX() - this.player.getX());
				this.player.setDy(m.getY() - this.player.getY());
				return m;
			}
		}

		for (Item i : items) {
			if (new Point(i.getX() + 18 - gameCamera.getxOffset(), i.getY() + 18 - gameCamera.getyOffset())
					.distance(new Point(x, y)) < 24) {// FIXME: fix item
				this.player.setTarget(i);
				this.player.setDx(i.getX() - this.player.getX());
				this.player.setDy(i.getY() - this.player.getY());
				return i;
			}
		}
		// this.player.setTarget(null);
		return null;
	}

	@Override
	public boolean isPlayerAttacking() {
		return this.isPlayerAttacking;
	}

	@Override
	public boolean isSwitchMap() {
		if(this.isSwitchMap == true) {
			this.isSwitchMap = false;
			return true;
		}
		return false;
	}
}
