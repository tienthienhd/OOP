package scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gfx.Animation;
import gfx.Assets;
import gfx.GameCamera;
import manager.EntityManager.EntityState;
import map.Tile;
import manager.IEntityManager;
import manager.IMapManager;

public class GameScene extends Scene {

	IMapManager map;
	IEntityManager entities;
	GameCamera gameCamera;
	Animation playerUp;
	Animation playerDown;
	Animation playerRight;
	Animation playerLeft;

	public GameScene(GameCamera gameCamera, IMapManager map, IEntityManager entities) {
		this.gameCamera = gameCamera;
		this.map = map;
		this.entities = entities;
		this.playerDown = new Animation(Assets.playerDown, 300);
		this.playerUp = new Animation(Assets.playerUp, 300);
		this.playerRight = new Animation(Assets.playerRight, 300);
		this.playerLeft = new Animation(Assets.playerLeft, 300);
	}

	@Override
	public void update() {
		if (entities.isPlayerMoving()) {
			// update animation
			playerRight.update();
			playerLeft.update();
			playerUp.update();
			playerDown.update();
		}
	}

	@Override
	public void draw(Graphics g) {
		drawMap(g);
		drawMonster(g);
		drawPlayer(g);
	}

	private void drawPlayer(Graphics g) {
		EntityState state = this.entities.getPlayerState();
		BufferedImage buffer = null;
		switch (this.entities.getPlayerState().getDirection()) {
		case UP:
			buffer = playerUp.getCurrentFrame();
			break;
		case DOWN:
			buffer = playerDown.getCurrentFrame();
			break;
		case RIGHT:
			buffer = playerRight.getCurrentFrame();
			break;
		case LEFT:
			buffer = playerLeft.getCurrentFrame();
			break;

		}

		g.setColor(Color.blue);
		g.fillRect(state.getX() - gameCamera.getxOffset(), state.getY() - gameCamera.getyOffset()
				, 48, 96);
		
		g.drawImage(buffer, state.getX() - gameCamera.getxOffset(), 
				state.getY() - gameCamera.getyOffset(), null);
		

	}

	private void drawMonster(Graphics g) {
		ArrayList<EntityState> states = this.entities.getMonsterState();
		for (EntityState state : states) {
			
			g.setColor(Color.RED);
			g.fillRect(state.getX() - gameCamera.getxOffset(), state.getY() - gameCamera.getyOffset()
					, 48, 48);
			
			g.drawImage(Assets.monster, state.getX() - gameCamera.getxOffset(), state.getY() - gameCamera.getyOffset(),
					null);
			
			
		}
	}

	private void drawMap(Graphics g) {
		// map.getCurrentMap().draw(g);

		int widthMap = map.getCurrentMap().getWidthMap();
		int heightMap = map.getCurrentMap().getHeightMap();

		int xStart = (int) Math.max(0, gameCamera.getxOffset() / Tile.WIDTH_TILE);
		int xEnd = (int) Math.min(widthMap, (gameCamera.getxOffset() + GameCamera.CAMERA_WIDTH) / Tile.WIDTH_TILE + 1);
		int yStart = (int) Math.max(0, gameCamera.getyOffset() / Tile.HEIGHT_TILE);
		int yEnd = (int) Math.min(heightMap,
				(gameCamera.getyOffset() + GameCamera.CAMERA_HEIGHT) / Tile.HEIGHT_TILE + 1);

		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {

				Tile t = map.getCurrentMap().getTile(y, x);
				g.drawImage(t.getImage(), (int) (x * Tile.WIDTH_TILE - gameCamera.getxOffset()),
						(int) (y * Tile.HEIGHT_TILE - gameCamera.getyOffset()), null);
				
//				g.setColor(Color.red);
//				g.drawString(t.getX()/48 + "," + t.getY()/48,
//						(int) (x * Tile.WIDTH_TILE - gameCamera.getxOffset()) + 10,
//						(int) (y * Tile.HEIGHT_TILE - gameCamera.getyOffset()) + 20);
//						
//				g.drawLine((int) (x * Tile.WIDTH_TILE - gameCamera.getxOffset()),
//						(int) (y * Tile.HEIGHT_TILE - gameCamera.getyOffset()), 
//						(int) (x * Tile.WIDTH_TILE - gameCamera.getxOffset()) + 48,
//						(int) (y * Tile.HEIGHT_TILE - gameCamera.getyOffset()));
//				g.drawLine((int) (x * Tile.WIDTH_TILE - gameCamera.getxOffset()),
//						(int) (y * Tile.HEIGHT_TILE - gameCamera.getyOffset()), 
//						(int) (x * Tile.WIDTH_TILE - gameCamera.getxOffset()),
//						(int) (y * Tile.HEIGHT_TILE - gameCamera.getyOffset()) + 48);
//				g.drawLine((int) (x * Tile.WIDTH_TILE - gameCamera.getxOffset()) + 48,
//						(int) (y * Tile.HEIGHT_TILE - gameCamera.getyOffset()), 
//						(int) (x * Tile.WIDTH_TILE - gameCamera.getxOffset()) + 48,
//						(int) (y * Tile.HEIGHT_TILE - gameCamera.getyOffset()) + 48);
//				g.drawLine((int) (x * Tile.WIDTH_TILE - gameCamera.getxOffset()),
//						(int) (y * Tile.HEIGHT_TILE - gameCamera.getyOffset()) + 48, 
//						(int) (x * Tile.WIDTH_TILE - gameCamera.getxOffset()) + 48,
//						(int) (y * Tile.HEIGHT_TILE - gameCamera.getyOffset()) + 48);
			}
		}
	}

}
