/**
 * File: PropertyPage.java
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
import ensf480.group14.external.Property;
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
import java.awt.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * View to display a single property
 *
 */

public class PropertyPage {

    private Property currentProp;

    public Property getCurrentProp() {
        return currentProp;
    }

    public void setCurrentProp(Property currentProp) {
        this.currentProp = currentProp;
    }

    private Listener listener;

    public PropertyPage() {

    }

    /**
     * Displays a property and all its details
     * has a button to contact owner
     */
    public JPanel display(Property prop, Listener listen) {

        listener = listen;
        currentProp = prop;
        JPanel panel = new JPanel(new BorderLayout(50, 50));
        if (prop == null)
            return panel;
        // Top panel (address)
        JLabel top = new JLabel(prop.getAddress() + " " + prop.getCityQuad(), SwingConstants.CENTER);
        top.setBackground(Color.GRAY);
        top.setForeground(Color.PINK);
        top.setFont(new Font("Serif", Font.BOLD, 50));
        panel.add(top, BorderLayout.NORTH);

        // Bottom Panel Contact Popout
        JButton contactButton = new JButton("Contact Owner");
        contactButton.setPreferredSize(new Dimension(5, 40));
        contactButton.addActionListener(listener); // opens contact form ; NEED TO KEEP TRACK OF CURRENT PROP use
                                                   // getCurrentProp
        panel.add(contactButton, BorderLayout.SOUTH);

        // Right Panel with details
        String s = "";
        s += prop.getType() + "\n";
        s += "Bedrooms: " + prop.getNumBedrooms().toString() + "\n";
        s += "Bathrooms: " + prop.getNumBathrooms().toString() + "\n";
        s += ((prop.isFurnished()) ? "Furnished" : "Unfurnished") + "\n";
        s += "Rent: $";
        s += String.format("%.02f", prop.getRentCost());

        JTextArea details = new JTextArea(s);
        details.setEditable(false);
        details.setBackground(Color.GRAY);
        details.setForeground(Color.PINK);
        details.setFont(new Font("Serif", Font.BOLD, 25));
        panel.add(details, BorderLayout.EAST);
        panel.setBackground(Color.GRAY);


        //ImageIcon imageIcon2 = new ImageIcon(this.getClass().getResource("images/" + 2 + ".png"));
        //ImageIcon imageIcon3 = new ImageIcon(this.getClass().getResource("images/" + 3 + ".png"));
        // Left/Center Panel
        
        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("images/" + 1 + ".png"));

         if (prop.getType().equals("TownHouse")) {
                imageIcon =  new ImageIcon(this.getClass().getResource("images/" + 2 + ".png"));
         } else if (prop.getType().equals("Apartment")) {
                imageIcon = new ImageIcon(this.getClass().getResource("images/" + 3 + ".png"));
         }

        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        JLabel img = new JLabel(imageIcon);
        // test.setBackground(Color.GRAY);
        // test.setForeground(Color.PINK);
        // test.setFont(new Font("Serif", Font.BOLD, 40));
        // test.setEditable(false);
        img.setPreferredSize(new Dimension(500, 500));
        // test.setHighlighter(null);
        panel.add(img, BorderLayout.WEST);
        panel.setBackground(Color.GRAY);

        return panel;
    }

    // For testing

    // public static void main(String[] args) {
    // JFrame frame = new JFrame();
    // PropertyPage pp = new PropertyPage();

    // // LoginForm login = getOnlyInstance();
    // Property temp = new Property();
    // temp.setRentCost(500.1);
    // temp.setType("House");
    // temp.setAddress("111111");
    // temp.setCityQuad("NW");
    // temp.setNumBedrooms(2);
    // temp.setNumBathrooms(3.0);
    // temp.setFurnished(true);

    // frame.add(pp.display(temp));
    // // frame.setBackground(Color.GRAY);
    // frame.setPreferredSize(new Dimension(900, 600));
    // frame.pack();
    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // frame.setLocationRelativeTo(null);
    // frame.pack();
    // frame.setVisible(true);
    // }

}
