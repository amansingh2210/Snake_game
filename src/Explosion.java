import java.util.ArrayList;

public class Explosion {
    /**
     * This creates an Explosion
     */
	Position pc = null;
	private static final String BACKGROUND_COLOR = "BLUE";
	private static final String FOREGROUND_COLOR = "YELLOW";
	public Explosion(Position p) {
    	pc = p;
    }

    /**
     * Draw the expoosion
     */
    public void draw(Gui gui) {
    	gui.drawLine(pc.x, pc.y, pc.x-2, pc.y, '*', FOREGROUND_COLOR,
    			BACKGROUND_COLOR);
    	gui.drawLine(pc.x, pc.y, pc.x+2, pc.y, '*', FOREGROUND_COLOR,
    			BACKGROUND_COLOR);
    	gui.drawLine(pc.x, pc.y, pc.x, pc.y-2, '*', FOREGROUND_COLOR,
    			BACKGROUND_COLOR);
    	gui.drawLine(pc.x, pc.y, pc.x-2, pc.y+2, '*', FOREGROUND_COLOR,
    			BACKGROUND_COLOR);
    	gui.drawLine(pc.x, pc.y, pc.x-2, pc.y-2, '*', FOREGROUND_COLOR,
    			BACKGROUND_COLOR);
    	gui.drawLine(pc.x, pc.y, pc.x+2, pc.y+2, '*', FOREGROUND_COLOR,
    			BACKGROUND_COLOR);
    	gui.drawLine(pc.x, pc.y, pc.x-2, pc.y+2, '*', FOREGROUND_COLOR,
    			BACKGROUND_COLOR);
    	gui.drawLine(pc.x, pc.y, pc.x+2, pc.y-2, '*', FOREGROUND_COLOR,
    			BACKGROUND_COLOR);
    	
    }
}
