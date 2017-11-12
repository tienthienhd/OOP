
package game;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import gfx.Assets;
import manager.EntityManager;
import manager.MapManager;
import manager.SceneManager;

/**
 * Main panel of game and game loop
 * @author tienthien
 *
 */
public class GamePanel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	
	// properties of frame
	public static final String title = "Game RPG ver 1.0";
	public static final int WIDTH = 1000;
	public static final int HEIGHT = WIDTH * 9 / 16; // 9/16 la ti le khung hinh chuan
	
	// thread implement actions of game
	private Thread thread;
	private boolean running;
	
	// graphics process
	private Image dbImage;
	private Graphics2D dbg;
	
	// manager components
	private EntityManager entityManager;
//	private InputManager inputManager;
	private SceneManager sceneManager;
	private MapManager mapManager;
	
	
	// constructor
	public GamePanel() {
		super();
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		this.setFocusable(true);
	}
	
	// initialize of game
	private void init() {
		Assets.init();
		this.entityManager = new EntityManager();
//		this.inputManager = new InputManager(this, entityManager);
		this.mapManager = new MapManager("map/listmap.txt");
//		
//		this.sceneManager.setGameScene(mapManager, entityManager);
//		
		this.sceneManager = new SceneManager(this.mapManager, this.entityManager);
		mapManager.loadMap(1);
	}
	
	// update data of game
	private void update() {
//		entityManager.update();
//		inputManager.update();
//		sceneManager.update();
//		mapManager.update();
	}
	
	// draw graphics to buffer
	private void render() {
		if(dbImage == null) {
			dbImage = this.createImage(WIDTH, HEIGHT);
			if(dbImage == null) {
				System.out.println("dbImage is null");
				return;
			}
			else
				dbg = (Graphics2D) dbImage.getGraphics();
		}
		
		dbg.setColor(Color.LIGHT_GRAY);
		dbg.fillRect(0, 0, WIDTH, HEIGHT);
		
		// draw here
		this.sceneManager.draw(dbg);
		
		
	}
	
	// draw graphics to screen
	public void paintScreen() {
		Graphics2D g = (Graphics2D) this.getGraphics();
		if(dbImage != null && g != null) {
			g.drawImage(dbImage, 0, 0, WIDTH, HEIGHT, null);
			Toolkit.getDefaultToolkit().sync(); // for linux
			g.dispose();
		}
	}
	
	
	// game loop
	@Override
	public void run() {
		init();
		while(running) {
			update();
			render();
			paintScreen();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	// start game is called when run program
	public synchronized void startGame() {
		if(thread == null && !running) {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}
	
	// stop game
	public synchronized void stopGame() {
		if(running) {
			running = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	// active start game when this panel was add on container
	@Override
	public void addNotify() {
		super.addNotify();
		startGame();
	}
	
	
}
