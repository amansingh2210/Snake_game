
import java.util.ArrayList;

public class Position {
    /**
     * The x-coordinate for this position
     */
    public int x;

    /**
     * The y-coordinate for this position
     */
    public int y;

    /**
     * Make a new position object that represents (x, y) coordinates
     * of a single point
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
    * Returns true if this position collides with any of the ArrayList
    * of positions, false otherwise
    */
    public boolean collides(ArrayList<Position> positions) {
        for(Position p: positions) {
            if (x == p.x && y == p.y) {
                return true;
            }
        }
        return false;
    }
}
