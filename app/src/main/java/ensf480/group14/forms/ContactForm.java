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

package ensf480.group14.forms;

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
 * Form to send email to a landlord
 *
 */

public class ContactForm implements Form {
    private String sendEmailAddress;
    private String message;

    /**
     * display form to contact the landlord of the property you are viewing
     *
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
     * parses email to a landlord
     *
     */
    Email parseEmail(String body, String sendAddr) {
        Email mail = new Email(body, sendAddr);
        return mail;
    }
    
    public String getSenderEmailAddress() {
        return sendEmailAddress;
    }

    public void setSenderEmailAddress(String senderEmail) {
        this.sendEmailAddress = senderEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // For testing
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        ContactForm form = new ContactForm();
        // frame.add(form.display());
        frame.setPreferredSize(new Dimension(524, 368));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
