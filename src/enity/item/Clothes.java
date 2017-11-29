package enity.item;

public class Clothes extends Item {

	private int defense;
	
	public Clothes(int x, int y, int defense) {
		super(ItemType.CLOTHES, x, y);
		this.defense = defense;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

}
