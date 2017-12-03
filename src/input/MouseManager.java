package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

public class MouseManager implements MouseListener, MouseMotionListener{
	
	HashMap<Integer, Boolean> mouses;
	public int 	x = 0, y = 0;
	public MouseManager() {
		mouses = new HashMap<>();
		mouses.put(MouseEvent.BUTTON1, false);
		mouses.put(MouseEvent.BUTTON3, false);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		mouses.replace(e.getButton(), true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouses.replace(e.getButton(), true);
	}
	
	public boolean isMousePressed(int mouseBtn) {
		return mouses.get(mouseBtn);
	}
	
	public void setMouse(int mouseBtn, boolean isPressed) {
		mouses.replace(mouseBtn, isPressed);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();
		
	}
}

