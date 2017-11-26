package enity.item;

public class Clothes extends Item {

	private int defense;
	private static final int ID = 3;
	
	public Clothes(int x, int y, int defense) {
		super(ID, x, y);
		this.defense = defense;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

}
