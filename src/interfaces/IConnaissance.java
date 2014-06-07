package interfaces;

import objet.Box;
import objet.Position;

public interface IConnaissance {
    public String getLog();

    public Position getPosition();

    public Box getBox(); //return null if no getBox
}
