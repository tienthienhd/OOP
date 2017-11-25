package manager;

import java.util.ArrayList;

import manager.EntityManager.CreatureState;

public interface IEntityManager {

	public CreatureState getPlayerState();
	public ArrayList<CreatureState> getMonsterState();
	public boolean isPlayerMoving();
	public ArrayList<Boolean> isMonstersMoving();
}
