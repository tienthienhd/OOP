package enity.item;

public class Weapon extends Item {

	private int damage;	
	private static final int ID = 4;
	public Weapon(int x, int y, int damage) {
		super(ID, x, y);
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

}
