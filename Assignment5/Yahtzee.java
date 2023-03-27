
/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.ArrayList;

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {

	public static void main(String[] args) {
		new Yahtzee().start(args);
	}

	/* runs the Yahtzee program */
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	/*
	 * gives yahtzee the gameplay part initializes the three part array scores which
	 * stores categories players the third part indicates if the category for the
	 * player has aready been chosen by running three methods in a for cycle players
	 * can take turns rolling, rerolling and picking a category when all the rounds
	 * are over adds upper bonus if neccesary and the winner is nominated
	 */
	private void playGame() {
		scores = new int[N_CATEGORIES][nPlayers][2];
		for (int round = 0; round < N_SCORING_CATEGORIES; round++) {
			for (int player = 1; player <= nPlayers; player++) {
				firstRoll(player);
				rerolls();
				selectCategory(player);
			}
		}
		addUpperBonus();
		nominateWinner();
	}

	/*
	 * method notifies players for their turn, randomly generates the roll
	 * combination and displays already rolled dice
	 */
	private void firstRoll(int player) {
		display.printMessage(playerNames[player - 1] + "‘s turn! Click 'Roll Dice' button to roll the dice.");
		display.waitForPlayerToClickRoll(player);

		for (int diceIndex = 0; diceIndex < N_DICE; diceIndex++)
			rollCombination[diceIndex] = rgen.nextInt(1, 6);

		display.displayDice(rollCombination);
	}

	/*
	 * method lets the player reroll one or two dice REROLL_N times which is stored
	 * as a constant and can be changed randomly generates the numbers on the dice
	 * if it has been selected, displays the new dice combination
	 */
	private void rerolls() {
		for (int rerollNumber = 0; rerollNumber < REROLL_N; rerollNumber++) {
			display.printMessage("Select the dice you wish to re-roll and click 'Roll Again'.");

			display.waitForPlayerToSelectDice();

			for (int diceIndex = 0; diceIndex < N_DICE; diceIndex++) {
				if (display.isDieSelected(diceIndex))
					rollCombination[diceIndex] = rgen.nextInt(1, 6);
			}

			display.displayDice(rollCombination);

		}
	}

	/*
	 * notifies the player to select a category after two rerolls waits for the
	 * player to select a category checks if the category has been picked and runs
	 * until an unpicked category has been selected changes the selected status for
	 * the correctly picked category calculates score for the picked category
	 */
	private void selectCategory(int player) {
		display.printMessage("Select a category for this roll.");

		while (true) {
			int category = display.waitForPlayerToSelectCategory();
			if (checkIfPicked(player, category) == false) {
				scores[category - 1][player - 1][1] = 1;
				calculateCategoryScore(player, category);
				break;
			}

		}
	}

	/*
	 * checks if the picked category has been picked or if its an unpickable
	 * category returns false if category is valid
	 */
	private boolean checkIfPicked(int player, int category) {
		if (scores[category - 1][player - 1][1] == 0 && category != UPPER_SCORE && category != UPPER_BONUS
				&& category != LOWER_SCORE && category != TOTAL)
			return false;

		return true;
	}

	/*
	 * calculates the score for the picked category for the player updates upper,
	 * lower and total scores displays updated category,upper, lower and total
	 * scores
	 */
	private void calculateCategoryScore(int player, int category) {
		int categoryScore = 0;
		if (category >= ONES && category <= SIXES) {
			categoryScore = checkForSingles(category);
			scores[category - 1][player - 1][0] = categoryScore;
			scores[UPPER_SCORE - 1][player - 1][0] += categoryScore;
			scores[TOTAL - 1][player - 1][0] += categoryScore;

		}

		if (category == THREE_OF_A_KIND) {
			if (checkForCombos(category) == true)
				for (int diceIndex = 0; diceIndex < N_DICE; diceIndex++)
					categoryScore += rollCombination[diceIndex];
			scores[category - 1][player - 1][0] = categoryScore;
			scores[LOWER_SCORE - 1][player - 1][0] += categoryScore;
			scores[TOTAL - 1][player - 1][0] += categoryScore;

		}
		if (category == FOUR_OF_A_KIND) {
			if (checkForCombos(category) == true)
				for (int diceIndex = 0; diceIndex < N_DICE; diceIndex++)
					categoryScore += rollCombination[diceIndex];
			scores[category - 1][player - 1][0] = categoryScore;
			scores[LOWER_SCORE - 1][player - 1][0] += categoryScore;
			scores[TOTAL - 1][player - 1][0] += categoryScore;

		}
		if (category == FULL_HOUSE) {
			if (checkForCombos(category) == true)
				categoryScore += 25;
			scores[category - 1][player - 1][0] = categoryScore;
			scores[LOWER_SCORE - 1][player - 1][0] += categoryScore;
			scores[TOTAL - 1][player - 1][0] += categoryScore;

		}
		if (category == SMALL_STRAIGHT) {
			if (checkForCombos(category) == true)
				categoryScore += 30;
			scores[category - 1][player - 1][0] = categoryScore;
			scores[LOWER_SCORE - 1][player - 1][0] += categoryScore;
			scores[TOTAL - 1][player - 1][0] += categoryScore;
		}
		if (category == LARGE_STRAIGHT) {
			if (checkForCombos(category) == true)
				categoryScore += 40;
			scores[category - 1][player - 1][0] = categoryScore;
			scores[LOWER_SCORE - 1][player - 1][0] += categoryScore;
			scores[TOTAL - 1][player - 1][0] += categoryScore;
		}
		if (category == YAHTZEE) {
			if (checkForCombos(category) == true)
				categoryScore += 50;
			scores[category - 1][player - 1][0] = categoryScore;
			scores[LOWER_SCORE - 1][player - 1][0] += categoryScore;
			scores[TOTAL - 1][player - 1][0] += categoryScore;

		}
		if (category == CHANCE) {
			for (int diceIndex = 0; diceIndex < N_DICE; diceIndex++)
				categoryScore += rollCombination[diceIndex];
			scores[category - 1][player - 1][0] = categoryScore;
			scores[LOWER_SCORE - 1][player - 1][0] += categoryScore;
			scores[TOTAL - 1][player - 1][0] += categoryScore;

		}

		display.updateScorecard(category, player, categoryScore);
		display.updateScorecard(UPPER_SCORE, player, scores[UPPER_SCORE - 1][player - 1][0]);
		display.updateScorecard(LOWER_SCORE, player, scores[LOWER_SCORE - 1][player - 1][0]);
		display.updateScorecard(TOTAL, player, scores[TOTAL - 1][player - 1][0]);

	}

	/*
	 * this private int returns the score if one of the upper categories are
	 * selected
	 */
	private int checkForSingles(int category) {
		int categoryScore = 0;
		for (int diceIndex = 0; diceIndex < N_DICE; diceIndex++) {
			if (rollCombination[diceIndex] == category) {
				categoryScore += category;
			}
		}
		return categoryScore;
	}

	/*
	 * boolean checks if the picked lower category deserves the points it's meant to
	 * have all the lower categories are checked in the same boolean because of the
	 * similar nature creates six local arraylists and using their size checks if
	 * the combinations are correct returns false if the combination isn't correct
	 */
	private boolean checkForCombos(int category) {
		ArrayList<Integer> ones = new ArrayList<Integer>();
		ArrayList<Integer> twos = new ArrayList<Integer>();
		ArrayList<Integer> threes = new ArrayList<Integer>();
		ArrayList<Integer> fours = new ArrayList<Integer>();
		ArrayList<Integer> fives = new ArrayList<Integer>();
		ArrayList<Integer> sixes = new ArrayList<Integer>();

		for (int diceIndex = 0; diceIndex < N_DICE; diceIndex++) {
			if (rollCombination[diceIndex] == 1)
				ones.add(1);
			if (rollCombination[diceIndex] == 2)
				twos.add(1);
			if (rollCombination[diceIndex] == 3)
				threes.add(1);
			if (rollCombination[diceIndex] == 4)
				fours.add(1);
			if (rollCombination[diceIndex] == 5)
				fives.add(1);
			if (rollCombination[diceIndex] == 6)
				sixes.add(1);
		}

		if (category == THREE_OF_A_KIND)
			if (ones.size() >= 3 || twos.size() >= 3 || threes.size() >= 3 || fours.size() >= 3 || fives.size() >= 3
					|| sixes.size() >= 3)
				return true;
		if (category == FOUR_OF_A_KIND)
			if ((ones.size() >= 4 || twos.size() >= 4 || threes.size() >= 4 || fours.size() >= 4 || fives.size() >= 4
					|| sixes.size() >= 4))
				return true;
		if (category == YAHTZEE)
			if ((ones.size() == 5 || twos.size() == 5 || threes.size() == 5 || fours.size() == 5 || fives.size() == 5
					|| sixes.size() == 5))
				return true;
		if (category == FULL_HOUSE)
			if (ones.size() == 3 || twos.size() == 3 || threes.size() == 3 || fours.size() == 3 || fives.size() == 3
					|| sixes.size() == 3)
				if (ones.size() == 2 || twos.size() == 2 || threes.size() == 2 || fours.size() == 2 || fives.size() == 2
						|| sixes.size() == 2)
					return true;
		if (category == SMALL_STRAIGHT)
			if ((ones.size() >= 1 && twos.size() >= 1 && threes.size() >= 1 && fours.size() >= 1)
					|| (twos.size() >= 1 && threes.size() >= 1 && fours.size() >= 1 && fives.size() >= 1
							&& sixes.size() >= 1)
					|| (threes.size() >= 1 && fours.size() >= 1 && fives.size() >= 1 && sixes.size() >= 1))
				return true;
		if (category == LARGE_STRAIGHT)
			if ((ones.size() >= 1 && twos.size() >= 1 && threes.size() >= 1 && fours.size() >= 1 && fives.size() >= 1)
					|| (twos.size() >= 1 && threes.size() >= 1 && fours.size() >= 1 && fives.size() >= 1
							&& sixes.size() >= 1))
				return true;
		return false;

	}

	/*
	 * adds upper bonus if upper score is more than 62 displays updated upper bonus
	 * and total score
	 */
	private void addUpperBonus() {
		for (int player = 1; player <= nPlayers; player++) {
			if (scores[UPPER_SCORE - 1][player - 1][0] > 62) {
				scores[UPPER_BONUS - 1][player - 1][0] += 35;
				scores[TOTAL - 1][player - 1][0] += 35;
			}
			display.updateScorecard(UPPER_BONUS, player, scores[UPPER_BONUS - 1][player - 1][0]);
			display.updateScorecard(TOTAL, player, scores[TOTAL - 1][player - 1][0]);
		}
	}

	/*
	 * nominates and displays the winner of the game. 
	 * also works in a case of a tie
	 */
	private void nominateWinner() {
		int maxScore = 0;
		String winningMessage = "it's a tie! The winners are: ";
		ArrayList<Integer> winners = new ArrayList<Integer>();

		for (int player = 0; player < nPlayers; player++) {
			if (scores[TOTAL - 1][player][0] > maxScore)
				maxScore = scores[TOTAL - 1][player][0];
		}
		
		for (int player = 0; player < nPlayers; player++) {
			if (scores[TOTAL - 1][player][0] == maxScore) {
				winners.add(player);
				winningMessage += playerNames[player] + "  ";
			}
		}
		
		if (winners.size()>1)
			display.printMessage(winningMessage);
		
		if (winners.size()==1)
			display.printMessage(playerNames[winners.get(0)] + "  has won") ;
		
	}

	/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private int[] rollCombination = new int[N_DICE];
	private int[][][] scores;
}
