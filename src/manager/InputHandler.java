package manager;

import enity.creature.Direction;

public interface InputHandler {
	public void PlayerMove(Direction dir);

	public void playerAttack();
	
	public void switchMap();
}
