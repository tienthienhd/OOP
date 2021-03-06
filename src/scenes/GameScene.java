package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enity.creature.Monster;
import enity.creature.Player;
import enity.item.Inventory;
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

	Animation playerRunUp;
	Animation playerRunDown;
	Animation playerRunLeft;
	Animation playerRunRight;
	
	Animation playerStandUp;
	Animation playerStandDown;
	Animation playerStandLeft;
	Animation playerStandRight;

	Animation playerAttackUp;
	Animation playerAttackDown;
	Animation playerAttackLeft;
	Animation playerAttackRight;

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
		this.playerDown = new Animation(Assets.playerDown, 150);
		this.playerUp = new Animation(Assets.playerUp, 150);
		this.playerRight = new Animation(Assets.playerRight, 150);
		this.playerLeft = new Animation(Assets.playerLeft, 150);

		this.playerRunUp = new Animation(Assets.playerRunUp, 200);
		this.playerRunRight = new Animation(Assets.playerRunRight, 200);
		this.playerRunDown = new Animation(Assets.playerRunDown, 200);
		this.playerRunLeft = new Animation(Assets.playerRunLeft, 200);

		this.playerStandUp = new Animation(Assets.playerStandUp, 200);
		this.playerStandDown = new Animation(Assets.playerStandDown, 200);
		this.playerStandLeft = new Animation(Assets.playerStandLeft, 200);
		this.playerStandRight = new Animation(Assets.playerStandRight, 200);

		this.playerAttackDown = new Animation(Assets.playerAttackDown, 150);
		this.playerAttackUp = new Animation(Assets.playerAttackUp, 150);
		this.playerAttackLeft = new Animation(Assets.playerAttackLeft, 150);
		this.playerAttackRight = new Animation(Assets.playerAttackRight, 150);

		createAnimationMonster();

	}

	public void createAnimationMonster() {
		this.monstersDown = new ArrayList<>();
		this.monstersUp = new ArrayList<>();
		this.monstersLeft = new ArrayList<>();
		this.monstersRight = new ArrayList<>();

		for (int i = 0; i < entities.getMonsterState().size(); i++) {
			if (true) {// TODO: for each monster
				if (this.entities.getMonsterState().get(0).getName().equals("dragon")) {
					this.monstersDown.add(new Animation(Assets.dragonDown, 200));
					this.monstersUp.add(new Animation(Assets.dragonUp, 200));
					this.monstersRight.add(new Animation(Assets.dragonRight, 200));
					this.monstersLeft.add(new Animation(Assets.dragonLeft, 200));
				} else {
					this.monstersDown.add(new Animation(Assets.stoneHumanDown, 200));
					this.monstersUp.add(new Animation(Assets.stoneHumanUp, 200));
					this.monstersRight.add(new Animation(Assets.stoneHumanRight, 200));
					this.monstersLeft.add(new Animation(Assets.stoneHumanLeft, 200));
				}

			}
		}
	}

	@Override
	public void update() {
		if (this.entities.isSwitchMap()) {
			this.createAnimationMonster();
		}
		if (entities.isPlayerMoving()) {
			moving = true;
			// update animation
			playerRunUp.update();
			playerRunDown.update();
			playerRunRight.update();
			playerRunLeft.update();
			// //playerRight.update();
			// playerLeft.update();
			// playerUp.update();
			// playerDown.update();
		} else {
			moving = false;
			playerStandDown.update();
			playerStandRight.update();
			playerStandLeft.update();
			playerStandUp.update();
		}
		if (entities.isPlayerAttacking()) {
			playerAttackDown.update();
			playerAttackRight.update();
			playerAttackLeft.update();
			playerAttackUp.update();
			playerAttack = true;
		} else {
			playerAttack = false;
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

	boolean moving = false;
	boolean playerAttack = false;

	@Override
	public void draw(Graphics g) {
		drawMap(g);
		drawItem(g);
		drawMonster(g);
		drawPlayer(g);
		drawGUI(g);
		// drawMenu(g);
		// if (this.entities.getPlayerState().getHp() <= 0) {
		// drawGameOver(g);
		// }
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

	@SuppressWarnings("unused")
	private void drawGameOver(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(400, 200, 300, 150, 20, 20);
		g.setColor(Color.red);
		g.drawString("Game Over", 550, 250);
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
			g.drawImage(Assets.eyesDie, state.getX() - this.gameCamera.getxOffset() + 18,
					state.getY() - this.gameCamera.getyOffset() + 32, null);
			return;
		}
		switch (this.entities.getPlayerState().getDirection()) {
		case UP:
			if (moving) {
				buffer = playerRunUp.getCurrentFrame();
			} else if (!moving) {
				buffer = playerStandUp.getCurrentFrame();
			}
			if (playerAttack && this.entities.isCheckCollisionWithMonster()) {
				buffer = playerAttackUp.getCurrentFrame();
			}
			break;
		case DOWN:
			if (moving) {
				buffer = playerRunDown.getCurrentFrame();
			} else if (!moving) {
				buffer = playerStandDown.getCurrentFrame();
			}
			if (playerAttack && this.entities.isCheckCollisionWithMonster()) {
				buffer = playerAttackDown.getCurrentFrame();
			}
			break;
		case RIGHT:
			if (moving) {
				buffer = playerRunRight.getCurrentFrame();
			} else if (!moving) {
				buffer = playerStandRight.getCurrentFrame();
			}
			if (playerAttack && this.entities.isCheckCollisionWithMonster()) {
				buffer = playerAttackRight.getCurrentFrame();
			}
			break;
		case LEFT:
			if (moving) {
				buffer = playerRunLeft.getCurrentFrame();
			} else if (!moving) {
				buffer = playerStandLeft.getCurrentFrame();
			}
			if (playerAttack && this.entities.isCheckCollisionWithMonster()) {
				buffer = playerAttackLeft.getCurrentFrame();
			}
			break;

		}

		// g.setColor(Color.blue);
		// g.fillRect(state.getX() - gameCamera.getxOffset(), state.getY() -
		// gameCamera.getyOffset(), 48, 96);
		g.setColor(Color.WHITE);
		g.drawString("Team 2", state.getX() - gameCamera.getxOffset(), state.getY() - gameCamera.getyOffset());
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

			g.setColor(Color.RED);
			g.fillRoundRect(state.getX() - gameCamera.getxOffset() + 4, state.getY() - gameCamera.getyOffset() - 5,
					(int) ((float) state.getHp() / (float) Monster.HP_MAX * 40), 3, 5, 5);
			g.setColor(Color.LIGHT_GRAY);
			g.drawString(state.getName(), state.getX() - gameCamera.getxOffset() + 4,
					state.getY() - gameCamera.getyOffset() - 10);

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
		if (map.getCurrentMapIndex() == 5) {
			g.drawImage(Assets.home, 1250 - this.gameCamera.getxOffset(), 236 - this.gameCamera.getyOffset(), null);
			g.drawImage(Assets.message, this.entities.getPlayerState().getX() - this.gameCamera.getxOffset(),
					this.entities.getPlayerState().getY() - 100 - this.gameCamera.getyOffset(), null);
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
			g.drawImage(Assets.hpState, 37, 13, (int) ((float) playerState.getHp() / (float) Player.HP_MAX * 120), 14,
					null);
		}
		g.setFont(new Font("Tahoma", Font.PLAIN, 13));
		g.drawString(playerState.getHp() + "/" + Player.HP_MAX, 60, 25);

		g.drawImage(Assets.mpString, 0, 35, null);
		g.drawImage(Assets.mpBar, 35, 35, null);
		if (this.entities.getPlayerState().getMp() >= 0) {
			g.drawImage(Assets.mpState, 37, 37,
					(int) ((float) entities.getPlayerState().getMp() / (float) Player.MP_MAX * 120), 14, null);
			g.drawString(playerState.getMp() + "/" + Player.MP_MAX, 65, 48);
		}

		if (this.entities.isShowInventory()) {
			Inventory inventory = this.entities.getPlayerInventory();
			g.drawImage(Assets.table, 20, 350, null);
			int indexX = 0, indexY = 0;
			for (ItemType type : ItemType.values()) {
				int nb = inventory.getNbItem(type);
				if (nb > 0) {
					if (type == ItemType.BLOOD) {
						g.drawImage(Assets.hp, 23 + indexX * 35, 353 + indexY * 35, null);
						g.drawString(nb + "", 25 + indexX * 35, 380 + indexY * 35);
					} else if (type == ItemType.MANA) {
						g.drawImage(Assets.mn, 23 + indexX * 35, 353 + indexY * 35, null);
						g.drawString(nb + "", 25 + indexX * 35, 380 + indexY * 35);
					} else if (type == ItemType.WEAPON) {
						g.drawImage(Assets.weapon, 23 + indexX * 35, 353 + indexY * 35, null);
						g.drawString(nb + "", 25 + indexX * 35, 380 + indexY * 35);
					} else if (type == ItemType.CLOTHES) {
						g.drawImage(Assets.clothe, 23 + indexX * 35, 353 + indexY * 35, null);
						g.drawString(nb + "", 25 + indexX * 35, 380 + indexY * 35);
					}
					indexX++;
				}
			}
			// g.drawImage(Assets.hp,23,352,null);
			// g.drawString("1", 25, 380);

		}
	}

}
