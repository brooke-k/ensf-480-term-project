/**
 * File: LandlordSignUpForm.java
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

package ensf480.group14.forms;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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
 * landlord form to signup as a landlord
 *
 */
public class LandlordSignUpForm implements Form {
    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;

    /**
     * displays form for land lord to sign up
     *
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

        JLabel loginLabel = new JLabel("Landlord SignUp");
        loginLabel.setFont(new Font("Serif", Font.BOLD, 35));
        loginLabel.setSize(40, 40);
        panel.add(loginLabel);

        panel.add(Box.createRigidArea(new Dimension(1, 5)));

        panel.add(new JLabel("Email"));
        JTextField emailField = new JTextField();
        emailField.setSize(190, 20);
        emailField.setMaximumSize(new Dimension(190, 20));
        emailField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                setUsername(emailField.getText());
            }
        });
        panel.add(emailField);

        panel.add(new JLabel("First Name"));
        JTextField firstNameField = new JTextField();
        firstNameField.setSize(190, 20);
        firstNameField.setMaximumSize(new Dimension(190, 20));
        firstNameField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                setFirstName(firstNameField.getText());
            }
        });
        panel.add(firstNameField);
        panel.add(new JLabel("Last name"));
        JTextField lastNameField = new JTextField();
        lastNameField.setSize(190, 20);
        lastNameField.setMaximumSize(new Dimension(190, 20));
        lastNameField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                setLastName(lastNameField.getText());
            }
        });
        panel.add(lastNameField);

        panel.add(new JLabel("Password"));
        JPasswordField passwordField = new JPasswordField();
        passwordField.setEchoChar('*');
        passwordField.setMaximumSize(new Dimension(190, 20));
        passwordField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                setPassword(passwordField.getText());
            }
        });
        panel.add(passwordField);

        panel.add(new JLabel("Confirm Password"));
        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setEchoChar('*');
        confirmPasswordField.setMaximumSize(new Dimension(190, 20));
        confirmPasswordField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                setConfirmPassword(confirmPasswordField.getText());
            }
        });
        panel.add(confirmPasswordField);

        panel.add(Box.createRigidArea(new Dimension(1, 5)));

        JButton landLordSignUpButton = new JButton("Sign Up as Landlord");
        landLordSignUpButton.addActionListener(listener);
        panel.add(landLordSignUpButton);

        return panel;
    }

    /*
     * public Landlord signUp(){
     * // Should talk to db?
     * }
     */

    public LandlordSignUpForm() {

    };

    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    // For testing
    // public static void main(String[] args) {
    //     JFrame frame = new JFrame();
    //     LandlordSignUpForm form = new LandlordSignUpForm();
    //     // frame.add(form.display());
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     frame.pack();
    //     frame.setVisible(true);
    // }
}