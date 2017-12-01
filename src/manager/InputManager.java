package manager;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import enity.creature.Direction;
import input.KeyManager;
import input.MouseManager;

public class InputManager extends Manager {
	private KeyManager keyManager;
	private MouseManager mouseManager;
	private InputHandler handler;
	private boolean isEnter;

	public InputManager(Component target, InputHandler handler) {
		this.handler = handler;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		target.addKeyListener(keyManager);
		target.addMouseListener(mouseManager);
	}

	public boolean isKeyPressed(int keyCode) {
		return keyManager.isKeyPressed(keyCode);
	}

	public boolean isKeyReleased(int keyCode) {
		return keyManager.isKeyReleased(keyCode);
	}

	public boolean isMousePressed(int mouseBtn) {
		return mouseManager.isMousePressed(mouseBtn);
	}
	
	public void setMouse(int mouseBtn, boolean isPressed) {
		this.mouseManager.setMouse(mouseBtn, isPressed);
	}

	public int getX() {
		return mouseManager.x;
	}

	public int getY() {
		return mouseManager.y;
	}

	@Override
	public void update() {
		if (isKeyPressed(KeyEvent.VK_A)) {
			handler.PlayerMove(Direction.LEFT);
		} else if (isKeyPressed(KeyEvent.VK_D)) {
			handler.PlayerMove(Direction.RIGHT);
		} else if (isKeyPressed(KeyEvent.VK_W)) {
			handler.PlayerMove(Direction.UP);
		} else if (isKeyPressed(KeyEvent.VK_S)) {
			handler.PlayerMove(Direction.DOWN);
		} else if (isKeyPressed(KeyEvent.VK_ENTER)) {
			isEnter = !isEnter;
			handler.playerAttack(isEnter);
			keyManager.setKeyReleased(KeyEvent.VK_ENTER);
		} else if(isKeyPressed(KeyEvent.VK_M)) {
			handler.switchMap();
			keyManager.setKeyReleased(KeyEvent.VK_M);
		}
		
		if(isMousePressed(MouseEvent.BUTTON1)) {
			this.handler.chooseEntity(getX(), getY());
			this.setMouse(MouseEvent.BUTTON1, false);
		}
	}
}
