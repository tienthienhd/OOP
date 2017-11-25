package enity.item;

public class Clothes extends Item {

	private int defense;

	public Clothes(String name, int x, int y, int width, int height, int defense) {
		super(name, x, y, width, height);
		this.defense = defense;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

}
