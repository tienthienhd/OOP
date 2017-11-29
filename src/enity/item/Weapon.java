package enity.item;

public class Weapon extends Item {

	private int damage;	
	public Weapon(int x, int y, int damage) {
		super(ItemType.WEAPON, x, y);
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

}
