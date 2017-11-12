package map;

import java.awt.Graphics;
import java.awt.Point;

public class Map {

	private Tile[][] tiles;
	private Point coordStart;
	private Point coordEnd;

	public Map(int[][] tilesId, Point coordStart, Point coordEnd) {
		this.coordStart = coordStart;
		this.coordEnd = coordEnd;
		tiles = new Tile[tilesId.length][tilesId[0].length];
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				tiles[i][j] = new Tile(tilesId[i][j], new Point(j * Tile.WIDTH_TILE, i * Tile.HEIGHT_TILE));
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
}
