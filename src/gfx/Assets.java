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
	public static ArrayList<Integer> collisionTile;
	
	public static BufferedImage[] playerRunUp;
	public static BufferedImage[] playerRunDown;
	public static BufferedImage[] playerRunLeft;
	public static BufferedImage[] playerRunRight;

	public static BufferedImage[] playerStandUp;
	public static BufferedImage[] playerStandDown;
	public static BufferedImage[] playerStandRight;
	public static BufferedImage[] playerStandLeft;

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
	
	public static BufferedImage btnStart;
	public static BufferedImage btnStartPressed;
	public static BufferedImage help;
	public static BufferedImage eyesDie;
	
	public static void init() {
		
		int nbTileSet = 0;
		String listName = System.getProperty("user.dir") + "/resource/textures/";
		ArrayList<String> listResource = new ArrayList<>();
		collisionTile = new ArrayList<>();
		FileReader fr;
		try {
			fr = new FileReader(listName + "listtile.txt");
			BufferedReader br = new BufferedReader(fr);
			nbTileSet = Integer.parseInt(br.readLine());
			while(br.ready()) {
				listResource.add("/textures/" + br.readLine());
				collisionTile.add(Integer.parseInt(br.readLine()));
			}
			br.close();
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
		eyesDie = ImageLoader.loadImage("/textures/eyedie.png");

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
//			e.printStackTrace();
		}
		
		table = ImageLoader.loadImage("/textures/table.png");
		
		hp = ImageLoader.loadImage("/textures/hp.png");
		mn = ImageLoader.loadImage("/textures/mana.png");
		weapon = ImageLoader.loadImage("/textures/weapon.png");
		clothe = ImageLoader.loadImage("/textures/clothe.png");
		

		btnStart = ImageLoader.loadImage("/textures/start.png");
		btnStartPressed = ImageLoader.loadImage("/textures/start_pressed.png");
		help = ImageLoader.loadImage("/textures/help.png");
		SpriteSheet sheetPlayerNew = new SpriteSheet(ImageLoader.loadImage("/textures/testplayer.png"), 100, 100);

		playerRunUp = new BufferedImage[4];
		playerRunDown = new BufferedImage[4];
		playerRunLeft = new BufferedImage[4];
		playerRunRight = new BufferedImage[4];

		playerStandUp = new BufferedImage[4];
		playerStandDown = new BufferedImage[4];
		playerStandRight = new BufferedImage[4];
		playerStandLeft = new BufferedImage[4];
		
		playerRunUp[0] = sheetPlayerNew.crop(11, 1287, 92, 78);
		playerRunUp[1] = sheetPlayerNew.crop(105, 1285, 94, 75);
		playerRunUp[2] = sheetPlayerNew.crop(205, 1288, 91, 76);
		playerRunUp[3] = sheetPlayerNew.crop(301, 1286, 93, 75);
		
		playerRunDown[0] =sheetPlayerNew.crop(8, 950, 86, 76);
		playerRunDown[1] =sheetPlayerNew.crop(96, 947, 84, 76);
		playerRunDown[2] =sheetPlayerNew.crop(186, 949, 83, 77);
		playerRunDown[3] =sheetPlayerNew.crop(272, 947, 84, 76);
		
		playerRunLeft[0] =sheetPlayerNew.crop(11, 1120, 65, 74);
		playerRunLeft[1] =sheetPlayerNew.crop(78, 1117, 58, 80);
		playerRunLeft[2] =sheetPlayerNew.crop(140, 1119, 67, 79);
		playerRunLeft[3] =sheetPlayerNew.crop(210, 1116, 61, 82);
		
		playerRunRight[0] =sheetPlayerNew.crop(13, 1459, 66, 75);
		playerRunRight[1] =sheetPlayerNew.crop(84, 1456, 60, 79);
		playerRunRight[2] =sheetPlayerNew.crop(147, 1458, 64, 80);
		playerRunRight[3] =sheetPlayerNew.crop(219, 1457, 64, 84);
		
		playerStandRight[0] = sheetPlayerNew.crop(9, 772, 53, 79);
		playerStandRight[1] = sheetPlayerNew.crop(65, 772, 53, 79);
		playerStandRight[2] = sheetPlayerNew.crop(125, 771, 52, 80);
		playerStandRight[3] = sheetPlayerNew.crop(180, 771, 57, 79);
		
		playerStandLeft[0] = sheetPlayerNew.crop(7, 423, 53, 80);
		playerStandLeft[1] = sheetPlayerNew.crop(67, 422, 51, 80);
		playerStandLeft[2] = sheetPlayerNew.crop(126, 422, 50, 80);
		playerStandLeft[3] = sheetPlayerNew.crop(180, 423, 57, 78);
		
		playerStandUp[0] = sheetPlayerNew.crop(6, 597, 60, 81);
		playerStandUp[1] = sheetPlayerNew.crop(69, 596, 58, 79);
		playerStandUp[2] = sheetPlayerNew.crop(132, 595, 57, 79);
		playerStandUp[3] = sheetPlayerNew.crop(190, 596, 61, 80);
		
		playerStandDown[0] = sheetPlayerNew.crop(6, 249, 51, 80);
		playerStandDown[1] = sheetPlayerNew.crop(67, 249, 48, 80);
		playerStandDown[2] = sheetPlayerNew.crop(124, 247, 50, 81);
		playerStandDown[3] = sheetPlayerNew.crop(181, 249, 54, 79);
	}
}
