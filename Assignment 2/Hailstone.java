/*
 *  File: Hailstone.java
 * 
 * Hailstone program touches the Collatz problem and tries to get numbers down to 1 with simple operations
 * The rules are simple if the number is odd Hailstone multiplies it by 3 and adds 1 
 * And if the number is even Hailstone divides  it by 2
 * this process goes on until we reach 1
 * Hailstone also has to count the number f operations needed 
 * 
 */
import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {// reach one from a positive integer
		reachOne();
	}

	// This method reads the number provided in console and performs operations until the bumber reaches one
	private void reachOne() {
		int n= readInt ("enter a number:  ");
		int count=0;
		while (n!=1 && n>0) {// this while cycle works until 1 is reached and doesn't work at all when number isn't positive
			if (n%2==0) {// if the number is even we get n/2
				println(n +"is even, so I take half: "+ n/2);
				n=n/2;
			}else {// if the number is odd we get 3n+1
				println(n +"is odd, so I make 3n+1: "+ ((3*n)+1));
				n=(3*n)+1;
			}
			count++;//while a single operation is made count increases by 1 
		}
		if (n==1) {//when 1 is reached the final result is printed
			println("The process took "+ count +" to reach 1");
		}		
	}
}

