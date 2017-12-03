package map;

import java.awt.Point;

public class Map {

	private Tile[][] tiles;
	private int xStart;
	private int yStart;
	private int xEnd;
	private int yEnd;
	

	public Map(int tileSetIndex, int[][] tilesId, int xStart, int yStart, int xEnd, int yEnd) {
		this.xStart = xStart;
		this.yStart = yStart;
		this.xEnd = xEnd;
		this.yEnd = yEnd;
		tiles = new Tile[tilesId.length][tilesId[0].length];
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				tiles[i][j] = new Tile(tileSetIndex, tilesId[i][j], j * Tile.WIDTH_TILE, i * Tile.HEIGHT_TILE);
			}
		}
	}

	
	public boolean checkOnGateNext(int x, int y) {
		Point p = new Point(x, y);
		
		if(p.distance(new Point(this.xEnd, this.yEnd)) > 50) {
			return false;
		}
		return true;
	}
	
	public boolean checkOnGatePrev(int x, int y) {
		Point p = new Point(x, y);
		
		if(p.distance(new Point(this.xStart, this.yStart)) > 50) {
			return false;
		}
		return true;
	}
	

	public int getWidthMap() {
		return tiles[0].length;
	}
	
	public int getHeightMap() {
		return tiles.length;
	}

	public boolean isSolid(int x, int y) {
		if(x < 0 || y < 0 || x >= tiles[0].length || y >= tiles.length) return true;
		return tiles[y][x].isSoLid();

	}
	
	public Tile getTile(int y, int x) {
		return tiles[y][x];
	}

	public int getXStart() {
		return xStart;
	}

	public int getYStart() {
		return yStart;
	}
	
	public int getXEnd() {
		return xEnd;
	}
	
	public int getYEnd() {
		return yEnd;
	}
}
