package gfx;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Assets {
	public static final int WIDTH_TILE = 48;
	public static final int HEIGHT_TILE = 48;

	public static ArrayList<ArrayList<BufferedImage>> tileImages;

	public static BufferedImage[] playerUp;
	public static BufferedImage[] playerDown;
	public static BufferedImage[] playerLeft;
	public static BufferedImage[] playerRight;

	public static BufferedImage[] dragonUp;
	public static BufferedImage[] dragonDown;
	public static BufferedImage[] dragonRight;
	public static BufferedImage[] dragonLeft;

	public static void init() {
		
		int nbTileSet = 0;
		String listName = System.getProperty("user.dir") + "\\resource\\textures\\";
		ArrayList<String> listResource = new ArrayList<>();
		FileReader fr;
		try {
			fr = new FileReader(listName + "listtile.txt");
			BufferedReader br = new BufferedReader(fr);
			nbTileSet = Integer.parseInt(br.readLine());
			while(br.ready()) {
				listResource.add(listName + br.readLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		tileImages = new ArrayList<>();
		for (int i = 0; i < nbTileSet; i++) {
			ArrayList<BufferedImage> images = new ArrayList<>();
			System.out.println(listResource.get(i));
			String link = listResource.get(i);
			SpriteSheet sheetTiles = new SpriteSheet(ImageLoader.loadImage("/textures/tile0.png"), WIDTH_TILE,
					HEIGHT_TILE);// Not file
			for (int j = 0; j < sheetTiles.getHeight() / 48; j++) {
				images.add(sheetTiles.crop(0, j * 48));
			}
			tileImages.add(images);
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

	}
}
