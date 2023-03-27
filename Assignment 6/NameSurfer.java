
/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base and
	 * initializing the interactors at the bottom of the window.
	 */
	public void init() {
		initJObjects();
		graph = new NameSurferGraph();
		add(graph);
	
	}

	private void initJObjects() {
		nameLabel = new JLabel("Name:  ");
		add(nameLabel, SOUTH);

		nameInput = new JTextField(15);
		add(nameInput, SOUTH);

		graphButton = new JButton("Graph");
		add(graphButton, SOUTH);

		clearButton = new JButton("Clear");
		add(clearButton, SOUTH);

		addActionListeners();
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are clicked, so you
	 * will have to define a method to respond to button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == graphButton) {
			String entryName = nameInput.getText();
			NameSurferEntry entry = database.findEntry(entryName);
			if(entry != null) {
				graph.addEntry(entry);
				graph.update();
				}

		} else if (e.getSource() == clearButton) graph.clear();
		
		}

	

	private JLabel nameLabel;
	private JTextField nameInput;
	private JButton graphButton;
	private JButton clearButton;

	private NameSurferDataBase database = new NameSurferDataBase("names-data.txt");
	private NameSurferGraph graph;

}
