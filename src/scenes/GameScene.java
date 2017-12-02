package scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enity.creature.Monster;
import enity.creature.Player;
import enity.item.ItemType;
import gfx.Animation;
import gfx.Assets;
import gfx.GameCamera;
import manager.EntityManager.CreatureState;
import manager.EntityManager.EntityState;
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
				if(this.entities.getMonsterState().get(0).getName().equals("dragon")){
				this.monstersDown.add(new Animation(Assets.dragonDown, 300));
				this.monstersUp.add(new Animation(Assets.dragonUp, 300));
				this.monstersRight.add(new Animation(Assets.dragonRight, 300));
				this.monstersLeft.add(new Animation(Assets.dragonLeft, 300));
				} else {
					this.monstersDown.add(new Animation(Assets.stoneHumanDown, 300));
					this.monstersUp.add(new Animation(Assets.stoneHumanUp, 300));
					this.monstersRight.add(new Animation(Assets.stoneHumanRight, 300));
					this.monstersLeft.add(new Animation(Assets.stoneHumanLeft, 300));
				}

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
		for (int i = 0; i < isMonstersMoving.size(); i++) {
			if (isMonstersMoving.get(i)) {
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
		if (this.entities.getPlayerState().getHp() <= 0) {
			 drawGameOver(g);
		}
		drawPointer(g);
	}

	private void drawPointer(Graphics g) {
		EntityState stateTarget = this.entities.getPlayerState().getTarget();
		if (stateTarget != null) {
			g.drawImage(Assets.newFocus.get(0), stateTarget.getX() + 15 - gameCamera.getxOffset(),
					stateTarget.getY() - 20 - gameCamera.getyOffset(), null);

			if (stateTarget instanceof CreatureState) {
				CreatureState target = (CreatureState) stateTarget;
				g.drawImage(Assets.imghpsmall_back, 880, 20, null);
				if (target.getHp() >= 0) {
					g.drawImage(Assets.imghpsmall, 880, 20,
							(int) ((float) target.getHp() / (float) Monster.HP_MAX * 104), 14, null);
				}
			}
		}
	}

	private void drawGameOver(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRoundRect(400, 200, 300, 150, 20, 20);
		g.setColor(Color.red);
		g.drawString("Game Over", 450, 250);
	}

	public void drawItem(Graphics g) {
		ArrayList<ItemState> states = this.entities.getItemState();
		for (ItemState state : states) {
			int x = state.getX() - gameCamera.getxOffset();
			int y = state.getY() - gameCamera.getyOffset();
			if (state.getType() == ItemType.BLOOD) {
				g.drawImage(Assets.blood, x, y, null);
			} else if (state.getType() == ItemType.MANA) {
				g.drawImage(Assets.mana, x, y, null);
			} else if (state.getType() == ItemType.CLOTHES) {
				g.drawImage(Assets.clothes.get(0), x, y, null);
			} else if (state.getType() == ItemType.WEAPON) {
				g.drawImage(Assets.weapons.get(0), x, y, null);
			}
		}
	}

	private void drawPlayer(Graphics g) {
		CreatureState state = this.entities.getPlayerState();
		BufferedImage buffer = null;
		if (state.getHp() <= 0) {
			g.drawImage(Assets.playerDie, state.getX() - this.gameCamera.getxOffset(),
					state.getY() - this.gameCamera.getyOffset(), null);
			return;
		}
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

		// g.setColor(Color.blue);
		// g.fillRect(state.getX() - gameCamera.getxOffset(), state.getY() -
		// gameCamera.getyOffset(), 48, 96);

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

			// g.setColor(Color.RED);
			// g.fillRect(state.getX() - gameCamera.getxOffset(), state.getY() -
			// gameCamera.getyOffset(), 48, 48);

			g.drawImage(buffer, state.getX() - gameCamera.getxOffset(), state.getY() - gameCamera.getyOffset(), null);

//			g.setColor(Color.RED);
//			g.fillRoundRect(state.getX() - gameCamera.getxOffset() + 4, state.getY() - gameCamera.getyOffset() - 5,
//					(int) ((float) state.getHp() / (float) Monster.HP_MAX * 40), 3, 5, 5);
			g.setColor(Color.LIGHT_GRAY);
			g.drawString(state.getName(), state.getX() - gameCamera.getxOffset() + 4, state.getY() - gameCamera.getyOffset() - 5);

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

		if (xStart == 0 && yStart == 0) {
			g.drawImage(Assets.gate, map.getXStart() - gameCamera.getxOffset(),
					map.getYStart() - gameCamera.getyOffset(), null);
		} else if (xEnd == map.getCurrentMap().getWidthMap() && yEnd == map.getCurrentMap().getHeightMap()) {
			g.drawImage(Assets.gate, map.getXEnd() - gameCamera.getxOffset(), map.getYEnd() - gameCamera.getyOffset(),
					null);
		}
	}

	private void drawGUI(Graphics g) {
		// g.setColor(Color.gray);
		// g.fillRoundRect(11, 11, 150, 13, 10, 10);
		// g.setColor(Color.RED);
		// g.fillRoundRect(12, 12,
		// (int)((float)entities.getPlayerState().getHp()/(float)Player.HP_MAX * 150),
		// 12, 10, 10);
		CreatureState playerState = this.entities.getPlayerState();
		g.drawImage(Assets.hpString, 0, 11, null);
		g.drawImage(Assets.hpBar, 35, 11, null);
		if (playerState.getHp() >= 0) {
			g.drawImage(Assets.hpState, 37, 13,
					(int) ((float) playerState.getHp() / (float) Player.HP_MAX * 120), 14, null);
		}
		g.drawString(playerState.getHp() + "/" + Player.HP_MAX , 60, 25);
		
		g.drawImage(Assets.mpString, 0, 35, null);
		g.drawImage(Assets.mpBar, 35, 35, null);
		if (this.entities.getPlayerState().getHp() >= 0) {
			g.drawImage(Assets.mpState, 37, 37,
					(int) ((float) entities.getPlayerState().getHp() / (float) Player.HP_MAX * 120), 14, null);
		} //FIXME: hp to mp
	}

}
