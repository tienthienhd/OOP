package gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Assets {
	public static final int WIDTH = 48;
	public static final int HEIGHT = 48;

	public static ArrayList<BufferedImage> tileImages;

	public static void init() {
		tileImages = new ArrayList<>();

		SpriteSheet sheetTile = new SpriteSheet(ImageLoader.loadImage(""), 48, 48);// Not file
		for (int i = 0; i < sheetTile.getHeight() / 48; i++) {
			tileImages.add(sheetTile.crop(0, i * 48));
		}
	}
}
