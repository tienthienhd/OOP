package gfx;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import game.Main;

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
	public static BufferedImage gate;

	public static BufferedImage[] stoneHumanUp;
	public static BufferedImage[] stoneHumanDown;
	public static BufferedImage[] stoneHumanLeft;
	public static BufferedImage[] stoneHumanRight;
	
	public static ArrayList<BufferedImage> clothes;
	public static ArrayList<BufferedImage> weapons;
	public static BufferedImage blood;
	public static BufferedImage mana;
	public static BufferedImage playerDie;
	
	public static BufferedImage hpBar;
	public static BufferedImage hpState;
	public static BufferedImage mpBar;
	public static BufferedImage mpState;
	
	public static ArrayList<BufferedImage> newFocus;
	public static BufferedImage imghpsmall;
	public static BufferedImage imghpsmall_back;
	public static BufferedImage hpString;
	public static BufferedImage mpString;
	
	public static Clip open;
	public static Clip gameover;
	
	public static BufferedImage hp;
	public static BufferedImage mn;
	public static BufferedImage weapon;
	public static BufferedImage clothe;
	
	public static BufferedImage table;
	
	public static void init() {
		
		int nbTileSet = 0;
		String listName = System.getProperty("user.dir") + "/resource/textures/";
		ArrayList<String> listResource = new ArrayList<>();
		FileReader fr;
		try {
			fr = new FileReader(listName + "listtile.txt");
			BufferedReader br = new BufferedReader(fr);
			nbTileSet = Integer.parseInt(br.readLine());
			while(br.ready()) {
				listResource.add("/textures/" + br.readLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		tileImages = new ArrayList<>();
		for (int i = 0; i < nbTileSet; i++) {
			ArrayList<BufferedImage> images = new ArrayList<>();
			SpriteSheet sheetTiles = new SpriteSheet(ImageLoader.loadImage(listResource.get(i)), WIDTH_TILE,
					HEIGHT_TILE);// Not file
			for (int j = 0; j < sheetTiles.getHeight() / 48; j++) {
				images.add(sheetTiles.crop(0, j * 48));
			}
			tileImages.add(images);
		}
		
		gate = ImageLoader.loadImage("/textures/gate.png");
		
		SpriteSheet sheetHpMpBar = new SpriteSheet(ImageLoader.loadImage("/textures/color_hp_mpback.png"), 124, 18);
		hpBar = sheetHpMpBar.crop(0, 0);
		mpBar = sheetHpMpBar.crop(0, 18);
		
		SpriteSheet sheetHpMpState = new SpriteSheet(ImageLoader.loadImage("/textures/color_hp_mp.png"), 120, 14);
		hpState = sheetHpMpState.crop(0, 0);
		mpState = sheetHpMpState.crop(0, 14);
		


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
		
		playerDie = ImageLoader.loadImage("/textures/player_die.png");

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

		SpriteSheet sheetItems = new SpriteSheet(ImageLoader.loadImage("/textures/item.png"), 24, 24);

		clothes = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			clothes.add(sheetItems.crop(24 * i, 0));
		}

		weapons = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			weapons.add(sheetItems.crop(24 * i, 24));
		}

		blood = sheetItems.crop(24, 48);
		mana = sheetItems.crop(0, 48);
		
		newFocus = new ArrayList<>();
		SpriteSheet focusSheet = new SpriteSheet(ImageLoader.loadImage("/textures/newfocus.png"), 18, 12);
		for(int i = 0; i < 10; i++) {
			newFocus.add(focusSheet.crop(0, 12 * i));
		}
		
		imghpsmall = ImageLoader.loadImage("/textures/imghpsmall.png");
		imghpsmall_back = ImageLoader.loadImage("/textures/imghpsmall_back.png");
		
		SpriteSheet hpMpString = new SpriteSheet(ImageLoader.loadImage("/textures/hp_mp.png"), 32, 21);
		hpString = hpMpString.crop(0, 0);
		mpString = hpMpString.crop(0, 21);
		
		try {
			open = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(
					Main.class.getResourceAsStream("/sound/Open.mid"));
			open.open(inputStream);
			
			gameover = AudioSystem.getClip();
			inputStream = AudioSystem.getAudioInputStream(
					Main.class.getResourceAsStream("/sound/monument.wav"));
			gameover.open(inputStream);
		} catch (Exception e){
			System.err.println(e.getMessage());
		}
		table = ImageLoader.loadImage("/textures/table.png");
		
		hp = ImageLoader.loadImage("/textures/hp.png");
		mn = ImageLoader.loadImage("/textures/mana.png");
		weapon = ImageLoader.loadImage("/textures/weapon.png");
		clothe = ImageLoader.loadImage("/textures/clothe.png");
	}
}
