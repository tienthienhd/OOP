package enity.creature;

public class Player extends Creature {
	
	private static final int WIDTH = 48;
	private static final int HEIGHT = 96;
	
	private static final int HP_MAX = 200;
//	public static final int MP_MAX = 100;

	private static final int DEFAULT_SPEED = 10;
	private static final int DEFAULT_DAMAGE = 20;
	private static final int DEFAULT_DEFENSE = 10;
	private static final int DEFAULT_ATTACK_RADIUS = 50;
	
//	private int mp;

	public Player(String name, int x, int y) {
		super(name, x, y, WIDTH, HEIGHT,
				HP_MAX, DEFAULT_SPEED, DEFAULT_DEFENSE, DEFAULT_DAMAGE, DEFAULT_ATTACK_RADIUS);
		
	}

	@Override
	public void update() {
		move();
		
	}
	
	public void switchMap() {
		//TODO: chuyen map
	}
}
