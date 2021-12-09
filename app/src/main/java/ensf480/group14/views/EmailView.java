/**
 * File: EmailView.java
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import ensf480.group14.external.Email;

/**
 * View for single email
 *
 */
public class EmailView {

    private ActionListener listener;

    public EmailView() {

    }

    /**
     * displays a single email with sender subject body
     *
     */
    public JPanel display(Email email, ActionListener listen) {

        listener = listen;
        JPanel panel = new JPanel(new BorderLayout(50, 50));
        JPanel header = new JPanel(new GridLayout(0, 1));
        // Top panel (sender)
        JLabel top = new JLabel(email.getSender(), SwingConstants.CENTER);
        top.setBackground(Color.GRAY);
        top.setForeground(Color.PINK);
        top.setFont(new Font("Serif", Font.BOLD, 25));
        header.add(top);

        JLabel top1 = new JLabel("Subject: " + email.getSubject(), SwingConstants.CENTER);
        top1.setBackground(Color.GRAY);
        top1.setForeground(Color.PINK);
        top1.setFont(new Font("Serif", Font.BOLD, 20));
        header.add(top1);
        header.setBackground(Color.GRAY);
        header.setForeground(Color.PINK);
        panel.add(header, BorderLayout.NORTH);

        JTextArea details = new JTextArea(email.getBody());
        details.setEditable(false);
        details.setBackground(Color.GRAY);
        details.setForeground(Color.PINK);
        details.setLineWrap(true);
        details.setWrapStyleWord(true);
        details.setFont(new Font("Serif", Font.BOLD, 20));
        panel.add(new JScrollPane(details), BorderLayout.CENTER);
        panel.setBackground(Color.GRAY);

        return panel;
    }

    // For testing

    // public static void main(String[] args) {
    // JFrame frame = new JFrame();

    // // LoginForm login = getOnlyInstance();
    // Email temp = new Email(
    // "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
    // tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
    // quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
    // consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
    // cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
    // proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    // "Tester@fake.com", "12312 SouthHill ");

    // EmailView em = new EmailView();

    // frame.add(em.display(temp));
    // // frame.setBackground(Color.GRAY);
    // frame.setPreferredSize(new Dimension(900, 600));
    // frame.pack();
    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // frame.setLocationRelativeTo(null);
    // frame.pack();
    // frame.setVisible(true);
    // }

}
