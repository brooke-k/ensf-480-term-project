/**
 * File: LoginForm.java
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

/**
 *  The folder which the class lies in the project. 
 */
package ensf480.group14.forms;
/**
 * The import statements used in order for the code to work. 
 */

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ensf480.group14.eventListeners.Listener;

/**
 * This class basically creates a form which is based of the form class and is for creating a login page for the user to put 
 * their credentials in and using the textfield to get these values. 
 * This class mostly just builds from using the frontend(JavaSwing effects which are being used to make it display nicely)
 * To make the GUI this is where the components are located in. 
 */
public class LoginForm implements Form {
    private static LoginForm onlyInstance;
    private String username;
    private String password;
    private ActionListener listener;

    /**
     * Default constructor with no parameters or returns nothing. 
     * @params: Nothing. 
     * @returns: Nothing.
     */
    public LoginForm() {
        username = new String();
        password = new String();
        // listener = listen;
    }

    /**
     * Takes in the listener for the LoginForm constructor
     * @params: Takes the listener for which the user can enter the information in the text field
     * @returns: Nothing. 
     */
    public LoginForm(Listener listen) {
        username = new String();
        password = new String();
        listener = listen;
    }

    /**
     * Unique login form listener is being returned here
     * @params: A listener which is suited for the login 
     * @returns: The login form based on the sucessful information. 
     */
    public static LoginForm getOnlyInstance(Listener loginListener) {
        if (onlyInstance == null) {
            onlyInstance = new LoginForm(loginListener);
        }
        return onlyInstance;
    }

    /**
    * Diplaying the panels, and the layouts which were learnt in the JavaSwing slides in the lectures for a brief amount of 
    * of time. Takes in a listener to watch for an components being pressed by the user on this page. 
    * @params: The listener which is going with the user. 
    * @returns: A panel which can be displayed which contains all of these components. 
    */
    public JPanel display(Listener listener) {
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

        panel.add(new JLabel("Email"));
        JTextField emailField = new JTextField();
        emailField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

            }

            public void focusLost(FocusEvent e) {
                System.out.println("test");
                setUsername(emailField.getText());
            }
        });
        emailField.setSize(190, 20);
        emailField.setMaximumSize(new Dimension(190, 20));
        panel.add(emailField);

        panel.add(new JLabel("Password"));
        JPasswordField passwordField = new JPasswordField();
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

    /**
     * Default Constructor  
     */
    public void login() {
    }
    
    /** Gets the user name of the user
     * @params: Takes in nothing. 
     * @returns: A string which is the username.
     */
    public String getUsername() {
        return username;
    }

    /** Sets the user name of the user 
     * @params: Takes a string which is the username.
     * @returns: Nothing.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /** Gets the password of the user 
     * @params: Takes in nothing. 
     * @returns: A string which is the password.
     */
    public String getPassword() {
        return password;
    }

    /** Sets the password of the user 
     * @params: Takes in a string which is the password. 
     * @returns: Nothing.
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
    * This is how we were testing the forms sepeartely when the whole application was still building. 
    */
    
    // For testing

    // public static void main(String[] args) {
    // JFrame frame = new JFrame();
    // LoginForm login = new LoginForm();
    // frame.add(login.display());
    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // frame.pack();
    // frame.setVisible(true);
    // }

}
