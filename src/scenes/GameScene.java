package scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gfx.Animation;
import gfx.Assets;
import gfx.GameCamera;
import manager.EntityManager.EntityState;
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
		g.drawImage(buffer, state.getX() - gameCamera.getxOffset(), state.getY() - gameCamera.getyOffset(), null);

	}

	private void drawMonster(Graphics g) {
		ArrayList<EntityState> states = this.entities.getMonsterState();

	}

	private void drawMap(Graphics g) {
		map.getCurrentMap().draw(g);

	}

}
