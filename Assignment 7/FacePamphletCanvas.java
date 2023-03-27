
/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */

import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas implements FacePamphletConstants {

	/**
	 * Constructor This method takes care of any initialization needed for the
	 * display
	 */
	public FacePamphletCanvas() {

	}

	/**
	 * This method displays a message string near the bottom of the canvas. Every
	 * time this method is called, the previously displayed message (if any) is
	 * replaced by the new message text passed in.
	 */
	public void showMessage(String msg) {
		GLabel message = new GLabel(msg, getWidth() / 2, getHeight() - BOTTOM_MESSAGE_MARGIN);
		message.setFont(MESSAGE_FONT);
		message.move(-message.getWidth() / 2, -message.getAscent());
		add(message);
	}

	/**
	 * This method displays the given profile on the canvas. The canvas is first
	 * cleared of all existing items (including messages displayed near the bottom
	 * of the screen) and then the given profile is displayed. The profile display
	 * includes the name of the user from the profile, the corresponding image (or
	 * an indication that an image does not exist), the status of the user, and a
	 * list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		addNameLabel(profile.getName());
		addImage(profile.getImage());
		addStatus(profile.getStatus(), profile.getName());
		addFriends(profile.getFriends());
	}

	/**
	 * This method adds name label to the profile canvas
	 */
	private void addNameLabel(String name) {
		GLabel nameLabel = new GLabel(name, LEFT_MARGIN, TOP_MARGIN);
		nameLabel.setFont(PROFILE_NAME_FONT);
		nameAscent = nameLabel.getAscent() / 2;
		nameLabel.move(0, nameLabel.getAscent() / 2);
		nameLabel.setColor(Color.BLUE);

		add(nameLabel);

	}

	/**
	 * This method adds an image to the profile canvas if the image is non-existant
	 * it adds an empty GRect and a label
	 */
	private void addImage(GImage image) {
		if (image == null) {
			GRect empty = new GRect(LEFT_MARGIN, IMAGE_MARGIN + TOP_MARGIN + nameAscent, IMAGE_WIDTH, IMAGE_HEIGHT);
			GLabel label = new GLabel("No Image", LEFT_MARGIN + IMAGE_WIDTH / 2,
					IMAGE_MARGIN + TOP_MARGIN + nameAscent + IMAGE_HEIGHT / 2);
			label.setFont(PROFILE_IMAGE_FONT);
			label.move(-label.getWidth() / 2, label.getAscent() / 2);

			add(empty);
			add(label);
		} else {
			image.setBounds(LEFT_MARGIN, IMAGE_MARGIN + TOP_MARGIN + nameAscent, IMAGE_WIDTH, IMAGE_HEIGHT);
			add(image);
		}
	}

	/**
	 * This method adds status label to the profile canvas if the status isn't an
	 * empty string
	 */
	private void addStatus(String status, String name) {
		if (status.isEmpty() == false) {
			String text = name + " is " + status;
			GLabel label = new GLabel(text, LEFT_MARGIN,
					IMAGE_MARGIN + TOP_MARGIN + nameAscent + IMAGE_HEIGHT + STATUS_MARGIN);
			label.setFont(PROFILE_STATUS_FONT);
			label.move(0, label.getAscent());

			add(label);
		}
	}

	/**
	 * This method adds the headline label and the friend list line-by-line to the
	 * profile canvas
	 */
	private void addFriends(Iterator<String> friends) {
		double x = getWidth() / 2;
		double y = IMAGE_MARGIN + TOP_MARGIN + nameAscent;

		GLabel headline = new GLabel("Friends:", x, y);
		headline.setFont(PROFILE_FRIEND_FONT);
		headline.move(0, headline.getAscent());
		add(headline);

		double fx = getWidth() / 2;
		double fy = IMAGE_MARGIN + TOP_MARGIN + nameAscent + FRIEND_MARGIN_FROM_HEADLINE;
		int cnt = 0;

		if (friends.hasNext()) {
			while (friends.hasNext()) {
				GLabel friend = new GLabel(friends.next(), fx, fy + cnt * FRIEND_MARGIN_FROM_FRIEND);
				cnt++;
				friend.setFont(PROFILE_FRIEND_FONT);
				add(friend);

			}
		}

	}

	private double nameAscent;
}
