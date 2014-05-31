package implementation;

import java.util.HashMap;
import java.util.Map;

import interfaces.IGUIEnvironement;
import interfaces.MapObject;
import interfaces.Position;

public class GUIEnvironementImpl implements IGUIEnvironement {
	
	Map<Position, MapObject> map = new HashMap<Position, MapObject>();
	
	@Override
	public Map<Position, MapObject> update() {
		for(int i=0; i<200; i++) {
			for(int j=0; j<200; j++) {
				Position position = new Position(i, j);
				map.put(position, MapObject.RobotEmpty);
			}
		}
		return map;
	}
}
