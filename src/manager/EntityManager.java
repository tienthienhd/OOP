package manager;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import enity.Entity;
import enity.creature.Creature;
import enity.creature.Direction;
import enity.creature.Monster;
import enity.creature.Player;
import gfx.Assets;
import gfx.GameCamera;

public class EntityManager extends Manager implements IEntityManager, InputHandler {

	private Player player;
	private int xPlayerLast, yPlayerLast;
	private ArrayList<Monster> monsters;
	private ArrayList<Integer> xMonsterLast;
	private ArrayList<Integer> yMonsterLast;
	

	private GameCamera gameCamera;

	private IMapManager map;

	public EntityManager(IMapManager map) {
		this.map = map;
		player = new Player("", 0, 0);
		monsters = new ArrayList<>();
		xMonsterLast = new ArrayList<>();
		yMonsterLast = new ArrayList<>();
		Random r = new Random();
		for(int i = 0; i < 10; i++) {
			Monster m = new Monster("", r.nextInt(2400), r.nextInt(1344));
			monsters.add(m);
			this.xMonsterLast.add(m.getX());
			this.yMonsterLast.add(m.getY());
		}
	}

	@Override
	public void update() {
		if(player.getHp() <= 0) {
			
		}
		this.checkCollisionWithTile(player);
		player.update();
		gameCamera.centerOnEntity(player);
		for (int i = 0; i < monsters.size(); i++) {
			Monster m = monsters.get(i);
			if(m.getHp()  <= 0) {
				monsters.remove(m);
			}
			this.checkCollisionWithTile(m);
			if(checkCollisionWithEntity(player, m)) {
				m.setDx(0);
				m.setDy(0);
				m.attack(player);
//				System.out.println("m attack : player's hp: " + player.getHp());
			}
			
			m.update();
			
		}
		
	}
	
	@Override
	public void switchMap() {
		if(map.switchNextMap(player.getX(), player.getY())) {
			player.setX(map.getXStart());
			player.setY(map.getYStart());
		}
		else if(map.switchPrevMap(player.getX(), player.getY())) {
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

		if(creature instanceof Player) {
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
		for(Monster m: monsters) {
			m.playerHasMoved(player.getX(), player.getY());
		}
	}
	
	@Override
	public void playerAttack() {
		for(Monster m: monsters) {
			if(checkCollisionWithEntity(player, m)) {
				player.attack(m);
//				System.out.println("player attack : monster's hp: " + m.getHp());
			}
		}
	}
	
	public static Rectangle getBound(Entity entity) {
		Rectangle r = new Rectangle();
		if(entity instanceof Player) {
			r.x = entity.getX() + 10;
			r.y = entity.getY() + 20;
			r.width = entity.getWidth() - 20;
			r.height = entity.getHeight() -22;
		}
		else if(entity instanceof Monster) {
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
	public ArrayList<Boolean> isMonstersMoving(){
		ArrayList<Boolean> isMonstersMoving = new ArrayList<>();
		for(int i = 0; i < monsters.size(); i++) {
			Monster m = monsters.get(i);
			if(m.getX() == xMonsterLast.get(i) && m.getY() == yMonsterLast.get(i)) {
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

	public static class CreatureState {
		private int x, y;
		private Direction direction;
		private int hp;
		

		public CreatureState(int x, int y, Direction direction, int hp) {
			this.x = x;
			this.y = y;
			this.direction = direction;
			this.hp = hp;
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

}
