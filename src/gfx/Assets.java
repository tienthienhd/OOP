package gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Assets {
	public static final int WIDTH_TILE = 48;
	public static final int HEIGHT_TILE = 48;

	public static ArrayList<BufferedImage> tileImages;

	public static BufferedImage[] playerUp;
	public static BufferedImage[] playerDown;
	public static BufferedImage[] playerLeft;
	public static BufferedImage[] playerRight;

	public static BufferedImage[] dragonUp;
	public static BufferedImage[] dragonDown;
	public static BufferedImage[] dragonRight;
	public static BufferedImage[] dragonLeft;

	public static BufferedImage[] stoneHumanUp;
	public static BufferedImage[] stoneHumanDown;
	public static BufferedImage[] stoneHumanLeft;
	public static BufferedImage[] stoneHumanRight;

	public static ArrayList<BufferedImage> clothes;
	public static ArrayList<BufferedImage> weapons;
	public static BufferedImage blood;
	public static BufferedImage mana;

	public static void init() {

		tileImages = new ArrayList<>();
		SpriteSheet sheetTiles = new SpriteSheet(ImageLoader.loadImage("/textures/tile0.png"), WIDTH_TILE, HEIGHT_TILE);// Not
																														// file
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

		dragonUp = new BufferedImage[4];
		dragonDown = new BufferedImage[4];
		dragonLeft = new BufferedImage[4];
		dragonRight = new BufferedImage[4];

		SpriteSheet sheetMonster = new SpriteSheet(ImageLoader.loadImage("/textures/dragon.png"), 48, 48);
		for (int i = 0; i < 4; i++) {
			dragonDown[i] = sheetMonster.crop(48 * i, 0);
		}
		for (int i = 0; i < 4; i++) {
			dragonLeft[i] = sheetMonster.crop(48 * i, 48);
		}
		for (int i = 0; i < 4; i++) {
			dragonRight[i] = sheetMonster.crop(48 * i, 96);
		}
		for (int i = 0; i < 4; i++) {
			dragonUp[i] = sheetMonster.crop(48 * i, 144);
		}

		stoneHumanDown = new BufferedImage[4];
		stoneHumanUp = new BufferedImage[4];
		stoneHumanRight = new BufferedImage[4];
		stoneHumanLeft = new BufferedImage[4];

		SpriteSheet sheetStoneHuman = new SpriteSheet(ImageLoader.loadImage("/textures/stonehuman.png"), 48, 48);
		for (int i = 0; i < 4; i++) {
			stoneHumanDown[i] = sheetStoneHuman.crop(48 * i, 0);
		}
		for (int i = 0; i < 4; i++) {
			stoneHumanLeft[i] = sheetStoneHuman.crop(48 * i, 48);
		}
		for (int i = 0; i < 4; i++) {
			stoneHumanRight[i] = sheetStoneHuman.crop(48 * i, 96);
		}
		for (int i = 0; i < 4; i++) {
			stoneHumanUp[i] = sheetStoneHuman.crop(48 * i, 144);
		}

		SpriteSheet sheetItems = new SpriteSheet(ImageLoader.loadImage("/textures/items.png"), 48, 48);

		clothes = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			clothes.add(sheetItems.crop(48 * i, 0));
		}

		weapons = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			weapons.add(sheetItems.crop(48 * i, 48));
		}

		blood = sheetItems.crop(48, 96);
		mana = sheetItems.crop(0, 96);
	}
}
