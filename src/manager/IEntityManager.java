package manager;

import java.util.ArrayList;

import manager.EntityManager.CreatureState;
import manager.EntityManager.ItemState;

public interface IEntityManager {

	public CreatureState getPlayerState();
	public ArrayList<CreatureState> getMonsterState();
//	public ArrayList<ItemState> getWeapons();
//	public ArrayList<ItemState> getClothes();
//	public ArrayList<ItemState> getManas();
//	public ArrayList<ItemState> getBloods();
	public boolean isPlayerMoving();
	public ArrayList<Boolean> isMonstersMoving();
	ArrayList<ItemState> getItemState();
}
