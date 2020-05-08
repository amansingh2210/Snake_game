import java.time.Year;
import java.util.ArrayList;

public class Apple {
	
	 private static final String BACKGROUND_COLOR = "BLUE";
	 private static final String FOREGROUND_COLOR = "RED";
	 
    /**
     * This creates an Apple
     */
	
	Position position = null;
	
    public Apple(int screenWidth, int screenHeight,ArrayList<Position> positions) {
    	position = randPosition(positions,screenWidth, screenHeight);
    }

    /**
     * Draw the apple
     */
    public void draw(Gui gui) {
    	gui.drawCharacter(position.x, position.y, '@', FOREGROUND_COLOR, BACKGROUND_COLOR);
    }
    
    public Position randPosition(ArrayList<Position> positions,int screenWidth, int screenHeight) {
        int xMax = screenWidth-2; 
        int xMin = 1; 
        int yMax = screenHeight-2; 
        int yMin = 2; 
        int xrange = xMax - xMin + 1; 
        int yrange = yMax - yMin + 1;
        boolean collide = true;
        Position p = null;
         while (collide) { 
             int Xrand = (int)(Math.random() * xrange) + xMin; 
             int Yrand = (int)(Math.random() * yrange) + yMin; 
             if(Xrand==screenWidth/4||Yrand==screenHeight/4||Xrand==(screenWidth/2)
            		 ||Yrand ==screenHeight/2+5) {
             continue;
             }
              p = new Position(Xrand, Yrand);
             collide = p.collides(positions);
         }
		return p; 
    }
}
