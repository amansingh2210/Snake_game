import java.util.ArrayList;

import javax.sound.midi.Soundbank;

public class Snake {
	 private static final String BACKGROUND_COLOR = "BLUE";
	 private static final String FOREGROUND_COLOR = "WHITE";
    /**
     * The Snake is represented as a list of positions.
     * The head is at position 0.
     */
    public ArrayList<Position> positions = new ArrayList<Position>();

    /**
     * The direction of motion of the snake can be either
     * UP, DOWN, LEFT or RIGHT. Default is to the right.
     */
    private String direction = "RIGHT";

    /**
     * Create a new Snake object. You will need to add to the
     * constructor here to pass it more information
     * @param j 
     * @param i 
     */
    public Snake(int i, int j) {
    	Position head = new Position(i, j);
    	Position tail1 = new Position(i-1,j);
    	Position tail2 = new Position(i-2, j);
    	positions.add(head);
    	positions.add(tail1);
    	positions.add(tail2);
    }

 
	/**
     * This method should draw the snake
     */
    public void draw(Gui gui) {
        // Add your code here
    	for(int i = 0; i<positions.size() ; i++ ){
    		Position e = positions.get(i);
    		if(i==0) {
    			 if(direction.equals("RIGHT")) {
    				 gui.drawCharacter(e.x, e.y, '>', FOREGROUND_COLOR, BACKGROUND_COLOR);
    		        }else if(direction.equals("LEFT")) {
    		        	gui.drawCharacter(e.x, e.y, '<', FOREGROUND_COLOR, BACKGROUND_COLOR);
    		        } else if(direction.equals("UP")) {
    		        	gui.drawCharacter(e.x, e.y, '^', FOREGROUND_COLOR, BACKGROUND_COLOR);
    		        }
    		        if(direction.equals("DOWN")) {
    		        	gui.drawCharacter(e.x, e.y, 'V', FOREGROUND_COLOR, BACKGROUND_COLOR);
    		        }
    		}
    		else
    			gui.drawCharacter(e.x, e.y, '+', FOREGROUND_COLOR, BACKGROUND_COLOR);
    	}
    }

    /**
     * Move the snake in its direction of motion
     */
    public void move() {
    	
        int length = positions.size();
    	for(int k = length-1; k > 0; k--) {
    		int x=positions.get(k-1).x;
    		int y=positions.get(k-1).y;
    		Position p = new Position(x,y);
    		positions.set(k, p);
    	}
    	Position head = positions.get(0);
        if(direction.equals("RIGHT")) {
        	head.x = head.x+1;
        	head.y = head.y;
        }else if(direction.equals("LEFT")) {
        	head.x = head.x-1;
        	head.y = head.y;
        } else if(direction.equals("UP")) {
        	head.x = head.x;
        	head.y = head.y-1;
        } else if(direction.equals("DOWN")) {
        	head.x = head.x;
        	head.y = head.y+1;
        }
    }
    public void setDirection (String direction) {
    	if(this.direction.equals("RIGHT")&&(direction.equals("UP")||direction
    			.equals("DOWN"))) {
    		this.direction = direction;
        }else if(this.direction.equals("LEFT")&&(direction.equals("UP")||direction
    			.equals("DOWN"))) {
    		this.direction = direction;
        } else if(this.direction.equals("UP")&&(direction.equals("LEFT")||direction
    			.equals("RIGHT"))) {
    		this.direction = direction;
        } else if(this.direction.equals("DOWN")&&(direction.equals("LEFT")||direction
    			.equals("RIGHT"))) {
    		this.direction = direction;
        }
    	
    }
    
    public void grow() {
    	int length = positions.size();
    	Position tail = positions.get(length-1);
    	Position newtail = null;
    	if(direction.equals("RIGHT")) {
        	newtail = new Position(tail.x-1,tail.y);
        	
        }else if(direction.equals("LEFT")) {
        	newtail = new Position(tail.x+1,tail.y);
        } else if(direction.equals("UP")) {
        	newtail = new Position(tail.x,tail.y+1);
        	
        } else if(direction.equals("DOWN")) {
        	newtail = new Position(tail.x,tail.y-1);
        	
        }
    	positions.add(newtail);
	}
    
}
