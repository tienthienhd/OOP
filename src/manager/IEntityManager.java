package manager;

import java.util.ArrayList;

import manager.EntityManager.EntityState;

public interface IEntityManager {

	public EntityState getPlayerState();
	public ArrayList<EntityState> getMonsterState();
}
