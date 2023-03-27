
/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the interactors in the
	 * application, and taking care of any other initialization that needs to be
	 * performed.
	 */
	public void init() {
		addGUIs();
		database = new FacePamphletDatabase();
		canvas = new FacePamphletCanvas();
		add(canvas);

	}

	/**
	 * This method adds GUI interactors to the program, adds actions listeners
	 */
	private void addGUIs() {
		statusGUIs();
		pictureGUIs();
		friendGUIs();
		northGUIs();

		addActionListeners();

	}

	/**
	 * This method adds the name field and the button that change the status, adds
	 * an empty label to have free space
	 */
	private void statusGUIs() {
		statusField = new JTextField(TEXT_FIELD_SIZE);
		statusField.addActionListener(this);
		add(statusField, WEST);

		statusButton = new JButton("Change Status");
		add(statusButton, WEST);

		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
	}

	/**
	 * This method adds the name field and the button that change the picture, adds
	 * an empty label to have free space
	 */
	private void pictureGUIs() {
		pictureField = new JTextField(TEXT_FIELD_SIZE);
		pictureField.addActionListener(this);
		add(pictureField, WEST);

		pictureButton = new JButton("Change Picture");
		add(pictureButton, WEST);

		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
	}

	/**
	 * This method adds the name field and the button that add a friend
	 */
	private void friendGUIs() {
		friendField = new JTextField(TEXT_FIELD_SIZE);
		friendField.addActionListener(this);
		add(friendField, WEST);

		friendButton = new JButton("Add Friend");
		add(friendButton, WEST);

	}

	/**
	 * This method adds GUIs in the northern part of the window
	 */
	private void northGUIs() {
		nameLabel = new JLabel("Name  ");
		add(nameLabel, NORTH);

		nameField = new JTextField(TEXT_FIELD_SIZE);
		add(nameField, NORTH);
		nameField.addActionListener(this);

		addButton = new JButton("Add");
		add(addButton, NORTH);

		deleteButton = new JButton("Delete");
		add(deleteButton, NORTH);

		lookupButton = new JButton("Lookup");
		add(lookupButton, NORTH);

	}

	/**
	 * This class is responsible for detecting when the buttons are clicked or
	 * interactors are used, so you will have to add code to respond to these
	 * actions.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == statusField || e.getSource() == statusButton) {
			if (currentProfile != null) {
				currentProfile.setStatus(statusField.getText());
				canvas.displayProfile(currentProfile);
				canvas.showMessage("Status updated to: " + currentProfile.getStatus());
			} else {
				canvas.showMessage("Please select a profile to change status");
			}

		} else if (e.getSource() == pictureField || e.getSource() == pictureButton) {
			if (currentProfile != null) {
				GImage image = null;
				try {
					image = new GImage(pictureField.getText());
					currentProfile.setImage(image);
				} catch (ErrorException ex) {
					image = null;
				}
				canvas.displayProfile(currentProfile);
				if (image == null) {
					canvas.showMessage("Unable to open image file: " + pictureField.getText());
				} else {
					canvas.showMessage("Profile picture has been updated");
				}
			} else {
				canvas.showMessage("Please select a profile to change image");
			}

		} else if (e.getSource() == friendField || e.getSource() == friendButton) {
			if (currentProfile != null) {
				if (database.containsProfile(friendField.getText())) {
					if (currentProfile.addFriend(friendField.getText())) {
						currentProfile.addFriend(friendField.getText());
						database.getProfile(friendField.getText()).addFriend(currentProfile.getName());
						canvas.displayProfile(currentProfile);
						canvas.showMessage(friendField.getText() + " has been added to friends");
					} else {
						canvas.displayProfile(currentProfile);
						canvas.showMessage("These two are already friends");
					}

				} else {
					canvas.displayProfile(currentProfile);
					canvas.showMessage("No profile with that name was found");
				}
			} else {
				canvas.showMessage("No current profile");
			}

		} else if (e.getSource() == addButton) {
			if (nameField.getText() != "") {

				if (database.containsProfile(nameField.getText())) {
					currentProfile = database.getProfile(nameField.getText());
					canvas.displayProfile(currentProfile);
					canvas.showMessage("A profile wih the name " + currentProfile.getName() + " alreadt exists ");
				} else {
					FacePamphletProfile profile = new FacePamphletProfile(nameField.getText());
					database.addProfile(profile);
					currentProfile = profile;
					currentProfile.setStatus("");
					canvas.displayProfile(currentProfile);
					canvas.showMessage("New profile added");
				}

			}

		} else if (e.getSource() == deleteButton) {
			if (nameField.getText() != "") {
				if (database.containsProfile(nameField.getText())) {
					database.deleteProfile(nameField.getText());
					canvas.removeAll();
					canvas.showMessage("Profile of " + nameField.getText() + " deleted");
					currentProfile = null;
				} else {
					canvas.removeAll();
					canvas.showMessage("Database doesn't include the profile of " + nameField.getText());
					currentProfile = null;
				}

			}

		} else if (e.getSource() == lookupButton) {
			currentProfile = database.getProfile(nameField.getText());
			if (currentProfile == null) {
				canvas.removeAll();
				canvas.showMessage("No such profile was found");
			} else {
				canvas.displayProfile(currentProfile);
				canvas.showMessage("Displaying " + currentProfile.getName());
			}
		}

	}

	private JTextField statusField;
	private JTextField pictureField;
	private JTextField friendField;
	private JTextField nameField;
	private JButton addButton;
	private JButton deleteButton;
	private JButton lookupButton;
	private JButton statusButton;
	private JButton pictureButton;
	private JButton friendButton;
	private JLabel nameLabel;

	private FacePamphletDatabase database;
	private FacePamphletProfile currentProfile = null;
	private FacePamphletCanvas canvas;

}
