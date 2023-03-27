/*	This program helps Karel Reapair all the columns damaged 
 * 
 * Preconditions:
 * Karel starts at the corner of 1st Ave. and 1st St.
 * Karel needs to fix all the columns 
 * Columns are always 4 units from each other
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run() { // fix all of the columns damaged
		while (frontIsClear()) {
			repairColumn();
			turnAround();
			comeBackToFirstRow();
			turnLeft();
			moveToNextColumn();
		}
		repairColumn();
		turnAround();
		comeBackToFirstRow();
	}

	private void repairColumn() {// repair one column
		turnLeft();
		while (frontIsClear()) {
			if (noBeepersPresent()) {
				putBeeper();
			}
			move();
		}
	}

	private void comeBackToFirstRow() {// karel returns to first row of the column
		if (noBeepersPresent()) {
			putBeeper();
		}
		while (frontIsClear()) {
			move();
		}
	}

	private void moveToNextColumn() {// karel moves to next row
		if (frontIsClear()) {
			for (int i = 0; i < 4; i++) {
				move();
			}
		}

	}

}
