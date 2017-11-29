package enity.item;

public enum ItemType {
	WEAPON(2), CLOTHES(3), BLOOD(0), MANA(1);
	
	private int value;
	ItemType(int value){
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
