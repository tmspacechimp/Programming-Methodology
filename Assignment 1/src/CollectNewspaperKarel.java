/*	This program solves CollectNewspaperKarel
 * 
 * Preconditions: 
 * Karel is standing in the corner of it's house
 * He needs to move to the newspaper, grab it and return to his starting position
 * 
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {

	public void run() {// get karel to the newspaper and return him back

		goToNewspaper();
		pickBeeper();
		returnToPosition();
	}

	private void goToNewspaper() { // get Karel to the newspaper
		turnRight();
		move();
		turnLeft();
		for (int i = 0; i < 3; i++) {
			move();
		}
	}

	private void returnToPosition() { // return Karel back
		turnAround();
		for (int i = 0; i < 3; i++) {
			move();
		}
		turnRight();
		move();
		turnRight();
	}
}
