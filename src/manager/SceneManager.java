package manager;

import java.awt.Graphics;

import gfx.GameCamera;
import scenes.GameScene;
import scenes.Scene;


public class SceneManager extends Manager {

	GameCamera gameCamera;
	Scene currentScene;
	IMapManager map;
	IEntityManager entities;
	
	public SceneManager(GameCamera gameCamera, IMapManager map, IEntityManager entities) {
		this.gameCamera = gameCamera;
		this.map = map;
		this.entities = entities;
		this.currentScene = new GameScene(gameCamera,map, entities);
	}

	@Override
	public void update() {
		this.currentScene.update();
		
	}
	
	public void draw(Graphics g) {
		currentScene.draw(g);
	}

	public Scene getCurrentScene() {
		return currentScene;
	}

	public void setCurrentScene(Scene currentScene) {
		this.currentScene = currentScene;
	}
	
}
