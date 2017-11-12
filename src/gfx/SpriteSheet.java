package gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage sheet;
	private int width, height;
	
	public SpriteSheet(BufferedImage sheet, int widthTile, int heightTile) {
		this.sheet = sheet;
		this.width = widthTile;
		this.height = heightTile;
	}
	
	public BufferedImage crop(int x, int y) {
		return sheet.getSubimage(x, y, width, height);
	}
	
	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}

	public int getWidth() {
		return sheet.getWidth();
	}

	public int getHeight() {
		return sheet.getHeight();
	}
	
	
}
