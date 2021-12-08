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

public class HomePage {
	private ActionListener listener;

	public HomePage() {

	}

	public HomePage(ActionListener listen) {
		listener = listen;
	}

	public JPanel display(User user) {
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
		JButton searchButton = new JButton("Search Properties");
		searchButton.addActionListener(listener);
		panel.add(searchButton);

		if (user.getType().equals("registered_renter")) {
			panel.add(Box.createRigidArea(new Dimension(1, 5)));
			JButton preferenceButton = new JButton("Notifications Settings");
			preferenceButton.addActionListener(listener);
			panel.add(preferenceButton);
		}
		if (user.getType().equals("manager")) {
			panel.add(Box.createRigidArea(new Dimension(1, 5)));
			JButton accessButton = new JButton("Access Database");
			accessButton.addActionListener(listener);
			panel.add(accessButton);

		}
		if (user.getType().equals("landlord")) {
			panel.add(Box.createRigidArea(new Dimension(1, 5)));
			JButton managePropertyButton = new JButton("Manage Properties");
			managePropertyButton.addActionListener(listener);
			panel.add(managePropertyButton);
		}
		return panel;
	}

	// For testing

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		User user = new RegisteredRenter("an email", "an ID", "manager");
		HomePage loggedIn = new HomePage();

		// LoginForm login = getOnlyInstance();
		frame.add(loggedIn.display(user));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
