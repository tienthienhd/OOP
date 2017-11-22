package map;

import java.awt.image.BufferedImage;

import gfx.Assets;

public class Tile {
	// size piece
	public static final int HEIGHT_TILE = 48;
	public static final int WIDTH_TILE = 48;

	private int id;
	private int x, y;
	private BufferedImage image;

	public Tile(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.image = Assets.tileImages.get(id - 1);
	}

	// check entity
	public boolean isSoLid() {
		if (id >= 26) {
			return true;
		}
		return false;
	}

	
//	//draw image
//	public void draw(Graphics g,int x, int y) {
//		g.drawImage(image, x, y, null);
//	}
//
//
//	// draw image
//	public void draw(Graphics g) {
//
//		g.drawImage(image, x, y, null);
//
//		g.setColor(Color.red);
//		g.drawString(this.id + "", x + 20, y + 20);
//
//		g.drawLine(x, y, x + 48, y);
//		g.drawLine(x, y, x, y + 48);
//		g.drawLine(x + 48, y, x + 48, y + 48);
//		g.drawLine(x, y + 48, x + 48, y + 48);
//		g.setColor(Color.GREEN);
//		g.drawString(y / 48 + "," + x / 48, x, y + 10);
//
//	}

	public BufferedImage getImage() {
		return this.image;
	}

	public int getId() {
		return id;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	
}
