package enity.item;

import enity.Entity;

public class Item extends Entity {
	public static final int DEFAUL_ITEM_WIDTH = 24, DEFAULT_ITEM_HEIGHT = 24;

	private ItemType type;
	
	public Item(ItemType type, int x, int y) {
		super("item", x, y, DEFAUL_ITEM_WIDTH, DEFAULT_ITEM_HEIGHT);
		this.type = type;
	}

	@Override
	public void update() {

	}

	public static int getDefaulItemWidth() {
		return DEFAUL_ITEM_WIDTH;
	}

	public static int getDefaultItemHeight() {
		return DEFAULT_ITEM_HEIGHT;
	}

	public ItemType getType() {
		return type;
	}
	
	

}
