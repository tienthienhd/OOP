package enity.creature;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.JFrame;

public class Monster extends Creature {

	private static final int WIDTH = 48;
	private static final int HEIGHT = 48;

	public static final int HP_MAX = 50;

	private static final int DEFAULT_SPEED = 5;
	private static final int DEFAULT_DAMAGE = 10;
	private static final int DEFAULT_DEFENSE = 5;
	private static final int DEFAULT_ATTACK_RADIUS = 50;

	private LinkedList<Point> path;

	public Monster(String name, int x, int y) {
		super(name, x, y, WIDTH, HEIGHT, HP_MAX, DEFAULT_SPEED, DEFAULT_DEFENSE, DEFAULT_DAMAGE, DEFAULT_ATTACK_RADIUS);
		path = new LinkedList<>();
	}

	@Override
	public void update() {
		if (!path.isEmpty()) {
			Point p = path.removeFirst();
			System.out.println(p);
			dx = p.x*this.speed - this.x;
			dy = p.y*this.speed - this.y;
			if(this.x <= 0) dx = 0;
			if(this.y <= 0) dx = 0;
		}
		move();
	}

	private void calcNewPath(Point playerLoc) {
		if(playerLoc.distance(new Point(this.x, this.y)) > this.attackRadius) return;
		path = AStarSearch.findPathAStar(convertPoint(x, y, 20), convertPoint(playerLoc, (double)1/ (double)20));
	}
	private Point convertPoint(Point p, double base) {
		return new Point((int) (p.x * base),(int) (p.y * base));
	}
	private Point convertPoint(int x, int y, double base) {
		return new Point((int) (x * base),(int) (y * base));
	}

	public static void main(String[] args) {
		AStarSearch.initTimer();
		Monster m = new Monster("", 30, 30);
		JFrame frame = new JFrame("Test ai ");
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				m.calcNewPath(e.getPoint());
			}
		});

		frame.setVisible(true);
		Graphics g = frame.getContentPane().getGraphics();

		while (true) {
			m.update();
			g.clearRect(0, 0, 300, 300);
			g.fillRect(m.x, m.y, 20, 20);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}
