package ensf480.group14.forms;

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

import ensf480.group14.eventListeners.Listener;

public class LoginForm implements Form {
    private static LoginForm onlyInstance;
    private String username;
    private String password;
    private ActionListener listener;

    public LoginForm() {
        username = new String();
        password = new String();
        //listener = listen;
    }

    public LoginForm(ActionListener listen) {
        username = new String();
        password = new String();
        listener = listen;
    }

    public static LoginForm getOnlyInstance(ActionListener loginListener) {
        if (onlyInstance == null) {
            onlyInstance = new LoginForm(loginListener);
        }
        return onlyInstance;
    }

    public JPanel display() {
        JPanel panel = new JPanel();
        Dimension expectDimension = new Dimension(300, 300);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));

        panel.setPreferredSize(expectDimension);
        panel.setMaximumSize(expectDimension);
        panel.setMinimumSize(expectDimension);

        panel.setBackground(Color.GRAY);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setFont(new Font("Serif", Font.BOLD, 35));
        loginLabel.setSize(40, 40);
        panel.add(loginLabel);

        panel.add(Box.createRigidArea(new Dimension(1, 5)));

        panel.add(new JLabel("Username"));
        JTextField usernameField = new JTextField();
        usernameField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

            }

            public void focusLost(FocusEvent e) {
                System.out.println("test");
                setUsername(usernameField.getText());
            }
        });
        usernameField.setSize(190, 20);
        usernameField.setMaximumSize(new Dimension(190, 20));
        panel.add(usernameField);

        panel.add(new JLabel("Password"));
        JTextField passwordField = new JTextField();
        passwordField.setMaximumSize(new Dimension(190, 20));
        passwordField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

            }

            public void focusLost(FocusEvent e) {
                setPassword(passwordField.getText());
            }
        });
        panel.add(passwordField);

        panel.add(Box.createRigidArea(new Dimension(1, 5)));

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(listener);
        panel.add(loginButton);

        JButton signUpButton = new JButton("Or Sign Up");
        signUpButton.addActionListener(listener);
        panel.add(signUpButton);

        JButton continueButton = new JButton("Continue without Logging in");
        continueButton.addActionListener(listener);
        panel.add(continueButton);

        return panel;
    }

    public void login() {
        // This should query the db to verify password, ideally in the db controller?
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // For testing
    
      public static void main(String[] args) {
        JFrame frame = new JFrame();
        LoginForm login = new LoginForm();
        frame.add(login.display());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
      }
     
}
