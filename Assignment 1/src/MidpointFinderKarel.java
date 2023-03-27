/*	This program helps Karel put a beeper on the middle or one of the two middle cells of the first row
 * 
 * Preconditions:
 * Karel starts at the corner of 1st Ave. and 1st St.
 * Karel has to put a beeper on the middle or one of the two middle cells of the firs row
 */

import stanford.karel.*;

public class MidpointFinderKarel extends SuperKarel {

	public void run() {// put beeper on the middle cell

		while (frontIsClear()) {
			twoUpOneRight();
		}
		returnToFirstRow();
		putBeeper();
	}

	private void twoUpOneRight() {// go up twice and go right once

		turnLeft();

		if (frontIsClear()) {
			move();

			if (frontIsClear()) {
				move();
				turnRight();
				move();
			}

		}
	}

	private void returnToFirstRow() {// Karel returns to first row

		turnAround();

		while (frontIsClear()) {
			move();
		}
	}

}