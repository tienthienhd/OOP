package enity.item;

public class Weapon extends Item {

	private int damage;	
	
	public Weapon(String name, int x, int y, int width, int height, int damage) {
		super(name, x, y, width, height);
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

}
