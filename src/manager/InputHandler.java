package manager;

import enity.Entity;
import enity.creature.Direction;
import enity.item.ItemType;

public interface InputHandler {
	public void PlayerMove(Direction dir);

	public void playerAttack(boolean isAttacking);
	
	public void switchMap();
	
	public Entity chooseEntity(int x, int y);

	public void showInventory(Boolean isShow);
	
	public void useItems(ItemType type);
}
