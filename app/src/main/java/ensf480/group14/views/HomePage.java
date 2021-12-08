package ensf480.group14.views;

import ensf480.group14.dbcontrol.DatabaseController;
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

public class HomePage {
	private ActionListener listener;

	public HomePage() {

	}


	public JPanel display(User user,ActionListener listen) {
		listener = listen;
		JPanel panel = new JPanel();
		Dimension expectDimension = new Dimension(300, 300);

		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0, 1));

		panel.setPreferredSize(expectDimension);
		panel.setMaximumSize(expectDimension);
		panel.setMinimumSize(expectDimension);

		panel.setBackground(Color.GRAY);

		panel.setAlignmentX(Component.CENTER_ALIGNMENT);

		// JLabel loginLabel = new JLabel("Login");
		// loginLabel.setFont(new Font("Serif", Font.BOLD, 35));
		// loginLabel.setSize(40, 40);
		// panel.add(loginLabel);
		if (!user.getType().equals("manager")){
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(listener);      //search form --> search results
		panel.add(searchButton);
		}

		if (user.getType().equals("registered_renter")) {
			panel.add(Box.createRigidArea(new Dimension(1, 5)));
			JButton preferenceButton = new JButton("Notifications Settings");  //open preference form
			preferenceButton.addActionListener(listener);
			panel.add(preferenceButton);
		}
		if (user.getType().equals("manager")) {
			panel.add(Box.createRigidArea(new Dimension(1, 5)));
			JButton accessButton = new JButton("Access Database");    //opens search results with all properties
			accessButton.addActionListener(listener);
			panel.add(accessButton);

			panel.add(Box.createRigidArea(new Dimension(1, 5)));
			JButton feesButton = new JButton("Adjust Fees");    //opens dialog box with pay period and amount
			feesButton.addActionListener(listener);                 // also needs to change those in DB/Billing
			panel.add(feesButton);

			panel.add(Box.createRigidArea(new Dimension(1, 5)));
			JButton reportButton = new JButton("Generate Report");  //generates report as txt file and opens it
			reportButton.addActionListener(listener);              //brooke already made this in Report
			panel.add(reportButton);
		}
		if (user.getType().equals("landlord")) {
			
			panel.add(Box.createRigidArea(new Dimension(1, 5)));
			JButton managePropertyButton = new JButton("Manage Properties");      //Open search results with only properties that the landlord owns
			managePropertyButton.addActionListener(listener);
			panel.add(managePropertyButton);
			panel.add(Box.createRigidArea(new Dimension(1, 5)));
			JButton addPropertyButton = new JButton("New Property Application");   //Open Property Application
			addPropertyButton.addActionListener(listener);
			panel.add(addPropertyButton);
			
		}
		if(user.getType().equals("landlord")||user.getType().equals("registered_renter")){
			panel.add(Box.createRigidArea(new Dimension(1, 5)));
			JButton addPropertyButton = new JButton("Inbox");                   //Open Inbox page needs array of emails addressed to user
			addPropertyButton.addActionListener(listener);
			panel.add(addPropertyButton);
		}
		return panel;
	}

	// For testing

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		ObjectId id = new ObjectId();
        User user = new RegisteredRenter("an email", id, "banana");
		HomePage loggedIn = new HomePage();

		// LoginForm login = getOnlyInstance();
		frame.add(loggedIn.display(user));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
