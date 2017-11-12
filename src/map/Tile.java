package map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gfx.Assets;

public class Tile {
	//size piece
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
	
	//check entity
	public boolean isSoLid() {
		return false;
	}
	
	public void draw(Graphics g) {
		g.drawImage(image, x, y, null);
	}
}
