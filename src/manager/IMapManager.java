package manager;

import map.Map;

public interface IMapManager {
	public Map getCurrentMap();
	public boolean isSolid(int x, int y);
	public boolean switchNextMap(int x, int y);
	public boolean switchPrevMap(int x, int y);
	public int getXStart();
	public int getYStart();
	public int getXEnd();
	public int getYEnd();
}
