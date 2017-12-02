package manager;

import java.util.ArrayList;

import enity.item.Inventory;
import manager.EntityManager.CreatureState;
import manager.EntityManager.ItemState;

public interface IEntityManager {

	public CreatureState getPlayerState();
	public ArrayList<CreatureState> getMonsterState();
	public boolean isPlayerMoving();
	public boolean isPlayerAttacking();
	public ArrayList<Boolean> isMonstersMoving();
	ArrayList<ItemState> getItemState();
	public boolean isSwitchMap();
	public boolean isShowInventory();
	public Inventory getPlayerInventory();
}
