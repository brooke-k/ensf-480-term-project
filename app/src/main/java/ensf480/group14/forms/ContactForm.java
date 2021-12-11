/**
 * File: ContactForm.java
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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ensf480.group14.eventListeners.Listener;

import ensf480.group14.external.Email;

/**
 * This class basically creates a form which is based of the form class and is for when the user wants to contact
 * another user it will input the email address and the message which needs to be inputted by the user. 
 * This class mostly just builds from using the frontend(JavaSwing effects which are being used to make it display nicely)
 * To make the GUI this is where the components are located in. 
 */
public class ContactForm implements Form {
    private String sendEmailAddress;
    private String message;

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
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(expectDimension);
        panel.setMaximumSize(expectDimension);
        panel.setMinimumSize(expectDimension);

        panel.setBackground(Color.GRAY);

        JLabel contactLabel = new JLabel("Contact");
        contactLabel.setFont(new Font("Serif", Font.BOLD, 35));
        contactLabel.setSize(40, 40);
        contactLabel.setAlignmentX(12);
        panel.add(contactLabel);

        panel.add(Box.createRigidArea(new Dimension(1, 5)));
        JLabel emailLabel = new JLabel("Enter Your Email Address");
        emailLabel.setAlignmentX(10);
        panel.add(emailLabel);
        JTextField userEmail = new JTextField(27);
        userEmail.setPreferredSize(new Dimension(190, 27));
        userEmail.setMaximumSize(userEmail.getPreferredSize());
        panel.add(userEmail);
        userEmail.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

            }

            public void focusLost(FocusEvent e) {
            setSenderEmailAddress(userEmail.getText());
            }
        });

        JLabel bodyLabel = new JLabel("Body");
        bodyLabel.setAlignmentX(16);
        panel.add(bodyLabel);
        JTextArea emailBody = new JTextArea(50, 50);
        emailBody.setSize(300, 60);
        emailBody.setMaximumSize(new Dimension(300, 60));
        emailBody.setColumns(300);
        emailBody.setLineWrap(true);
        panel.add(emailBody);
        emailBody.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

            }

            public void focusLost(FocusEvent e) {
            setMessage(emailBody.getText());
            }
        });

        panel.add(Box.createRigidArea(new Dimension(1, 5)));

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(listener);
        panel.add(sendButton);

        return panel;
    }

     /**
     * This is basically a function for parsing the email to the landlord. 
     * @params: Takes in the body and sender email address
     * @returns: An email object is returned. 
     */
    Email parseEmail(String body, String sendAddr) {
        Email mail = new Email(body, sendAddr);
        return mail;
    }
    /**
     * This is for getting the sender their email address. 
     * @params: Takes in nothing. 
     * @returns: Gives back a string which is the sender of the email. 
     */
    public String getSenderEmailAddress() {
        return sendEmailAddress;
    }

     /**
     * This is for setting the sender their email address. 
     * @params: Takes in the sender their email address. 
     * @returns: Returns Nothing. 
     */
    public void setSenderEmailAddress(String senderEmail) {
        this.sendEmailAddress = senderEmail;
    }

    /**
     * This is for getting the message of the email.  
     * @params: Takes in nothing. 
     * @returns: Returns the message of the email. 
     */
    public String getMessage() {
        return message;
    }

    /**
     * This is for setting the message of the email.  
     * @params: Takes in the message of the email.   
     * @returns: Returns Nothing. 
     */
    public void setMessage(String message) {
        this.message = message;
    }


   /**
    * This is how we were testing the forms sepeartely when the whole application was still building. 
    */

    // // For testing
    // public static void main(String[] args) {
    //     JFrame frame = new JFrame();
    //     ContactForm form = new ContactForm();
    //     // frame.add(form.display());
    //     frame.setPreferredSize(new Dimension(524, 368));

    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     frame.pack();
    //     frame.setLocationRelativeTo(null);
    //     frame.setVisible(true);
    // }
}
