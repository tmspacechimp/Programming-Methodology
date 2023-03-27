/*	This program helps Karel turn his world into a checkerboard
 * 
 * Preconditions:
 * Karel starts at the corner of 1st Ave. and 1st St.
 * Karel needs to paint any rectangular world like a checkerboard
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run() {// paint world like a checkerboard
		createCheckerboard();
	}

	private void createCheckerboard() {// Do the operations that help Karel paint it's world
		fillFirstRow();
		moveToNext();
		fillRows();
		fillLastRowIfEven();

	}

	private void fillRows() {// fill all rows except the first and last ones
		while (leftIsClear()) {
			fillEvenRow();
			moveToNext();
			fillOddRow();
			moveToNext();
		}
	}

	private void fillEvenRow() {// fill Eventh row
		if (frontIsClear()) {
			move();
			putFirstBeeper();
		}
		fillRestOfTheRow();
		ComeBackToFirstColumn();
	}

	private void fillOddRow() {// fill oddth row
		putFirstBeeper();
		fillRestOfTheRow();
		ComeBackToFirstColumn();

	}

	private void moveToNext() {// move to next row
		turnRight();
		if (frontIsClear()) {
			move();
			turnRight();
		}

	}

	private void fillFirstRow() {// fill very first row
		putFirstBeeper();
		fillRestOfTheRow();
		ComeBackToFirstColumn();

	}

	private void ComeBackToFirstColumn() {// come back to the firsl column of the painted row
		turnAround();
		while (frontIsClear()) {
			move();
		}

	}

	private void fillRestOfTheRow() {// fill rest of the row
		while (frontIsClear()) {
			move();
			if (frontIsClear()) {
				move();
				putBeeper();
			}
		}

	}

	private void putFirstBeeper() {// put first beeper of the row
		putBeeper();

	}

	private void fillLastRowIfEven() {// when the rows are even and fillRows() has stopped working fill the last row
		if (noBeepersPresent()) {
			if (frontIsClear()) {
				move();
				putBeeper();
			}
			fillRestOfTheRow();
			ComeBackToFirstColumn();
		}

	}
}
