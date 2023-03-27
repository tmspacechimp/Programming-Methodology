import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import acm.util.ErrorException;
import acmx.export.java.io.FileReader;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {

	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the data in the
	 * specified file. The constructor throws an error exception if the requested
	 * file does not exist or if an error occurs as the file is being read.
	 */
	public NameSurferDataBase(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				NameSurferEntry entry = new NameSurferEntry(line);
				namesMap.put(entry.getName(), entry);
			}
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}

	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one exists. If the
	 * name does not appear in the database, this method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		name = correctEntry(name);
		if (namesMap.containsKey(name)) {
			return namesMap.get(name);
		} else

			return null;
	}

	/* Method: correctEntry(String name) */
	/**
	 * Return the correct form of the inputed name, correct form is the form that all
	 * names adopt in the database
	 */
	private String correctEntry(String name) {
		char capital = name.charAt(0);
		if (Character.isLowerCase(capital) == true) {
			capital = Character.toUpperCase(capital);
		}
		String lowercase = name.substring(1);
		lowercase = lowercase.toLowerCase();
		name = capital + lowercase;
		return name;
	}

	private HashMap<String, NameSurferEntry> namesMap = new HashMap<String, NameSurferEntry>();
}
