import java.util.ArrayList;

public class Play {
    /**
     * This is the main program for the Snake game. Run it with
     *   java -cp .:lanterna-3.0.1-jar Play
     */
    public static void main(String[] args) {
    	
    	int UserPoints = 0;
        // Create the new Gui and start it. This clears the screen
        // and the Gui now controls the screen
        Gui gui = new Gui();
        gui.start();

        // Get the dimensions of the screen
        int screenWidth = gui.getScreenWidth();
        int screenHeight = gui.getScreenHeight();

        // Create the room, the snake and the apple.
        // You will need to change the constructors later to pass more
        // information to the Snake and Apple constructors
        Room room = new Room(screenWidth, screenHeight);
        Snake snake = new Snake(screenWidth/2,screenHeight/2);
        Apple apple1 = new Apple(screenWidth,screenHeight,snake.positions);
        Apple apple2 = new Apple(screenWidth,screenHeight,snake.positions);
        Apple apple3 = new Apple(screenWidth,screenHeight,snake.positions);
        Apple apple4 = new Apple(screenWidth,screenHeight,snake.positions);
        
        // The main loop of the game. If you change the value of
        // the continuePlaying boolean, the main loop will end
        boolean continuePlaying = true;
        int sleepTime = 100;
        int[][] walls = {
        		{screenWidth/4,screenHeight/4,screenWidth-10,screenHeight-10},
        		{screenWidth/2,screenHeight/2+5,screenWidth-10,screenHeight-10},
	
        };
        
        
        while (continuePlaying) {
            // Get a key press from the user
            char c = gui.getKeypress();
            // Do something with the key press
            if (c == 'q') {
                continuePlaying = false;
            }else if (c == Gui.UP_ARROW_KEY) {
            	snake.setDirection("UP");
           } else if (c == Gui.DOWN_ARROW_KEY) {
        	   snake.setDirection("DOWN");
            } else if(c == Gui.LEFT_ARROW_KEY) {
            	snake.setDirection("LEFT");
            } else if(c == Gui.RIGHT_ARROW_KEY) {
            	snake.setDirection("RIGHT");
            }

            // Add your code to move the snake
            // around the screen here.
            snake.move();

            // The redraw part of the game. First clear the screen
            gui.clear();

            // Redraw everything on the screen into an offscreen buffer,
            // including the room, the Snake and the apple
            room.draw(gui);
            apple1.draw(gui);
            snake.draw(gui);
            String Score = "Points = "+ UserPoints;
            int x = 5;
            for(int i=0; i<Score.length(); i++) {
            	gui.drawCharacter(x, 0, Score.charAt(i), "RED", "BLACK");
            	x++;
            }
            if(UserPoints>50) {
            	apple2.draw(gui);
            	for(int i=0;i<1;i++) {
            		room.HorizontalWalls(walls[i][0], walls[i][1], walls[i][2], gui);
            		room.VerticalWalls(walls[i][0], walls[i][1], walls[i][3], gui);
            	}
            }
            if(UserPoints>150) {
            	apple3.draw(gui);
            	for(int i=0;i<2;i++) {
            		room.HorizontalWalls(walls[i][0], walls[i][1], walls[i][2], gui);
            		room.VerticalWalls(walls[i][0], walls[i][1], walls[i][3], gui);
            	}
            }
            if(UserPoints>200) {
            	apple4.draw(gui);
        
            }
           
            
           
            
            // When done redrawing all the elements of the screen, refresh will
            // make the new graphic appear on the screen all at once
            gui.refresh();

            // Detect whether the snake ate the apple, or it hit the wall
            // or it hit its own tail here
            
            Position head = snake.positions.get(0);
            if(head.x == 0 || head.x == screenWidth-1||
            		head.y ==1 || head.y == screenHeight-1) {
            	continuePlaying =  false;
            }
          
            if(UserPoints>50) {
            	for(int i=0;i<1;i++) {
            		if(head.x==walls[i][0]&&(head.y>=walls[i][1]&&head.y<=walls[i][3])) {
                		continuePlaying =  false;
                	}
            		if(head.y==walls[i][1]&&(head.x>=walls[i][0]&&head.x<=walls[i][2])) {
                		continuePlaying =  false;
                	}
            	}
            }
            if(UserPoints>150) {
            	for(int i=0;i<2;i++) {
            		if(head.x==walls[i][0]&&(head.y>=walls[i][1]&&head.y<=walls[i][3])) {
                		continuePlaying =  false;
                	}
            		if(head.y==walls[i][1]&&(head.x>=walls[i][0]&&head.x<=walls[i][2])) {
                		continuePlaying =  false;
                	}
            	}
            }
            
            for(int i = 1; i<snake.positions.size(); i++) {
                	Position p = snake.positions.get(i);
                    if (head.x == p.x && head.y == p.y) {
                        continuePlaying =  false;
                    }
            }
            
            if(apple1.position.collides(snake.positions)) {
            	UserPoints += 10;
            	apple1 = new Apple(screenWidth, screenHeight,snake.positions);
            	snake.grow();
            }
            if(apple2.position.collides(snake.positions)) {
            	UserPoints += 10;
            	apple2 = new Apple(screenWidth, screenHeight,snake.positions);
            	snake.grow();
            }
            if(apple3.position.collides(snake.positions)) {
            	UserPoints += 10;
            	apple3 = new Apple(screenWidth, screenHeight,snake.positions);
            	snake.grow();
            }
            if(apple4.position.collides(snake.positions)) {
            	UserPoints += 10;
            	apple4 = new Apple(screenWidth, screenHeight,snake.positions);
            	snake.grow();
            }
           
           
           
            Gui.sleep(sleepTime);
            if(UserPoints>50)
            	sleepTime=90;
            else if(UserPoints>100)
            	sleepTime=80;
            else if(UserPoints>150)
            	sleepTime=70;
            else if(UserPoints>200)
            	sleepTime=60;
            else if(UserPoints>250)
            	sleepTime=50;
            else if(UserPoints>400)
            	sleepTime=40;
            else if(UserPoints>700)
            	sleepTime=30;
            else if(UserPoints>900)
            	sleepTime=20;
            else if(UserPoints>=1000)
            	sleepTime=10;
            
            
           
            
        }

        /*
         * Since we exited out of the main loop, the user must have died
         * or the user pressed 'q' to quit.
         * Uncomment this part when you're ready to work on Explosions
         */
        Explosion explosion = new Explosion(snake.positions.get(0));
        explosion.draw(gui);
       
        
        String Score = String.valueOf(UserPoints);
        Score = "Points = "+ Score;
        int c = screenWidth/2;
        int d = screenHeight/2;
        for(int i=0; i<Score.length(); i++) {
        	gui.drawCharacter(c, d, Score.charAt(i), "RED", "BLACK");
        	c++;
        }
        
        gui.refresh();
        
        /*
         * Now wait for a keypress to really quit so that the user
         * has time to see the explosion
         */
        while (gui.getKeypress() == (char) 0x0) {
            Gui.sleep(300);
        }

        // Stop the GUI, clearing the screen and restoring the screen
        // back to its original state
        gui.stop();

        // The game has ended since we broke out of the main loop
        // Display the user's score here
    }
    
}
