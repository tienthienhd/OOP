
package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import gfx.Assets;
import gfx.GameCamera;
import manager.EntityManager;
import manager.InputManager;
import manager.MapManager;
import manager.SceneManager;

/**
 * Main panel of game and game loop
 * 
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
	private InputManager inputManager;
	private SceneManager sceneManager;
	private MapManager mapManager;

	// GameCamera
	private GameCamera gameCamera;

	private boolean isStart;
	private boolean onBtnStart;
	private Rectangle btnStartBound = new Rectangle(450, 100, 150, 50);

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
		isStart = true;

		this.mapManager = new MapManager("/map/listmap.txt");
		this.gameCamera = new GameCamera(mapManager, 0, 0);
		mapManager.loadMap(0);

		this.entityManager = new EntityManager(this.mapManager);
		this.entityManager.setGameCamera(gameCamera);

		this.inputManager = new InputManager(this, entityManager);

		this.sceneManager = new SceneManager(this.gameCamera, this.mapManager, this.entityManager);
	}

	// update data of game
	private void update() {
		if (!isStart)
			entityManager.update();
		inputManager.update();
		sceneManager.update();
		mapManager.update();
		if (btnStartBound.contains(inputManager.getX(), inputManager.getY())) {
			onBtnStart = true;
		} else {
			onBtnStart = false;
		}
		if (inputManager.isMousePressed(MouseEvent.BUTTON3)
				&& btnStartBound.contains(inputManager.getX(), inputManager.getY())) {
			isStart = false;
		}
	}

	// draw graphics to buffer
	private void render() {
		if (dbImage == null) {
			dbImage = this.createImage(WIDTH, HEIGHT);
			if (dbImage == null) {
				System.out.println("dbImage is null");
				return;
			} else
				dbg = (Graphics2D) dbImage.getGraphics();
		}

		dbg.setColor(Color.LIGHT_GRAY);
		dbg.fillRect(0, 0, WIDTH, HEIGHT);

		// draw here
		this.sceneManager.draw(dbg);
		if (isStart) {
			this.drawMenu(dbg);
		}

	}

	// draw graphics to screen
	public void paintScreen() {
		Graphics2D g = (Graphics2D) this.getGraphics();
		if (dbImage != null && g != null) {
			g.drawImage(dbImage, 0, 0, WIDTH, HEIGHT, null);
			Toolkit.getDefaultToolkit().sync(); // for linux
			g.dispose();
		}
	}

	private void drawMenu(Graphics g) {
		if (!onBtnStart) {
			g.drawImage(Assets.btnStart, 450, 100, null);
		} else {
			g.drawImage(Assets.btnStartPressed, 450, 100, null);
		}
		g.drawImage(Assets.help, 270, 200, null);

	}

	// game loop
	@Override
	public void run() {
		init();
		Assets.open.start();

		Assets.open.loop(100);
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
		if (thread == null && !running) {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}

	// stop game
	public synchronized void stopGame() {
		if (running) {
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
