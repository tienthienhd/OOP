package manager;

import java.awt.Graphics;

import scenes.GameScene;
import scenes.Scene;


public class SceneManager extends Manager {

	Scene currentScene;
	IMapManager map;
	IEntityManager entities;
	
	public SceneManager(IMapManager map, IEntityManager entities) {
		this.map = map;
		this.entities = entities;
		this.currentScene = new GameScene(map, entities);
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
