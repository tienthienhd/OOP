package manager;

import java.util.ArrayList;

import enity.creature.Direction;
import enity.creature.Monster;
import enity.creature.Player;
import gfx.GameCamera;

public class EntityManager extends Manager implements IEntityManager, InputHandler {

	private Player player;
	private ArrayList<Monster> monsters;
	private GameCamera gameCamera;

	public EntityManager() {
		// TODO Auto-generated constructor stub
		player = new Player("", 0, 0);
		monsters = new ArrayList<>();
	}

	@Override
	public void update() {
		player.update();
		gameCamera.centerOnEntity(player);
		for (Monster m : monsters) {
			m.update();
		}
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
		this.player.getInput(dir);
		
	}
	
	int x, y;
	@Override
	public boolean isPlayerMoving() {
		if(x == player.getX() && y == player.getY()) {
			return false;
		} else {
			x = player.getX();
			y = player.getY();
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
