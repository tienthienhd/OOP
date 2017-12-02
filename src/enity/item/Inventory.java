package enity.item;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Item> listItem;
	private ItemType[] items;
	private int[] amountItems;

	public Inventory() {
		this.listItem = new ArrayList<>();
		this.items = new ItemType[] { ItemType.BLOOD, ItemType.MANA, ItemType.WEAPON, ItemType.CLOTHES };
		this.amountItems = new int[items.length];
	}

	public void added(Item item) {
		this.listItem.add(item);
		this.amountItems[item.getType().getValue()] += 1;
	}

	public int getNbItem(ItemType type) {
		return this.amountItems[type.getValue()];
	}

		
	public void reduceAmount(ItemType type) {
		if (this.amountItems[type.getValue()] > 0)
			this.amountItems[type.getValue()]--;
	}
	
	public Item getItem(ItemType type) {
		Item item = null;
		for(Item i : listItem) {
			if(i.getType() == type) {
				item = i;
			}
		}
		listItem.remove(item);
		return item;
	}
}
