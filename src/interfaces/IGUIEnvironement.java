package interfaces;

import java.util.Map;

public interface IGUIEnvironement {
	public Map<Position, MapObject> update();
	public String getLog(int robotId);

}
