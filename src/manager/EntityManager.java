package manager;

import java.util.ArrayList;

import enity.creature.Creature;
import enity.creature.Direction;
import enity.creature.Monster;
import enity.creature.Player;
import gfx.Assets;
import gfx.GameCamera;

public class EntityManager extends Manager implements IEntityManager, InputHandler {

	private Player player;
	int xPlayerLast, yPlayerLast;
	private ArrayList<Monster> monsters;

	private GameCamera gameCamera;

	private IMapManager map;

	public EntityManager(IMapManager map) {
		// TODO Auto-generated constructor stub
		this.map = map;
		player = new Player("", 0, 0);
		monsters = new ArrayList<>();
		monsters.add(new Monster("", 100, 400));
	}

	@Override
	public void update() {
		this.checkCollisionWithTile(player);
		player.update();
		gameCamera.centerOnEntity(player);
		for (Monster m : monsters) {
			this.checkCollisionWithTile(m);
			m.update();
		}
	}

	private void checkCollisionWithTile(Creature creature) {
		int dx = creature.getDx();
		int dy = creature.getDy();
		int x = creature.getX();
		int y = creature.getY();

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
	}

	private boolean collisionWithTile(int x, int y) {
		return map.isSolid(x, y);
	}

	@Override
	public EntityState getPlayerState() {
		EntityState state = new EntityState(player.getX(), player.getY(), player.getDirection());
		return state;
	}

	@Override
	public ArrayList<EntityState> getMonsterState() {
		ArrayList<EntityState> states = new ArrayList<>();
		for (Monster m : monsters) {
			states.add(new EntityState(m.getX(), m.getY(), m.getDirection()));
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

	public void setGameCamera(GameCamera gameCamera) {
		this.gameCamera = gameCamera;
	}

	public static class EntityState {
		private int x, y;
		private Direction direction;

		public EntityState(int x, int y, Direction direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
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
	}

}
