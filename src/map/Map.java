package map;

import java.awt.Graphics;
import java.awt.Point;

import gfx.GameCamera;

public class Map {

	private Tile[][] tiles;
	private int xStart;
	private int yStart;
	private int xEnd;
	private int yEnd;
	
	private GameCamera gameCamera;

	public Map(GameCamera gameCamera, int[][] tilesId, int xStart, int yStart, int xEnd, int yEnd) {
		this.gameCamera = gameCamera;
		this.xStart = xStart;
		this.yStart = yStart;
		this.xEnd = xEnd;
		this.yEnd = yEnd;
		tiles = new Tile[tilesId.length][tilesId[0].length];
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				tiles[i][j] = new Tile(tilesId[i][j], j * Tile.WIDTH_TILE, i * Tile.HEIGHT_TILE);
			}
		}
	}

	// draw map
	public void draw(Graphics g) {

		int xStart = (int) Math.max(0, gameCamera.getxOffset() / Tile.WIDTH_TILE);
		int xEnd = (int) Math.min(tiles[0].length, (gameCamera.getxOffset() + gameCamera.CAMERA_WIDTH ) / Tile.WIDTH_TILE + 1);
		int yStart = (int) Math.max(0, gameCamera.getyOffset() / Tile.HEIGHT_TILE);
		int yEnd = (int) Math.min(tiles.length, (gameCamera.getyOffset() + gameCamera.CAMERA_HEIGHT) / Tile.HEIGHT_TILE + 1);
		
		for(int y = yStart;y < yEnd;y++){
			for(int x = xStart;x < xEnd;x++){
				tiles[y][x].draw(g, (int) (x * Tile.WIDTH_TILE - gameCamera.getxOffset()),
						(int) (y * Tile.HEIGHT_TILE - gameCamera.getyOffset()));
			}
		}
	}
	

	public int getWidthMap() {
		return tiles[0].length;
	}
	
	public int getHeightMap() {
		return tiles.length;

	public boolean isSolid(int x, int y) {
		if(x < 0 || y < 0 || x >= tiles[0].length || y >= tiles.length) return true;
		return tiles[y][x].isSoLid();

	}
}
