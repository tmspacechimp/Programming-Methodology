
/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	/**
	 * random generator that generates instances that indicate chosen words index
	 **/
	private RandomGenerator rgen = RandomGenerator.getInstance();
	/** gives access hangman lexicon **/
	private HangmanLexicon wordList = new HangmanLexicon();
	/** holds the secret word chosen by rgen **/
	private String secretWord;
	/** holds the current word with hidden letters **/
	private String currentWord;
	/** counts remaining lives **/
	private int livesCount;
	/** creates canvas **/
	private HangmanCanvas canvas = new HangmanCanvas();
	/** saves incorrect guesses **/
	private char incorrectGuess;

	/* adds canvas */
	public void init() {
		add(canvas);
	}

	/* runs hangman program */
	public void run() {

		hangmanSetup();
		hangmanGameplay();
	}

	/*
	 * gives initial values to secret and hidden words sets up the initial parts of
	 * the game before it can be played resets canvas and displays the starting
	 * hidden word
	 */
	private void hangmanSetup() {
		secretWord = secretWordPicker();
		currentWord = secretWordHider();
		livesCount = 8;
		presentSecretWord();
		canvas.reset();
		canvas.displayWord(currentWord);
	}

	/* prints starting 3 lines in console */
	private void presentSecretWord() {
		println("Welcom To Hangman!");
		println("The word now looks like this:   " + currentWord);
		println("you have " + livesCount + " guesses left.");

	}

	/*
	 * hides secret word using a for cycle that draws a line everytime there's a
	 * letter returns the hidden word
	 */
	private String secretWordHider() {
		String res = "";
		for (int i = 0; i < secretWord.length(); i++)
			res = res + "-";

		return res;

	}

	/*
	 * randomly generates an int that is equal to secret word's index returns a
	 * random word which is later saved in secretWord
	 */
	private String secretWordPicker() {
		int wordIndex = rgen.nextInt(0, wordList.getWordCount() - 1);
		String randomWord = wordList.getWord(wordIndex);
		return randomWord;
	}

	/*
	 * takes care of the gameplay part of hangman, makes it playable method reads
	 * letters in a while cycle, validates it, turns it to uppercase letter check if
	 * the letter is present in the secret word and checks if the game has been won
	 * or lost
	 */
	private void hangmanGameplay() {
		String guess;
		while (true) {
			guess = readLine("Your guess:  ");
			while (validateGuess(guess) == false) {
				println("Your guess was invalid, make sure it's a letter");
				guess = readLine("Your guess:  ");
			}
			guess = guess.toUpperCase();
			searchForGuessInWord(guess);
			if (gameLost() == true) {
				printYouLost();
				break;
			}
			if (gameWon() == true) {
				printYouWon();
				break;
			}

		}

	}

	/*
	 * validates if the given chars length is one and weather or not it is a letter
	 */
	private boolean validateGuess(String guess) {
		if (guess.length() > 1)
			return false;
		else if (('a' <= guess.charAt(0) && guess.charAt(0) <= 'z')
				|| ('A' <= guess.charAt(0) && guess.charAt(0) <= 'Z'))
			return true;

		return false;

	}

	/* check if the letter is in the word */
	private boolean checkGuessInWord(String guess) {
		if (secretWord.indexOf(guess) == -1)
			return false;
		return true;
	}

	/*
	 * if the letter is not in the word prints according lines also reduces lifes
	 * count, draws hangman parts on the canvas saves incorrect guess and prints it
	 * on the canvas if the letter is in the word method gues through the secret
	 * word with a for cycle and then replaces lines with letters in the hidden word
	 * also displays the updated hidden word on the canvas
	 */
	private void searchForGuessInWord(String guess) {
		if (checkGuessInWord(guess) == false) {
			println("There are no " + guess + "'s in the word.");
			canvas.drawHangmanParts(livesCount);
			livesCount -= 1;
			incorrectGuess = guess.charAt(0);
			canvas.noteIncorrectGuess(incorrectGuess);
			if (livesCount != 1)
				println("You have " + livesCount + " guesses left.");
			if (livesCount == 1)
				println("You have one guess left.");

		}

		if (checkGuessInWord(guess) == true) {

			for (int i = 0; i < secretWord.length(); i++) {
				if (secretWord.charAt(i) == guess.charAt(0)) {
					if (i == 0)
						currentWord = guess + currentWord.substring(1);
					else
						currentWord = currentWord.substring(0, i) + guess + currentWord.substring(i + 1);
				}
			}

			println("That guess is correct.");
			if (gameWon() == false)
				println("the word now looks like this:   " + currentWord);

			canvas.displayWord(currentWord);

		}
	}

	/* checks if the game has been lost */
	private boolean gameLost() {
		if (livesCount == 0)
			return true;
		return false;

	}

	/* prints lines accordingly when the game is lost */
	private void printYouLost() {
		println("You're completly hung.");
		println("The word was " + secretWord);
		println("You lose.");
	}

	/* checks if the game has been won */
	private boolean gameWon() {
		for (int i = 0; i < secretWord.length(); i++)
			if (secretWord.charAt(i) != currentWord.charAt(i))
				return false;
		return true;
	}

	/* prints lines accordingly when the game is won */
	private void printYouWon() {
		println("You guessed the word:   " + secretWord);
		println("You win.");
	}

}
