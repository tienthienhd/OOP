package scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enity.creature.Monster;
import enity.creature.Player;
import gfx.Animation;
import gfx.Assets;
import gfx.GameCamera;
import manager.EntityManager.CreatureState;
import manager.EntityManager.ItemState;
import manager.IEntityManager;
import manager.IMapManager;
import map.Tile;

public class GameScene extends Scene {

	IMapManager map;
	IEntityManager entities;
	GameCamera gameCamera;

	Animation playerUp;
	Animation playerDown;
	Animation playerRight;
	Animation playerLeft;

	ArrayList<Animation> monstersUp;
	ArrayList<Animation> monstersDown;
	ArrayList<Animation> monstersLeft;
	ArrayList<Animation> monstersRight;
	

	public GameScene(GameCamera gameCamera, IMapManager map, IEntityManager entities) {
		this.gameCamera = gameCamera;
		this.map = map;
		this.entities = entities;
		this.playerDown = new Animation(Assets.playerDown, 300);
		this.playerUp = new Animation(Assets.playerUp, 300);
		this.playerRight = new Animation(Assets.playerRight, 300);
		this.playerLeft = new Animation(Assets.playerLeft, 300);

		this.monstersDown = new ArrayList<>();
		this.monstersUp = new ArrayList<>();
		this.monstersLeft = new ArrayList<>();
		this.monstersRight = new ArrayList<>();

		for (int i = 0; i < entities.getMonsterState().size(); i++) {
			if (true) {// TODO: for each monster
				this.monstersDown.add(new Animation(Assets.dragonDown, 300));
				this.monstersUp.add(new Animation(Assets.dragonUp, 300));
				this.monstersRight.add(new Animation(Assets.dragonRight, 300));
				this.monstersLeft.add(new Animation(Assets.dragonLeft, 300));
				
			}
		}

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
		
		ArrayList<Boolean> isMonstersMoving = entities.isMonstersMoving();
		for(int i = 0; i < isMonstersMoving.size(); i++) {
			if(isMonstersMoving.get(i)) {
				monstersDown.get(i).update();
				monstersUp.get(i).update();
				monstersRight.get(i).update();
				monstersLeft.get(i).update();
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		drawMap(g);
		drawItem(g);
		drawMonster(g);
		drawPlayer(g);
		drawGUI(g);
		
	}

	public void drawItem(Graphics g) {
		ArrayList<ItemState> stateMana = this.entities.getManas();
		for(ItemState it : stateMana) {
			g.drawImage(Assets.blood, it.getX() - gameCamera.getxOffset(), it.getY() - gameCamera.getyOffset(), null);	
		}
		ArrayList<ItemState> stateBlood = this.entities.getBloods();
		for(ItemState it : stateBlood) {
			g.drawImage(Assets.mana, it.getX() - gameCamera.getxOffset(), it.getY() - gameCamera.getyOffset(), null);
		}
	}
	
	private void drawPlayer(Graphics g) {
		CreatureState state = this.entities.getPlayerState();
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

//		g.setColor(Color.blue);
//		g.fillRect(state.getX() - gameCamera.getxOffset(), state.getY() - gameCamera.getyOffset(), 48, 96);

		g.drawImage(buffer, state.getX() - gameCamera.getxOffset(), state.getY() - gameCamera.getyOffset(), null);

	}

	private void drawMonster(Graphics g) {
		ArrayList<CreatureState> states = this.entities.getMonsterState();
		for (int i = 0; i < states.size(); i++) {
			CreatureState state = states.get(i);
			BufferedImage buffer = null;
			switch (state.getDirection()) {
			case UP:
				buffer = monstersUp.get(i).getCurrentFrame();
				break;
			case DOWN:
				buffer = monstersDown.get(i).getCurrentFrame();
				break;
			case RIGHT:
				buffer = monstersRight.get(i).getCurrentFrame();
				break;
			case LEFT:
				buffer = monstersLeft.get(i).getCurrentFrame();
				break;

			}
			
			
//			g.setColor(Color.RED);
//			g.fillRect(state.getX() - gameCamera.getxOffset(), state.getY() - gameCamera.getyOffset(), 48, 48);

			
			g.drawImage(buffer, state.getX() - gameCamera.getxOffset(), state.getY() - gameCamera.getyOffset(),
					null);
			
			g.setColor(Color.RED);
			g.fillRoundRect(state.getX() - gameCamera.getxOffset() + 4, state.getY() - gameCamera.getyOffset() - 5,
					(int)((float)state.getHp() / (float)Monster.HP_MAX * 40), 3, 5, 5);

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
				
				

				// g.setColor(Color.red);
				// g.drawString(t.getX()/48 + "," + t.getY()/48,
				// (int) (x * Tile.WIDTH_TILE - gameCamera.getxOffset()) + 10,
				// (int) (y * Tile.HEIGHT_TILE - gameCamera.getyOffset()) + 20);
				//
				// g.drawLine((int) (x * Tile.WIDTH_TILE - gameCamera.getxOffset()),
				// (int) (y * Tile.HEIGHT_TILE - gameCamera.getyOffset()),
				// (int) (x * Tile.WIDTH_TILE - gameCamera.getxOffset()) + 48,
				// (int) (y * Tile.HEIGHT_TILE - gameCamera.getyOffset()));
				// g.drawLine((int) (x * Tile.WIDTH_TILE - gameCamera.getxOffset()),
				// (int) (y * Tile.HEIGHT_TILE - gameCamera.getyOffset()),
				// (int) (x * Tile.WIDTH_TILE - gameCamera.getxOffset()),
				// (int) (y * Tile.HEIGHT_TILE - gameCamera.getyOffset()) + 48);
				// g.drawLine((int) (x * Tile.WIDTH_TILE - gameCamera.getxOffset()) + 48,
				// (int) (y * Tile.HEIGHT_TILE - gameCamera.getyOffset()),
				// (int) (x * Tile.WIDTH_TILE - gameCamera.getxOffset()) + 48,
				// (int) (y * Tile.HEIGHT_TILE - gameCamera.getyOffset()) + 48);
				// g.drawLine((int) (x * Tile.WIDTH_TILE - gameCamera.getxOffset()),
				// (int) (y * Tile.HEIGHT_TILE - gameCamera.getyOffset()) + 48,
				// (int) (x * Tile.WIDTH_TILE - gameCamera.getxOffset()) + 48,
				// (int) (y * Tile.HEIGHT_TILE - gameCamera.getyOffset()) + 48);
			}
		}
		
		if(xStart == 0 && yStart == 0) {
			g.drawImage(Assets.gate, 48 - gameCamera.getxOffset(), 48 - gameCamera.getyOffset(), null);
		} else if(xEnd == map.getCurrentMap().getWidthMap() && 
				yEnd == map.getCurrentMap().getHeightMap()) {
			g.drawImage(Assets.gate,
					map.getXEnd() - gameCamera.getxOffset(), 
					map.getYEnd() - gameCamera.getyOffset(), null);
		}
	}
	
	private void drawGUI(Graphics g) {
		g.setColor(Color.gray);
		g.fillRoundRect(11, 11, 150, 13, 10, 10);
		g.setColor(Color.RED);
		g.fillRoundRect(12, 12, (int)((float)entities.getPlayerState().getHp()/(float)Player.HP_MAX * 150), 12, 10, 10);
	}


}
