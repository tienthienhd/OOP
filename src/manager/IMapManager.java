package manager;

import map.Map;

public interface IMapManager {
	public Map getCurrentMap();
	public boolean isSolid(int x, int y);
	public boolean switchMap(int x, int y);
	public int getXStart();
	public int getYStart();
}
