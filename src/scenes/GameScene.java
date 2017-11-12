package scenes;

import java.awt.Graphics;
import java.util.ArrayList;

import gfx.Assets;
import manager.EntityManager.EntityState;
import manager.IEntityManager;
import manager.IMapManager;

public class GameScene extends Scene {
	
	IMapManager map;
	IEntityManager entities;
	
	public GameScene(IMapManager map, IEntityManager entities) {
		this.map = map;
		this.entities = entities;
	}
	
	@Override
	public void draw(Graphics g) {
		drawMap(g);
		drawMonster(g);
		drawPlayer(g);
	}

	private void drawPlayer(Graphics g) {
		EntityState state = this.entities.getPlayerState();
		g.drawImage(Assets.player, state.getX(), state.getY(), null);
		
	}

	private void drawMonster(Graphics g) {
		ArrayList<EntityState> states = this.entities.getMonsterState();
		
	}

	private void drawMap(Graphics g) {
		map.getCurrentMap().draw(g);
		
	}
	
	
}
