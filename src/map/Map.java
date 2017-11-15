package map;

import java.awt.Graphics;
import java.awt.Point;

public class Map {

	private Tile[][] tiles;
	private int xStart;
	private int yStart;
	private int xEnd;
	private int yEnd;

	public Map(int[][] tilesId, int xStart, int yStart, int xEnd, int yEnd) {
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
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				tiles[i][j].draw(g);
			}
		}
	}
	
	public boolean isSolid(int x, int y) {
		if(x < 0 || y < 0 || x >= tiles[0].length || y >= tiles.length) return true;
		return tiles[y][x].isSoLid();
	}
}
