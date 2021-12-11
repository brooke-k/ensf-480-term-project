/**
 * File: HomePage.java
 * ENSF 480, Fall 2021
 * Term Project
 * Lecture Section: L02
 * Instructor: M. Moshirpour
 * Group 14
 * @author Khosla, Abhay
 * @author Kindleman, Brooke
 * @author Knapton, Nicholas
 * @author Kramer, Brian
 * Created: Dec 2021
 * @version 1.0
 */

package ensf480.group14.views;

import ensf480.group14.eventListeners.Listener;
import ensf480.group14.users.RegisteredRenter;
import ensf480.group14.users.User;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.bson.types.ObjectId;

/**
 * Home page after logging in (or not loggin in)
 *
 */
public class HomePage {
	private Listener listener;

	public HomePage() {

	}

	/**
	 * displays home page based on the type of user who is logged in
	 *
	 */
	public JPanel display(User user, Listener listen) {
		listener = listen;
		JPanel panel = new JPanel();
		if(user == null) return panel;
		Dimension expectDimension = new Dimension(300, 300);

		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0, 1));

		panel.setPreferredSize(expectDimension);
		panel.setMaximumSize(expectDimension);
		panel.setMinimumSize(expectDimension);

		panel.setBackground(Color.GRAY);

		panel.setAlignmentX(Component.CENTER_ALIGNMENT);

		if (!user.getType().equals("manager")) {

			JButton searchButton = new JButton("Search Properties"); // search form --> search results
			searchButton.addActionListener(listener);
			panel.add(searchButton);

		}

		if (user.getType().equals("registered_renter")) {
			panel.add(Box.createRigidArea(new Dimension(1, 5)));
			JButton preferenceButton = new JButton("Notifications Settings"); // open preference form
			preferenceButton.addActionListener(listener);
			panel.add(preferenceButton);

			panel.add(Box.createRigidArea(new Dimension(1, 5)));
			JButton unsubButton = new JButton("Unsubscribe from preferences");
			unsubButton.addActionListener(listener);
			panel.add(unsubButton);

			panel.add(Box.createRigidArea(new Dimension(1, 5)));
			JButton inboxButton = new JButton("Notifications"); // Open Inbox page needs array of emails addressed to user
			inboxButton.addActionListener(listener);
			panel.add(inboxButton);
		}
		if (user.getType().equals("manager")) {
			panel.add(Box.createRigidArea(new Dimension(1, 5)));
			JButton accessPropertiesButton = new JButton("Access Properties"); // opens search results with all properties
			accessPropertiesButton.addActionListener(listener);
			panel.add(accessPropertiesButton);

			panel.add(Box.createRigidArea(new Dimension(1, 5)));
			JButton accessUsersButton = new JButton("Access Users"); // opens search results with all properties
			accessUsersButton.addActionListener(listener);
			panel.add(accessUsersButton);

			panel.add(Box.createRigidArea(new Dimension(1, 5)));
			JButton feesButton = new JButton("Adjust Fees"); // opens dialog box with pay period and amount
			feesButton.addActionListener(listener); // also needs to change those in DB/Billing
			panel.add(feesButton);

			panel.add(Box.createRigidArea(new Dimension(1, 5)));
			JButton periodButton = new JButton("Adjust Payment Period"); // opens dialog box with pay period and amount
			periodButton.addActionListener(listener); // also needs to change those in DB/Billing
			panel.add(periodButton);

			panel.add(Box.createRigidArea(new Dimension(1, 5)));
			JButton reportButton = new JButton("Generate Report"); // generates report as txt file and opens it
			reportButton.addActionListener(listener); // brooke already made this in Report
			panel.add(reportButton);
		}
		if (user.getType().equals("landlord")) {

			panel.add(Box.createRigidArea(new Dimension(1, 5)));
			JButton managePropertyButton = new JButton("Manage Properties"); // Open search results with only properties
																				// that the landlord owns
			managePropertyButton.addActionListener(listener);
			panel.add(managePropertyButton);

			panel.add(Box.createRigidArea(new Dimension(1, 5)));
			JButton addPropertyButton = new JButton("New Property Application"); // Open Property Application
			addPropertyButton.addActionListener(listener);
			panel.add(addPropertyButton);

			panel.add(Box.createRigidArea(new Dimension(1, 5)));
			JButton inboxButton = new JButton("Inbox"); // Open Inbox page needs array of emails addressed to user
			inboxButton.addActionListener(listener);
			panel.add(inboxButton);

		}
		
		return panel;
	}

	// For testing

	// public static void main(String[] args) {
	// JFrame frame = new JFrame();
	// ObjectId id = new ObjectId();
	// User user = new RegisteredRenter("an email", id, "banana");
	// HomePage loggedIn = new HomePage();

	// // LoginForm login = getOnlyInstance();
	// frame.add(loggedIn.display(user));
	// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// frame.pack();
	// frame.setVisible(true);
	// }

}
