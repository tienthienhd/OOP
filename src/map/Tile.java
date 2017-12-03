package map;

import java.awt.image.BufferedImage;

import gfx.Assets;

public class Tile {
	// size piece
	public static final int HEIGHT_TILE = 48;
	public static final int WIDTH_TILE = 48;
	
	private int tileSetIndex;
	
	private int id;
	private int x, y;
	private BufferedImage image;

	public Tile(int tileSetIndex, int id, int x, int y) {
		this.tileSetIndex = tileSetIndex;
		this.id = id;
		this.x = x;
		this.y = y;
		this.image = Assets.tileImages.get(tileSetIndex).get(id - 1);
	}

	// check entity
	public boolean isSoLid() {
		if (id >= Assets.collisionTile.get(tileSetIndex)) {
			return true;
		}
		return false;
	}


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
