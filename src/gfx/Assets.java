package gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Assets {
	public static final int WIDTH_TILE = 48;
	public static final int HEIGHT_TILE = 48;

	public static ArrayList<BufferedImage> tileImages;
	public static BufferedImage player;

	public static void init() {
		
		tileImages = new ArrayList<>();
		SpriteSheet sheetTiles = new SpriteSheet(ImageLoader.loadImage("/textures/tile0.png"), WIDTH_TILE, HEIGHT_TILE);// Not file
		for (int i = 0; i < sheetTiles.getHeight() / 48; i++) {
			tileImages.add(sheetTiles.crop(0, i * 48));
		}
		
		SpriteSheet sheetPlayer = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"), 48, 96);
		player = sheetPlayer.crop(0, 0);
	}
}
