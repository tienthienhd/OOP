package manager;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

import enity.creature.Direction;
import enity.item.ItemType;
import input.KeyManager;
import input.MouseManager;

public class InputManager extends Manager {
	private KeyManager keyManager;
	private MouseManager mouseManager;
	private InputHandler handler;
	private HashMap<Integer, Boolean> isKeyPressed;

	public InputManager(Component target, InputHandler handler) {
		this.handler = handler;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		target.addKeyListener(keyManager);
		target.addMouseListener(mouseManager);
		this.isKeyPressed = new HashMap<>();
		this.isKeyPressed.put(KeyEvent.VK_ENTER, false);
		this.isKeyPressed.put(KeyEvent.VK_I, false);
		this.isKeyPressed.put(KeyEvent.VK_1, false);
		this.isKeyPressed.put(KeyEvent.VK_2, false);
		this.isKeyPressed.put(KeyEvent.VK_3, false);
		this.isKeyPressed.put(KeyEvent.VK_4, false);
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
			this.isKeyPressed.replace(KeyEvent.VK_ENTER, !isKeyPressed.get(KeyEvent.VK_ENTER));
			handler.playerAttack(isKeyPressed.get(KeyEvent.VK_ENTER));
			keyManager.setKeyReleased(KeyEvent.VK_ENTER);
		} else if(isKeyPressed(KeyEvent.VK_M)) {
			handler.switchMap();
			keyManager.setKeyReleased(KeyEvent.VK_M);
		} else if(isKeyPressed(KeyEvent.VK_I)) {
			this.isKeyPressed.replace(KeyEvent.VK_I, !isKeyPressed.get(KeyEvent.VK_I));
			handler.showInventory(isKeyPressed.get(KeyEvent.VK_I));
			keyManager.setKeyReleased(KeyEvent.VK_I);
		}else if(isKeyPressed(KeyEvent.VK_1)) {
			this.isKeyPressed.replace(KeyEvent.VK_1, !isKeyPressed.get(KeyEvent.VK_1));
			this.handler.useItems(ItemType.BLOOD);
			keyManager.setKeyReleased(KeyEvent.VK_1);
		}else if(isKeyPressed(KeyEvent.VK_2)) {
			this.isKeyPressed.replace(KeyEvent.VK_2, !isKeyPressed.get(KeyEvent.VK_2));
			this.handler.useItems(ItemType.MANA);
			keyManager.setKeyReleased(KeyEvent.VK_2);
		}else if(isKeyPressed(KeyEvent.VK_3)) {
			this.isKeyPressed.replace(KeyEvent.VK_3, !isKeyPressed.get(KeyEvent.VK_3));
			this.handler.useItems(ItemType.WEAPON);
			keyManager.setKeyReleased(KeyEvent.VK_3);
		}else if(isKeyPressed(KeyEvent.VK_4)) {
			this.isKeyPressed.replace(KeyEvent.VK_4, !isKeyPressed.get(KeyEvent.VK_4));
			this.handler.useItems(ItemType.CLOTHES);
			keyManager.setKeyReleased(KeyEvent.VK_4);
		}
		
		if(isMousePressed(MouseEvent.BUTTON1)) {
			this.handler.chooseEntity(getX(), getY());
			this.setMouse(MouseEvent.BUTTON1, false);
		}
		
	}
}
