package map;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import gfx.Assets;

public class Tile {
	// size piece
	public static final int HEIGHT_TILE = 48;
	public static final int WIDTH_TILE = 48;

	private int id;
	private Point coord;
	private BufferedImage image;

	public Tile(int id, Point coord) {
		this.id = id;
		this.coord = coord;
		this.image = Assets.tileImages.get(id - 1);
	}

	// check entity
	public boolean isSoLid() {
		return false;
	}
	
	//draw image
	public void draw(Graphics g) {
		g.drawImage(image, coord.x, coord.y, null);
	}
}
