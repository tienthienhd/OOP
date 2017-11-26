package enity.item;

import enity.Entity;

public class Item extends Entity {
	public static final int DEFAUL_ITEM_WIDTH = 24, DEFAULT_ITEM_HEIGHT = 24;

	private int id;
	
	public Item(int id, int x, int y) {
		super("item", x, y, DEFAUL_ITEM_WIDTH, DEFAULT_ITEM_HEIGHT);
		this.id = id;
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

	public int getId() {
		return id;
	}
	
	

}
