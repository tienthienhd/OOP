package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class KeyManager implements KeyListener {
	HashMap<Integer, Boolean> keys;
	
	public KeyManager() {
		keys = new HashMap<Integer, Boolean>();
		keys.put(KeyEvent.VK_UP, false);
		keys.put(KeyEvent.VK_DOWN, false);
		keys.put(KeyEvent.VK_LEFT, false);
		keys.put(KeyEvent.VK_RIGHT, false);
		keys.put(KeyEvent.VK_A, false);
		keys.put(KeyEvent.VK_W, false);
		keys.put(KeyEvent.VK_D, false);
		keys.put(KeyEvent.VK_S, false);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keys.containsKey(keyCode)) {
			keys.replace(keyCode, true);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keys.containsKey(keyCode)) {
			keys.replace(keyCode, false);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isKeyPressed(int keyCode) {
		if(keys.containsKey(keyCode)) {
			return keys.get(keyCode);
		}
		return false;
	}
}
