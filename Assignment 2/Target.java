/*
 * File: Target.java
 * 
 * Target program draws the logo of the famous american chain store
 * The logo consists of three circles colored like a target
 * Radius sizes and colors are given beforehand and are saved as static doubles so they can be changed easily
 * 
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	
	//radius of the first circle
	private static final double radius1 = 72;
	//radius of the second circle
	private static final double radius2 = radius1*0.65;
	//radius of the third circle
	private static final double radius3 = radius1*0.3;
	
	public void run() {//draws target
		drawTarget();
	}

	private void drawTarget() {//this method decomposes the workload and divides it into three simple tasks
		drawFirstCircle();
		drawSecondCircle();
		drawThirdCircle();
		
	}

	private void drawFirstCircle() {//this method draws the first circle and gives the GOval constructors that match the programs needs
		GOval firstCircle = new GOval( getWidth()/2-radius1, getHeight()/2-radius1, radius1*2, radius1*2);
		firstCircle.setFilled(true);
		firstCircle.setColor(Color.red);
		add(firstCircle);
	}

	private void drawSecondCircle() {//this method draws the seconf circle and gives the GOval constructors that match the programs needs
		GOval secondCircle = new GOval( getWidth()/2-radius2, getHeight()/2-radius2, radius2*2, radius2*2);
		secondCircle.setFilled(true);
		secondCircle.setColor(Color.white);
		add (secondCircle);
		
	}

	private void drawThirdCircle() {//this method draws the third circle and gives the GOval constructors that match the programs needs
		GOval thirdCircle = new GOval( getWidth()/2-radius3, getHeight()/2-radius3, radius3*2, radius3*2);
		thirdCircle.setFilled(true);
		thirdCircle.setColor(Color.red);
		add (thirdCircle);
		
	}
}
