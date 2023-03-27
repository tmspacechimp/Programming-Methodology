
/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;

import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;

	/** Pause time between moves */
	private static final double PAUSE_TIME = 10;

	/* Method: run() */
	/** Runs the Breakout program. */
	public void run() {
		/* Breaks up the work into two main parts */
		breakoutInitialisation();
		breakoutGameplay();
	}

	/* Breaks up the initialisation into three main parts */
	private void breakoutInitialisation() {
		arrangeBricks();
		createPaddle();
		createBall();
	}

	/*
	 * Draws the bricks with two for cycles one for rows and one for columns Adds
	 * new instance variable that counts the bricks left to know when the game is
	 * over later
	 */
	private GRect brickRect;
	private int bricksLeft = (NBRICK_ROWS * NBRICKS_PER_ROW);

	private void arrangeBricks() {
		for (int row = 0; row < NBRICK_ROWS; row++) {
			for (int col = 0; col < NBRICKS_PER_ROW; col++) {
				int x = (col + 1) * BRICK_SEP + col * BRICK_WIDTH;
				int y = BRICK_Y_OFFSET + row * (BRICK_HEIGHT + BRICK_SEP);
				brickRect = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				colorBricks(row);
				add(brickRect);
			}
		}

	}

	/* Gives colors to different pairs of rows */
	private void colorBricks(int row) {
		if (row / 2 < 1) {
			brickRect.setFilled(true);
			brickRect.setColor(Color.RED);
		} else if (row / 2 == 1) {
			brickRect.setFilled(true);
			brickRect.setColor(Color.ORANGE);
		} else if (row / 2 == 2) {
			brickRect.setFilled(true);
			brickRect.setColor(Color.YELLOW);
		} else if (row / 2 == 3) {
			brickRect.setFilled(true);
			brickRect.setColor(Color.GREEN);
		} else if (row / 2 == 4) {
			brickRect.setFilled(true);
			brickRect.setColor(Color.CYAN);
		}

	}

	/* Draws paddle and gives it mouse listeners to control the paddle */
	private GRect paddleRect;

	public void createPaddle() {
		int x = ((WIDTH - PADDLE_WIDTH) / 2);
		int y = HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
		paddleRect = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddleRect.setFilled(true);
		paddleRect.setColor(Color.BLACK);
		add(paddleRect);
		addMouseListeners();

	}

	/* Gives the paddle it's functionality */
	public void mouseDragged(MouseEvent e) {
		if (e.getX() < WIDTH - PADDLE_WIDTH / 2 && e.getX() > PADDLE_WIDTH / 2) {
			int x = e.getX() - PADDLE_WIDTH / 2;
			int y = HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
			paddleRect.setLocation(x, y);
		}
	}

	/* Creates the GOval for the ball and method that draws it */
	private GOval ball;

	private void createBall() {
		int x = (WIDTH - BALL_RADIUS) / 2;
		int y = (HEIGHT - BALL_RADIUS) / 2;
		ball = new GOval(x, y, 2 * BALL_RADIUS, 2 * BALL_RADIUS);
		ball.setFilled(true);
		ball.setColor(Color.BLACK);
		add(ball);
	}

	/*
	 * Creates instance variables that determine the speed of the ball vy always has
	 * the value of 3.0 while vx is generated randomly with vxgen random generator
	 * livesCount is created to track the number of turn player is currently on and
	 * is given the starting value of 0
	 */
	private RandomGenerator vxgen = RandomGenerator.getInstance();
	private double vx, vy;
	private int livesCount = 0;

	/*
	 * BreakoutGameplay method gives values to vx and vy, and then takes care of the
	 * mechanics and rues of the game Ball movement is done in the while cycle which
	 * works until NTURNS is reached or there are no more bricks left
	 */
	private void breakoutGameplay() {
		vy = 3.0;
		vx = vxgen.nextDouble(1.0, 3.0);
		if (vxgen.nextBoolean(0.5))
			vx = -vx;
		waitForClick();
		while (livesCount < NTURNS) {
			ball.move(vx, vy);
			pause(PAUSE_TIME);
			WallCollision();
			objectCollision();
			respawnIfDead();
			if (bricksLeft == 0) {
				printYouWon();
				break;
			}
			if (livesCount == NTURNS)
				printYouLose();
		}
	}

	/*
	 * Checks if the ball has reached left, right or top wall and changes the
	 * direction of the ball accordingly
	 */
	private void WallCollision() {
		if (ball.getX() <= 0 || ball.getX() + 2 * BALL_RADIUS >= WIDTH) {
			vx = -vx;
		}
		if (ball.getY() <= 0) {
			vy = -vy;
		}
	}

	/*
	 * Checks if the ball has collided with the paddle or the brick, changes the
	 * direction and removes the bricks Also decreases the value of bricksLeft
	 */
	private void objectCollision() {
		GObject collider = checkForObject();
		if (paddleRect == collider) {
			vy = -vy;
		} else if (collider != null) {
			bricksLeft--;
			remove(collider);
			vy = -vy;
		}
	}

	/*
	 * Picks eight points around the ball and checks if they have collided with
	 * objects If they have, the method returns the object Four of those are points
	 * given in the instruction And the additional four are the points in which the
	 * circle touches the square around it These points are added to make the game
	 * run smoother and avoid the "sticky paddle" bug
	 */
	private GObject checkForObject() {
		if (getElementAt(ball.getX(), ball.getY()) != null) {
			return getElementAt(ball.getX(), ball.getY());
		}
		if (getElementAt((ball.getX() + 2 * BALL_RADIUS), ball.getY()) != null) {
			return getElementAt((ball.getX() + 2 * BALL_RADIUS), ball.getY());
		}
		if (getElementAt(ball.getX(), (ball.getY() + 2 * BALL_RADIUS)) != null) {
			return getElementAt(ball.getX(), (ball.getY() + 2 * BALL_RADIUS));
		}
		if (getElementAt((ball.getX() + 2 * BALL_RADIUS), (ball.getY() + 2 * BALL_RADIUS)) != null) {
			return getElementAt((ball.getX() + 2 * BALL_RADIUS), (ball.getY() + (2 * BALL_RADIUS)));
		}
		if (getElementAt((ball.getX() + BALL_RADIUS), (ball.getY() + BALL_RADIUS * 2)) != null) {
			return getElementAt(ball.getX(), ball.getY());
		}
		if (getElementAt((ball.getX() + BALL_RADIUS), ball.getY()) != null) {
			return getElementAt((ball.getX() + 2 * BALL_RADIUS), ball.getY());
		}
		if (getElementAt(ball.getX(), (ball.getY() + BALL_RADIUS)) != null) {
			return getElementAt(ball.getX(), (ball.getY() + 2 * BALL_RADIUS));
		}
		if (getElementAt((ball.getX() + 2 * BALL_RADIUS), (ball.getY() + BALL_RADIUS)) != null) {
			return getElementAt((ball.getX() + 2 * BALL_RADIUS), (ball.getY() + (2 * BALL_RADIUS)));
		}

		return null;
	}

	/*
	 * If the ball has reached the point where it's considered dead method updates
	 * livesCount After that the method respwans the ball (sets it's location to the
	 * centre) Method waits for you to click to start the next turn
	 */
	private void respawnIfDead() {
		if (ball.getY() > (HEIGHT)) {
			livesCount++;
			int x = (WIDTH - BALL_RADIUS) / 2;
			int y = (HEIGHT - BALL_RADIUS) / 2;
			ball.setLocation(x, y);
			if (livesCount != NTURNS) {
				waitForClick();
			}
		}
	}

	/* If the player has won the game method congratulates the player */
	private void printYouWon() {
		GLabel youWon = new GLabel("CONGRATULATIONS, YOU WON!", (WIDTH / 2), (3 * HEIGHT / 5));
		youWon.setFont(new Font("Serif", Font.BOLD, 18));
		youWon.move(-(youWon.getWidth() / 2), -(youWon.getAscent() / 2));

		add(youWon);
	}

	/* If the player loses all the lives method says "you lost" */
	private void printYouLose() {
		GLabel youLost = new GLabel("SORRY, YOU LOST", (WIDTH / 2), (3 * HEIGHT / 5));
		youLost.setFont(new Font("Serif", Font.BOLD, 18));
		youLost.move(-(youLost.getWidth() / 2), -(youLost.getAscent() / 2));

		add(youLost);

	}

}