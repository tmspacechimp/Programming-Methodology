/*
 * File: FindRange.java
 * 
 * FindRange program reads in a list of integers, one per line, until a sentinel value of 0 (easily changeable to any other value)
 * When the sentinel is read, the program should display the smallest and largest values 
 * If the user enters only one value before the sentinel, the program should report that value as both the largest and smallest
 * If the user enters the sentinel on the very first input line, then no values have been entered, and your program should display a message to that effect 
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	//the value which will be the last input is saved as SENTINEL and can be changed easily 
	private static final int SENTINEL=0;
	public void run() {//finds smallest and largest numbers from the inputed values
	findMaxAndMinNumbers();
	}
	
	private void findMaxAndMinNumbers() {//this method divides the whole thing in two parts, firstly displaying instructions and then comparing inputed numbers 
	displayInstructions();
	compareNumbers();
	}
	
	private void displayInstructions() {//this method displays program's instructions
		println("This program finds largest and smallest numbers");
		
	}
		 
	private void compareNumbers() {//method compares numbers, determines smallest and largest values and stops when sentinel is entered
		//at first a number is provided and compared to the sentinel  
		int firstNumber= readInt("Enter value:  ");
		//if the sentinel is equal to the first number the program stops and tells you that your number is invalid
		if (firstNumber==SENTINEL) {
			println("Sorry, the value you have provided is invalid");
		} else {
			//the first number entered is nominated as the smallest and the largest at first, after that new values are compared to smallest and largest numbers 
			int maxNumber = firstNumber;
			int minNumber = firstNumber;
			int givenNumber = firstNumber;
			
			while (givenNumber!=SENTINEL) {//every new value is compared to current smallest and largest numbers until sentinel is entered
				givenNumber= readInt("Enter value:  ");	
				if (givenNumber>maxNumber && givenNumber!=SENTINEL) {
					maxNumber=givenNumber;
				}
				if (givenNumber<minNumber && givenNumber!=SENTINEL) {
					minNumber=givenNumber;
				}
			}
			//after the sentinel is entered program prints largest and smallest numbers
			println("the smallest value was:  "+ minNumber);
			println("the largest value was:  "+ maxNumber);
			}
		
	}
}

