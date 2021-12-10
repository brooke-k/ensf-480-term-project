/**
 * File: EditPropertyView.java
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

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.bson.types.ObjectId;

import ensf480.group14.eventListeners.Listener;
import ensf480.group14.external.Property;

/**
 * View for landlords to edit property
 *
 */
public class EditPropertyView {
    // private String userEmailAddress;
    // private String message;

    String addr;
    int numBed;
    double numBath;
    boolean furnish;
    String cityQuad;
    double price;
    boolean rented;

    private Listener listener;

    /**
     *
     * basically preference form except we overwrite an existing property
     */
    public JPanel display(Property prop, Listener listen) {

        listener = listen;
        JPanel panel = new JPanel();
        if (prop == null)
            return panel;

        addr = prop.getAddress();
        numBed = prop.getNumBedrooms();
        numBath = prop.getNumBathrooms();
        furnish = prop.isFurnished();
        cityQuad = prop.getCityQuad();
        price = prop.getRentCost();
        rented = prop.isRented();

        Dimension expectDimension = new Dimension(300, 300);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));

        panel.setPreferredSize(expectDimension);
        panel.setMaximumSize(expectDimension);
        panel.setMinimumSize(expectDimension);

        panel.setBackground(Color.GRAY);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel editPropertyLabel = new JLabel("Edit Property");
        editPropertyLabel.setFont(new Font("Serif", Font.BOLD, 35));
        editPropertyLabel.setSize(40, 40);
        panel.add(editPropertyLabel);

        panel.add(Box.createRigidArea(new Dimension(1, 5)));

        panel.add(new JLabel("Address"));
        JTextField address = new JTextField(prop.getAddress());
        address.setSize(190, 20);
        address.setEditable(false);
        address.setMaximumSize(new Dimension(190, 20));

        panel.add(address);

        panel.add(new JLabel("Number of Bedrooms"));
        JTextField numBedrooms = new JTextField(prop.getNumBedrooms().toString());
        numBedrooms.setSize(190, 20);
        numBedrooms.setMaximumSize(new Dimension(190, 20));
        numBedrooms.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                setNumBed(Integer.parseInt(numBedrooms.getText()));
            }
        });
        panel.add(numBedrooms);
        panel.add(new JLabel("Number of Bathrooms"));
        JTextField numBathrooms = new JTextField(prop.getNumBedrooms().toString());
        numBathrooms.setSize(190, 20);
        numBathrooms.setMaximumSize(new Dimension(190, 20));
        numBathrooms.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                setNumBath(Double.parseDouble(numBathrooms.getText()));
            }
        });
        panel.add(numBathrooms);

        JLabel furnishCheckLabel = new JLabel("Furnished");
        panel.add(furnishCheckLabel);
        JCheckBox furnished = new JCheckBox();
        furnished.setSelected (prop.isFurnished());
        furnished.setOpaque(false);
        furnished.setMaximumSize(new Dimension(190, 20));
        furnished.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                setFurnish(furnished.isSelected());
            }
        });
        panel.add(furnished);

       
        panel.add(new JLabel("City Quadrant"));
        JTextField quad = new JTextField(prop.getCityQuad());
        quad.setSize(190, 20);
        quad.setMaximumSize(new Dimension(190, 20));
        quad.setEditable(false);
        panel.add(quad);

        panel.add(new JLabel("Rent Amount"));
        String s = String.format("%.02f", prop.getRentCost());
        JTextField price = new JTextField(s);
        price.setSize(190, 20);
        price.setMaximumSize(new Dimension(190, 20));
        price.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                setPrice(Double.parseDouble(price.getText()));
            }
        });
        panel.add(price);

        JLabel rentCheckLabel = new JLabel("Rented");
        panel.add(rentCheckLabel);
        JCheckBox rentedBox = new JCheckBox();
        rentedBox.setSelected (prop.isRented());
        rentedBox.setOpaque(false);
        rentedBox.setMaximumSize(new Dimension(190, 20));
        rentedBox.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                setRented(rentedBox.isSelected());
            }
        });
        panel.add(rentedBox);


        panel.add(Box.createRigidArea(new Dimension(1, 5)));

        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(listener);
        panel.add(saveButton);

        JButton removeButton = new JButton("Remove Property");
        removeButton.addActionListener(listener);
        panel.add(removeButton);

        return panel;
    }

    public String getAddress() {
        return addr;
    }

    public void setAddress(String address) {
        this.addr = address;
    }

    public int getNumBed() {
        return numBed;
    }

    public void setNumBed(int numBed) {
        this.numBed = numBed;
    }

    public double getNumBath() {
        return numBath;
    }

    public void setNumBath(double numBath) {
        this.numBath = numBath;
    }

    public boolean isFurnished() {
        return furnish;
    }

    public void setFurnish(boolean furnish) {
        this.furnish = furnish;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public String getCityQuad() {
        return cityQuad;
    }

    public void setCityQuad(String cityQuad) {
        this.cityQuad = cityQuad;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // For testing
    // public static void main(String[] args) {
    // JFrame frame = new JFrame();
    // EditPropertyView editView = new EditPropertyView();
    // Property temp = new Property();
    // temp.setRentCost(500.1);
    // temp.setType("House");
    // temp.setAddress("111111");
    // temp.setCityQuad("NW");
    // temp.setNumBedrooms(2);
    // temp.setNumBathrooms(3.0);
    // temp.setFurnished(true);

    // frame.add(editView.display(temp));
    // frame.setPreferredSize(new Dimension(624, 768));

    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // frame.pack();
    // frame.setLocationRelativeTo(null);
    // frame.setVisible(true);
    // }
}
