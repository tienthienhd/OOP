package manager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import map.Map;
import utils.Utils;

public class MapManager extends Manager implements IMapManager {
	private ArrayList<String> listMap;
	private ArrayList<String> listMonsters;
	private Map currentMap;
	private int indexCurrentMap;
	
	public MapManager(String listName) {
		
//		listName = "resource/" + listName;
		this.listMap = new ArrayList<>();
		this.listMonsters = new ArrayList<>();
//		FileReader fr;
		try {
			InputStream in = MapManager.class.getResourceAsStream(listName);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			while(br.ready()) {
				listMap.add("/map/" + br.readLine());
				listMonsters.add("/map/" + br.readLine());
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean changeMap(int indexOfMap) {
		if(indexOfMap < 0 || indexOfMap >= listMap.size()) {
			return false;
		}
		loadMap(indexOfMap);
		return true;
	}
	
	public void loadMap(int indexOfMap) {
		this.indexCurrentMap = indexOfMap;
		String filepath = listMap.get(indexOfMap);
		ArrayList<int[]> matrix = new ArrayList<>();
		
		int tileSet;
		if((tileSet = Utils.loadMapFromFile(filepath, matrix)) >= 0) {
			int[][] tileId = new int[matrix.size()][matrix.get(0).length];
			for(int i = 0; i < matrix.size(); i ++) {
				for(int j = 0; j < matrix.get(0).length; j++) {
					tileId[i][j] = matrix.get(i)[j];
				}
			}
			this.currentMap = new Map(tileSet, tileId, 0, 0 + 24, 2400 - 96, 1344 - 96);
//			System.out.print(filepath+"    "+indexOfMap);
		}
	}
	
	@Override
	public void update() {

	}

	@Override
	public Map getCurrentMap() {

		return this.currentMap;
	}
	
	@Override
	public boolean isSolid(int x, int y) {
		return currentMap.isSolid(x, y);
	}
	
	@Override
	public boolean switchNextMap(int x, int y) {
		if(this.currentMap.checkOnGateNext(x, y)) {
			return changeMap(this.indexCurrentMap+1);
		}
		
		return false;
	}
	
	@Override
	public boolean switchPrevMap(int x, int y) {
		if(this.currentMap.checkOnGatePrev(x, y)) {
			return changeMap(this.indexCurrentMap-1);
		}
		return false;
	}
	
	@Override
	public int getXStart() {
		return currentMap.getXStart();
	}
	@Override 
	public int getYStart() {
		return currentMap.getYStart();
	}

	@Override
	public int getXEnd() {
		return currentMap.getXEnd();
	}

	@Override
	public int getYEnd() {
		return currentMap.getYEnd();
	}
	
	@Override
	public ArrayList<String> getMonsterMap() {
		return this.listMonsters;
	}

	@Override
	public int getCurrentMapIndex() {
		return this.indexCurrentMap;
	}
}

