package gfx;

import enity.Entity;
import manager.IMapManager;
import map.Tile;

public class GameCamera {
	
	public static int CAMERA_WIDTH = 1000;
	public static int CAMERA_HEIGHT = CAMERA_WIDTH * 9 / 16;
	
	private IMapManager mapManager;
	private int xOffset, yOffset;
	public GameCamera(IMapManager mapManager, int xOffset, int yOffset) {
		this.mapManager = mapManager;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void checkBlankSpace() {
		if(xOffset < 0) {
			xOffset = 0;
		}else if(xOffset > mapManager.getCurrentMap().getWidthMap() * Tile.WIDTH_TILE - CAMERA_WIDTH) {
			xOffset = mapManager.getCurrentMap().getWidthMap() * Tile.WIDTH_TILE - CAMERA_WIDTH;
		}
		
		if(yOffset < 0) {
			yOffset = 0;
		}else if(yOffset > mapManager.getCurrentMap().getHeightMap() * Tile.HEIGHT_TILE - CAMERA_HEIGHT) {
			yOffset = mapManager.getCurrentMap().getHeightMap() * Tile.HEIGHT_TILE - CAMERA_HEIGHT;
		}		
	}
	
	public void centerOnEntity(Entity e) {
		xOffset  = e.getX() - CAMERA_WIDTH/2 + e.getWidth()/2;
		yOffset = e.getY() - CAMERA_HEIGHT/2 + e.getHeight()/2;
		checkBlankSpace();
 	}
	
	//setter,getter
		public int getxOffset() {
			return xOffset;
		}

		public int getyOffset() {
			return yOffset;
		}

}
