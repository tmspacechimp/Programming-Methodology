
/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import acm.util.*;

public class HangmanLexicon {
	/* creates an array for the lexicon words */
	public ArrayList<String> hangmanWords = new ArrayList<String>();

	/*
	 * reads the file line by line with buffered reader stops if there are no more
	 * lines adds the words read to the array
	 */
	public HangmanLexicon() {

		try {
			BufferedReader br = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				hangmanWords.add(line);
			}
			br.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}

	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return hangmanWords.size();
	}

	/** Returns the word at the specified index. */
	public String getWord(int index) {
		return hangmanWords.get(index);
	}
}
