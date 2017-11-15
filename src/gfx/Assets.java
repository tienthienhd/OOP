package gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Assets {
	public static final int WIDTH_TILE = 48;
	public static final int HEIGHT_TILE = 48;

	public static ArrayList<BufferedImage> tileImages;
	public static BufferedImage player;
	public static BufferedImage[] playerUp; 
	public static BufferedImage[] playerDown;
	public static BufferedImage[] playerLeft;
	public static BufferedImage[] playerRight;

	public static void init() {
		
		tileImages = new ArrayList<>();
		SpriteSheet sheetTiles = new SpriteSheet(ImageLoader.loadImage("/textures/tile0.png"), WIDTH_TILE, HEIGHT_TILE);// Not file
		for (int i = 0; i < sheetTiles.getHeight() / 48; i++) {
			tileImages.add(sheetTiles.crop(0, i * 48));
		}
		
		playerUp = new BufferedImage[3];
		playerDown = new BufferedImage[3];
		playerLeft = new BufferedImage[3];
		playerRight = new BufferedImage[3];
		
		SpriteSheet sheetPlayer = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"), 48, 96);
		playerDown[0] = sheetPlayer.crop(0, 0);
		playerDown[1] = sheetPlayer.crop(48, 0);
		playerDown[2] = sheetPlayer.crop(96, 0);
		
		playerLeft[0] = sheetPlayer.crop(0, 96);
		playerLeft[1] = sheetPlayer.crop(48, 96);
		playerLeft[2] = sheetPlayer.crop(96, 96);
		
		playerRight[0] = sheetPlayer.crop(0, 192);
		playerRight[1] = sheetPlayer.crop(48, 192);
		playerRight[2] = sheetPlayer.crop(96, 192);
		
		playerUp[0] = sheetPlayer.crop(0, 288);
		playerUp[1] = sheetPlayer.crop(48, 288);
		playerUp[2] = sheetPlayer.crop(96, 288);
	}
}
