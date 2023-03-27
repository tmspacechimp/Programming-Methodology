/*
 * File: PythagoreanTheorem.java
 * 
 * Pythagorean Theorem program calculates the hypontenuse of the right angle triangle when provided two sides
 * Program reads a and b (the two sides of the triangle) and calculates c (hypotenuse,square root of a^2+b^2)
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {	//calculates the hypotenuse
		CalculateHypotenuse();
	}

	private void CalculateHypotenuse() {//reads a and b, calculates c and then prints the value
		println("Enter values to compute Pythagorean theorem:");
		double a= readDouble("a:  ");
		double b= readDouble("b:  ");
		double c= Math.sqrt((a*a)+(b*b));
		println("c=  "+c);
	}

	
}
