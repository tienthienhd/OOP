package gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage sheet;
	private int width, height;
	
	public SpriteSheet(BufferedImage sheet, int width, int height) {
		this.sheet = sheet;
		this.width = width;
		this.height = height;
	}
	
	public BufferedImage crop(int x, int y) {
		return sheet.getSubimage(x, y, width, height);
	}
	
	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	
}
