/* File: ProgramHierarchy.java
 * 
 * ProgramHierarchy program draws a partial diagram of the acm.program class hierarchy
 * The width and height of the class boxes should be specified as named constants so that they are easy to change
 * The entire figure should be centered as well as the labels and lines
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	//static width of the rectangles
	private static final double WIDTH = 180;
	//static height of the rectangles
	private static final double HEIGHT = 75;
	
	public void run() {//draws centered diagram of the program hierarchy
		drawHierarchy();
	}

	private void drawHierarchy() {//method divides the work in four main and similar parts 
		drawProgram();		
		drawConsoleProgram();
		drawGraphicsProgram();
		drawDialogProgram();
	}

	private void drawProgram() {//method draws the program box and label
		drawProgramRect();
		drawProgramLabel();
		
	}

	private void drawProgramRect() {//method draws the program rect on (getWidth()/2-WIDTH/2, getHeight()/2-HEIGHT*2) coordinates
		GRect programRect = new GRect(getWidth()/2-WIDTH/2, getHeight()/2-HEIGHT*2, WIDTH, HEIGHT);
		add (programRect);
	}

	private void drawProgramLabel() {//method draws the program label on (getWidth()/2-WIDTH/2, getHeight()/2-HEIGHT*2) coordinates and then moves it to the center
		GLabel programLabel = new GLabel ("Program",getWidth()/2-WIDTH/2, getHeight()/2-HEIGHT*2);
		programLabel.move (WIDTH/2-programLabel.getWidth()/2,HEIGHT/2+programLabel.getAscent()/2);
		add(programLabel);
		
	}

	private void drawConsoleProgram() {//method draws console program box,label and the line connecting it to the program box
		drawConsoleProgramRect();
		drawConsoleProgramLabel();
		drawConsoleProgramLine();
		
	}

	private void drawConsoleProgramRect() {//method draws the console program rect on (getWidth()/2-WIDTH/2, getHeight()/2+HEIGHT) coordinates
		GRect consoleProgramRect = new GRect (getWidth()/2-WIDTH/2, getHeight()/2+HEIGHT, WIDTH, HEIGHT);
		add (consoleProgramRect);
	}

	private void drawConsoleProgramLabel() {//method draws the console program label on (getWidth()/2-WIDTH/2, getHeight()/2+HEIGHT) coordinates and then moves it to the center
		GLabel consoleProgramLabel = new GLabel ("Console Program",getWidth()/2-WIDTH/2, getHeight()/2+HEIGHT);
		consoleProgramLabel.move (WIDTH/2-consoleProgramLabel.getWidth()/2,HEIGHT/2+consoleProgramLabel.getAscent()/2);
		add(consoleProgramLabel);
	}

	private void drawConsoleProgramLine() {//method draws the line between console program and program boxes 
		GLine ConsoleProgramLine = new GLine (getWidth()/2, getHeight()/2 - HEIGHT, getWidth()/2, getHeight()/2 + HEIGHT);
		add(ConsoleProgramLine );
	}

	private void drawGraphicsProgram() {//method draws graphics program box,label and the line connecting it to the program box
		drawGraphicsProgramRect();
		drawGraphicsProgramLabel();
		drawGraphicsProgramLine();
	}
	
	private void drawGraphicsProgramRect() {//method draws the graphics program rect on (getWidth()/2-WIDTH*2,getHeight()/2+HEIGHT) coordinates
		GRect graphicsProgramRect = new GRect(getWidth()/2-WIDTH*2,getHeight()/2+HEIGHT,WIDTH,HEIGHT);
		add(graphicsProgramRect);
	}

	private void drawGraphicsProgramLabel() {//method draws the graphics program label on (getWidth()/2-WIDTH*2,getHeight()/2+HEIGHT) coordinates and then moves it to the center
		GLabel graphicsProgramLabel = new GLabel ("Graphics Program",getWidth()/2-WIDTH*2,getHeight()/2+HEIGHT);
		graphicsProgramLabel.move (WIDTH/2-graphicsProgramLabel.getWidth()/2,HEIGHT/2+graphicsProgramLabel.getAscent()/2);
		add(graphicsProgramLabel);
	}
	
	private void drawGraphicsProgramLine() {//method draws the line between graphics program and program boxes
		GLine GraphicsProgramLine = new GLine (getWidth()/2, getHeight()/2 - HEIGHT, getWidth()/2-WIDTH*1.5,getHeight()/2+HEIGHT);
		add(GraphicsProgramLine);
	}

	private void drawDialogProgram() {//method draws dialog program box,label and the line connecting it to the program box
		drawDialogProgramRect();
		drawDialogProgramLabel();
		drawDialogProgramLine();
		
	}

	private void drawDialogProgramRect() {//method draws the dialog program rect on (getWidth()/2+WIDTH,getHeight()/2+HEIGHT) coordinates
		GRect graphicsDialogRect = new GRect(getWidth()/2+WIDTH,getHeight()/2+HEIGHT,WIDTH,HEIGHT);
		add(graphicsDialogRect);
		
	}

	private void drawDialogProgramLabel() {//method draws the dialog program label on (getWidth()/2+WIDTH,getHeight()/2+HEIGHT) coordinates and then moves it to the center
		GLabel dialogProgramLabel = new GLabel ("Dialog Program",getWidth()/2+WIDTH,getHeight()/2+HEIGHT);
		dialogProgramLabel.move (WIDTH/2-dialogProgramLabel.getWidth()/2,HEIGHT/2+dialogProgramLabel.getAscent()/2);
		add(dialogProgramLabel);
	}

	private void drawDialogProgramLine() {//method draws the line between dialog program and program boxes
		GLine GraphicsDialogLine = new GLine (getWidth()/2, getHeight()/2 - HEIGHT, getWidth()/2+WIDTH*1.5,getHeight()/2+HEIGHT);
		add(GraphicsDialogLine);
		
	}

	
}

