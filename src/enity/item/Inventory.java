package enity.item;

public class Inventory {
	ItemType[] items;
	int[] amountItems;
	
	public Inventory() {
		this.items  = new ItemType[] {ItemType.BLOOD, ItemType.MANA, ItemType.WEAPON, ItemType.CLOTHES};
		this.amountItems = new int[items.length];
	}
	
	public void isAdded(Item item) {
		this.amountItems[item.getType().getValue()] += 1;
	}
}
