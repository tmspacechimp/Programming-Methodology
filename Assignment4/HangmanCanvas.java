
/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		addScaffold();
	}

	/* adds the scaffold,beam and rope to the canvas */
	private void addScaffold() {
		drawScaffold();
		drawBeam();
		drawRope();
	}

	/* draws scaffold */
	private void drawScaffold() {
		GLine scaffold = new GLine(ANCHORX, ANCHORY, ANCHORX, ANCHORY + SCAFFOLD_HEIGHT);
		add(scaffold);

	}

	/* draws beam */
	private void drawBeam() {
		GLine beam = new GLine(ANCHORX, ANCHORY, ANCHORX + BEAM_LENGTH, ANCHORY);
		add(beam);
	}

	/* draws the rope */
	private void drawRope() {
		GLine rope = new GLine(ANCHORX + BEAM_LENGTH, ANCHORY, ANCHORX + BEAM_LENGTH, ANCHORY + ROPE_LENGTH);
		add(rope);
	}

	/**
	 * Updates the word on the screen to correspond to the current state of the
	 * game. The argument string shows what letters have been guessed so far;
	 * unguessed letters are indicated by hyphens.
	 */
	private GLabel wordLabel;

	public void displayWord(String word) {
		if (wordLabel != null)
			remove(wordLabel);
		wordLabel = new GLabel(word, ANCHORX, ANCHORY + SCAFFOLD_HEIGHT + 50);
		wordLabel.setFont("Sylfaen-30");

		add(wordLabel);

	}

	/**
	 * Updates the display to correspond to an incorrect guess by the user. Calling
	 * this method causes the next body part to appear on the scaffold and adds the
	 * letter to the list of incorrect guesses that appears at the bottom of the
	 * window.
	 */
	private String incorrects = "";
	private GLabel incorrectsLabel;

	public void noteIncorrectGuess(char letter) {
		if (incorrectsLabel != null)
			remove(incorrectsLabel);
		if (incorrects.indexOf(letter) == -1)
			incorrects = incorrects + letter;
		incorrectsLabel = new GLabel(incorrects, ANCHORX, HEIGHT - 120);
		incorrectsLabel.setFont("Timesroman-20");

		add(incorrectsLabel);
	}

	/* draws hangman's bodyparts according to the lifecount */
	public void drawHangmanParts(int cnt) {
		drawHead(cnt);
		drawBody(cnt);
		drawleftArm(cnt);
		drawRightArm(cnt);
		drawLeftLeg(cnt);
		drawRightLeg(cnt);
		drawLeftFoot(cnt);
		drawRightFoot(cnt);

	}

	/* draws head if the cnt is 8 */
	private void drawHead(int cnt) {
		if (cnt == 8) {
			GOval head = new GOval(ANCHORX + BEAM_LENGTH - HEAD_RADIUS, ANCHORY + ROPE_LENGTH, HEAD_RADIUS * 2,
					HEAD_RADIUS * 2);
			add(head);
		}

	}

	/* draws body if the cnt is 7 */
	private void drawBody(int cnt) {
		if (cnt == 7) {
			int x1 = ANCHORX + BEAM_LENGTH;
			int y1 = ANCHORY + ROPE_LENGTH + HEAD_RADIUS * 2;
			int x2 = ANCHORX + BEAM_LENGTH;
			int y2 = ANCHORY + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH;
			GLine body = new GLine(x1, y1, x2, y2);
			add(body);
		}

	}

	/* draws Right Arm if the cnt is 6 */
	private void drawleftArm(int cnt) {
		if (cnt == 6) {
			int x1 = ANCHORX + BEAM_LENGTH;
			int y1 = ANCHORY + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;
			int x2 = ANCHORX + BEAM_LENGTH - UPPER_ARM_LENGTH;
			int y2 = ANCHORY + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;

			GLine arm = new GLine(x1, y1, x2, y2);
			int x3 = ANCHORX + BEAM_LENGTH - UPPER_ARM_LENGTH;
			int y3 = ANCHORY + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;
			int x4 = ANCHORX + BEAM_LENGTH - UPPER_ARM_LENGTH;
			int y4 = ANCHORY + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH;
			GLine lowerArm = new GLine(x3, y3, x4, y4);

			add(arm);
			add(lowerArm);

		}
	}

	/* draws Right Arm if the cnt is 5 */
	private void drawRightArm(int cnt) {
		if (cnt == 5) {
			int x1 = ANCHORX + BEAM_LENGTH;
			int y1 = ANCHORY + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;
			int x2 = ANCHORX + BEAM_LENGTH + UPPER_ARM_LENGTH;
			int y2 = ANCHORY + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;

			GLine arm = new GLine(x1, y1, x2, y2);
			int x3 = ANCHORX + BEAM_LENGTH + UPPER_ARM_LENGTH;
			int y3 = ANCHORY + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;
			int x4 = ANCHORX + BEAM_LENGTH + UPPER_ARM_LENGTH;
			int y4 = ANCHORY + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH;
			GLine lowerArm = new GLine(x3, y3, x4, y4);

			add(arm);
			add(lowerArm);

		}

	}

	/* draws lft leg if the cnt is 4 */
	private void drawLeftLeg(int cnt) {
		if (cnt == 4) {
			int x1 = ANCHORX + BEAM_LENGTH;
			int y1 = ANCHORY + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH;
			int x2 = ANCHORX + BEAM_LENGTH - HIP_WIDTH;
			int y2 = ANCHORY + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH;
			GLine hip = new GLine(x1, y1, x2, y2);

			int x3 = ANCHORX + BEAM_LENGTH - HIP_WIDTH;
			int y3 = ANCHORY + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH;
			int x4 = ANCHORX + BEAM_LENGTH - HIP_WIDTH;
			int y4 = ANCHORY + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH;
			GLine leg = new GLine(x3, y3, x4, y4);

			add(hip);
			add(leg);

		}
	}

	/* draws right leg if the cnt is 3 */
	private void drawRightLeg(int cnt) {
		if (cnt == 3) {
			int x1 = ANCHORX + BEAM_LENGTH;
			int y1 = ANCHORY + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH;
			int x2 = ANCHORX + BEAM_LENGTH + HIP_WIDTH;
			int y2 = ANCHORY + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH;
			GLine hip = new GLine(x1, y1, x2, y2);

			int x3 = ANCHORX + BEAM_LENGTH + HIP_WIDTH;
			int y3 = ANCHORY + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH;
			int x4 = ANCHORX + BEAM_LENGTH + HIP_WIDTH;
			int y4 = ANCHORY + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH;
			GLine leg = new GLine(x3, y3, x4, y4);

			add(hip);
			add(leg);

		}
	}

	/* draws left foot if the cnt is 2 */
	private void drawLeftFoot(int cnt) {
		if (cnt == 2) {
			int x1 = ANCHORX + BEAM_LENGTH - HIP_WIDTH;
			int y1 = ANCHORY + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH;
			int x2 = ANCHORX + BEAM_LENGTH - HIP_WIDTH - FOOT_LENGTH;
			int y2 = ANCHORY + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH;
			GLine foot = new GLine(x1, y1, x2, y2);
			add(foot);
		}

	}

	/* draws right foot if the cnt is 1 */
	private void drawRightFoot(int cnt) {
		if (cnt == 1) {
			int x1 = ANCHORX + BEAM_LENGTH + HIP_WIDTH;
			int y1 = ANCHORY + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH;
			int x2 = ANCHORX + BEAM_LENGTH + HIP_WIDTH + FOOT_LENGTH;
			int y2 = ANCHORY + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH;
			GLine foot = new GLine(x1, y1, x2, y2);
			add(foot);
		}

	}

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

	private static final int WIDTH = 400;
	private static final int HEIGHT = 600;

	private static final int ANCHORX = (WIDTH / 2) - BEAM_LENGTH;
	private static final int ANCHORY = 30;

}
