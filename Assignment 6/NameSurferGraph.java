
/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph() {
		addComponentListener(this);
		currentEntries = new ArrayList<NameSurferEntry>();
	}

	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		currentEntries.clear();
		update();
	}

	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display. Note that
	 * this method does not actually draw the graph, but simply stores the entry;
	 * the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		currentEntries.add(entry);
	}

	/**
	 * Updates the display image by deleting all the graphical objects from the
	 * canvas and then reassembling the display according to the list of entries.
	 * Your application must call update after calling either clear or addEntry;
	 * update is also called whenever the size of the canvas changes.
	 */
	public void update() {
		removeAll();
		drawBackground();
		if (currentEntries.size() >= 0) {

			for (int i = 0; i < currentEntries.size(); i++) {
				NameSurferEntry entry = currentEntries.get(i);
				drawEntryGraph(entry, i);
			}
		}
	}

	/* Method: drawEntryGraph(NameSurferEntry entry, int entryNumber) */
	/**
	 * Draws the lines for entries that depict growth and decay of the popularity,
	 * colors the entry graphs accordingly.
	 */

	private void drawEntryGraph(NameSurferEntry entry, int entryNumber) {
		for (int i = 0; i < NDECADES - 1; i++) {
			int margin = getWidth() / NDECADES;

			GLine line = new GLine(i * margin, entryY(entry, i), (1 + i) * margin, entryY(entry, i + 1));
			if (currentEntries.indexOf(entry) % 4 == 0)
				line.setColor(Color.red);
			if (currentEntries.indexOf(entry) % 4 == 1)
				line.setColor(Color.blue);
			if (currentEntries.indexOf(entry) % 4 == 2)
				line.setColor(Color.green);
			if (currentEntries.indexOf(entry) % 4 == 3)
				line.setColor(Color.orange);
			add(line);
			addLabel(entry, i, margin);
		}
	}

	/* Method: entryY(NameSurferEntry entry, int decade) */
	/**
	 * Returns the Y coordinate for given entry's given decade
	 */

	private double entryY(NameSurferEntry entry, int decade) {
		int rank = entry.getRank(decade);
		double y = 0;
		if (rank != 0) {
			y = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE * 2) * rank / MAX_RANK;

		} else if (rank == 0) {
			y = getHeight() - GRAPH_MARGIN_SIZE;

		}
		return y;
	}

	/* Method: addLabel(NameSurferEntry entry, int decade, int margin) */
	/**
	 * Adds labels to the canvas that contain information about the entry name and
	 * rank, labels re located right next to the entry markings of the vertical
	 * line.
	 */

	private void addLabel(NameSurferEntry entry, int decade, int margin) {
		String labelText = entry.getName() + "  " + entry.getRank(decade);
		GLabel label = new GLabel(labelText, decade * margin + LABEL_MARGIN, entryY(entry, decade));

		if (currentEntries.indexOf(entry) % 4 == 0)
			label.setColor(Color.red);
		if (currentEntries.indexOf(entry) % 4 == 1)
			label.setColor(Color.blue);
		if (currentEntries.indexOf(entry) % 4 == 2)
			label.setColor(Color.green);
		if (currentEntries.indexOf(entry) % 4 == 3)
			label.setColor(Color.orange);
		add(label);
	}

	/* Method: drawBackground() */
	/**
	 * Draws the background set pieces of the graph, consisting of: decade labels,
	 * vertical and horizontal lines.
	 */

	private void drawBackground() {
		drawVerticalLines();
		drawHorizontalLines();
		addDecades();

	}

	/* Method: drawVerticalLines() */
	/**
	 * draws vertical lines of the graph background, which are seperated equally by a constant
	 */
	private void drawVerticalLines() {
		int margin = getWidth() / NDECADES;
		for (int i = 0; i < NDECADES; i++) {
			GLine vertical = new GLine(i * margin, 0, i * margin, getHeight());

			add(vertical);
		}

	}

	/* Method: drawHorizontal() */
	/**
	 * draws horizontal lines of the graph background, which are seperated form the bottom abd the top of the canvas equally, by a constant
	 */
	
	private void drawHorizontalLines() {
		GLine topLine = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		GLine botLine = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);

		add(topLine);
		add(botLine);
	}

	/* Method: addDecades() */
	/**
	 * adds decade labels to the bottom parts of the graph background 
	 */
	private void addDecades() {
		int margin = getWidth() / NDECADES;
		for (int i = 0; i < NDECADES; i++) {
			GLabel decade = new GLabel(Integer.toString(START_DECADE + i * 10), DECADE_LABEL_MARGIN_X + i * margin,
					getHeight() - GRAPH_MARGIN_SIZE + DECADE_LABEL_MARGIN_Y);
			add(decade);
		}
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentResized(ComponentEvent e) {
		update();
	}

	public void componentShown(ComponentEvent e) {
	}

	private ArrayList<NameSurferEntry> currentEntries;
}
