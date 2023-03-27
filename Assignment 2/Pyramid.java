/*
 * File: Pyramid.java
 * 
 * Pyramid program draws a pyramid consisting of bricks arranged in horizontal rows
 * The number of bricks in each row decreases by one as you move up the pyramid
 * The pyramid should be centered at the bottom of the window and should use constants for: bricks in base, brick height and width
 * These values are saved as static ints, but program should work for any value
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	
	public void run() {//draws a pyramid that is centered in the bottom of the canvas
		
		//this cycle draws the given number of rows of the pyramid which is equal to number of brick in base
		for(int row=0; row<BRICKS_IN_BASE; row++) { 
			//this cycle draws the rows themselves and has a way of counting the number of bricks that need to be laid (BRICKS_IN_BASE-row)
			for(int brickN=0; brickN<(BRICKS_IN_BASE-row); brickN++) {
				//singleBrickX is the constructor that tells GRect where to lay the brick(x coordinate) and it changes depending on the brick number in the row 
				int singleBrickX=(getWidth()/2)-((BRICKS_IN_BASE-row)*BRICK_WIDTH/2)+(brickN*BRICK_WIDTH);
				//singleBrickY is the constructor that tells GRect where to lay the brick(y coordinate) and it changes depending on which row the brick represents
				int singleBrickY=getHeight()-(BRICK_HEIGHT*(row+1));
				//this GRect draws a brick on singleBrickX and singleBrickY coordinates and with the bricks preset size 
				GRect singleBrick= new GRect (singleBrickX,singleBrickY,BRICK_WIDTH,BRICK_HEIGHT);
				add(singleBrick);
			}
			
		}
	}
}

