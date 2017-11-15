package manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import gfx.GameCamera;
import map.Map;
import utils.Utils;

public class MapManager extends Manager implements IMapManager {
	private ArrayList<String> listmap;
	private Map currentMap;
	
	public MapManager(String listName) {
		
		listName = System.getProperty("user.dir") + "/resource/" + listName;
		this.listmap = new ArrayList<>();
		FileReader fr;
		try {
			fr = new FileReader(listName);
			BufferedReader br = new BufferedReader(fr);
			while(br.ready()) {
				listmap.add(System.getProperty("user.dir") + "/resource/map/" + br.readLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changeMap(int indexOfMap) {
		if(indexOfMap < 0 || indexOfMap >= listmap.size()) {
			return;
		}
		this.currentMap = loadMap(indexOfMap);
	}
	
	public Map loadMap(int indexOfMap) {
		String filepath = listmap.get(indexOfMap);
		ArrayList<int[]> matrix = new ArrayList<>();
		
		if(Utils.loadMapFromFile(filepath, matrix)) {
			int[][] tileId = new int[matrix.size()][matrix.get(0).length];
			for(int i = 0; i < matrix.size(); i ++) {
				for(int j = 0; j < matrix.get(0).length; j++) {
					tileId[i][j] = matrix.get(i)[j];
				}
			}
			this.currentMap = new Map(tileId, 0, 0, 10, 10);
		}
		return null;
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
}
