/**
 * File: PropertyApplication.java
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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ensf480.group14.eventListeners.Listener;

/**
 * This class basically creates a form which is based of the form class and is for creating the property application form for the user 
 * landlord to create his proerty to be rented which also has attributes of the properties and the landlord enters the information. 
 * This class mostly just builds from using the frontend(JavaSwing effects which are being used to make it display nicely)
 * To make the GUI this is where the components are located in. 
 */
public class PropertyApplication implements Form {
    // private String userEmailAddress;
    // private String message;
    int id;
    String addr;
    int numBed;
    double numBath;
    boolean furnish;
    String cityQuad;
    double price;
    String type;

    Pattern pat;
    Matcher mat;
    Pattern patBed;
    boolean notNumerical;

    /**
     * Using regex to make sure the landlord does not input something funky which messes the output, error checking component is here. 
     * Also sets the default type and city quadarant. 
     * @params: Nothing.
     * @returns: Nothing. 
     */
    public PropertyApplication() {
        pat = Pattern.compile("[^0-9.]");
        patBed = Pattern.compile("[^0-9]");
        notNumerical = false;
        type = "House";
        cityQuad = "NW";
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

        JLabel contactLabel = new JLabel("Property Application");
        contactLabel.setFont(new Font("Serif", Font.BOLD, 35));
        contactLabel.setSize(40, 40);
        panel.add(contactLabel);

        panel.add(Box.createRigidArea(new Dimension(1, 5)));

        panel.add(new JLabel("Building Type"));
        String buildingTypes[] = { "House", "Apartment", "TownHouse" };
        JComboBox buildingTypeField = new JComboBox<String>(buildingTypes);
        buildingTypeField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                setType((buildingTypeField.getItemAt(buildingTypeField.getSelectedIndex())).toString());
            }
        });
        panel.add(buildingTypeField);

        panel.add(new JLabel("Address"));
        JTextField address = new JTextField();
        address.setSize(190, 20);
        address.setMaximumSize(new Dimension(190, 20));
        address.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                setAddress(address.getText());
            }
        });
        panel.add(address);

        panel.add(new JLabel("Number of Bedrooms"));
        JTextField numBedrooms = new JTextField();
        numBedrooms.setSize(190, 20);
        numBedrooms.setMaximumSize(new Dimension(190, 20));
        numBedrooms.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                mat = patBed.matcher(numBedrooms.getText());
                notNumerical = mat.find();
                if(notNumerical){
                    setNumBed(-1);
                } else {
                    setNumBed(Integer.parseInt(numBedrooms.getText()));
                }
                
            }
        });
        panel.add(numBedrooms);

        panel.add(new JLabel("Number of Bathrooms"));
        JTextField numBathrooms = new JTextField();
        numBathrooms.setSize(190, 20);
        numBathrooms.setMaximumSize(new Dimension(190, 20));
        numBathrooms.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                mat = pat.matcher(numBathrooms.getText());
                notNumerical = mat.find();
                 if(notNumerical){
                    setNumBath(-1);
                } else {
                    setNumBath(Double.parseDouble(numBathrooms.getText()));
                }
                
            }
        });
        panel.add(numBathrooms);

        JLabel checkLabel = new JLabel("Furnished");
        panel.add(checkLabel);
        JCheckBox furnished = new JCheckBox();
        furnished.setOpaque(false);

        // furnished.setText("Furnished");
        // furnished.setSize(20, 20);
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
        String cityQuadrants[] = {"NW", "NE", "SW", "SE" };
        JComboBox cityQuadrantField = new JComboBox<String>(cityQuadrants);
        cityQuadrantField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                setCityQuad((cityQuadrantField.getItemAt(cityQuadrantField.getSelectedIndex())).toString());
            }
        });
        panel.add(cityQuadrantField);

        panel.add(new JLabel("Rent"));
        JTextField price = new JTextField();
        price.setSize(190, 20);
        price.setMaximumSize(new Dimension(190, 20));
        price.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                mat = pat.matcher(price.getText());
                notNumerical = mat.find();
                if(notNumerical){
                    setPrice(-1);
                } else {
                    setPrice(Double.parseDouble(price.getText()));
                }
                
            }
        });
        panel.add(price);

        panel.add(Box.createRigidArea(new Dimension(1, 5)));

        JButton submitButton = new JButton("Submit Application");
        submitButton.addActionListener(listener); // needs to open pay form
        panel.add(submitButton);

        return panel;
    }

    /**
	 * Gets the id of the objectid type. 
	 * @params: Takes in nothing. 
	 * @returns: The object id which is for the user.
	 */
    public int getId() {
        return id;
    }

    /**
	 * Sets the id of the objectid type. 
	 * @params: Takes in the id of the objectid type. 
	 * @returns: Nothing.
	 */
    public void setId(int id) {
        this.id = id;
    }

    /**
	 * Gets the address of the property from the property application. 
	 * @params: Takes in Nothing. 
	 * @returns: Returns the address of the property from the property application. 
	 */
    public String getAddress() {
        return addr;
    }

    /**
	 * Sets the address of the property from the property application. 
	 * @params: Takes in the address of the property from the property application. 
	 * @returns: Returns Nothing. 
	 */
    public void setAddress(String address) {
        this.addr = address;
    }

    /**
	 * Gets the how many bedrooms of the property from the property application. 
	 * @params: Takes in Nothing. 
	 * @returns: Returns the number of bedrooms of the property from the property application. 
	 */
    public int getNumBed() {
        return numBed;
    }

    /**
	 * Sets how many bedrooms of the property from the property application. 
	 * @params: Takes in how many bedrooms of the property from the property application. 
	 * @returns: Returns Nothing. 
	 */
    public void setNumBed(int numBed) {
        this.numBed = numBed;
    }

    /**
	 * Gets the how many bathrooms of the property from the property application. 
	 * @params: Takes in Nothing. 
	 * @returns: Returns the number of bathrooms of the property from the property application. 
	 */
    public double getNumBath() {
        return numBath;
    }
    
    /**
	 * Sets how many bathrooms of the property from the property application. 
	 * @params: Takes in how many bathrooms of the property from the property application. 
	 * @returns: Returns Nothing. 
	 */
    public void setNumBath(double numBath) {
        this.numBath = numBath;
    }

    /**
	  * Checks if it's furnished or not from the property application. 
	  * @params: Takes in nothing 
	  * @returns: Gives a boolean about the flag back from the property application.
	  */
    public boolean isFurnished() {
        return furnish;
    }

    /**
	 * Sets the furnsihed boolean from the property application. 
	 * @params: Takes in the furnsihed boolean from the property application. 
	 * @returns: Returns Nothing. 
	 */
    public void setFurnish(boolean furnish) {
        this.furnish = furnish;
    }

    /**
	 * Gets the quadarant from the property application. 
	 * @params: Takes in Nothing. 
	 * @returns: Returns the quadarant from the property application. 
	 */
    public String getCityQuad() {
        return cityQuad;
    }

     /**
	 * Sets the quadarant from the property application. 
	 * @params: Takes in the quadarant from the property application. 
	 * @returns: Returns Nothing.
	 */
    public void setCityQuad(String cityQuad) {
        this.cityQuad = cityQuad;
    }

    /**
	 * Gets the price from the property application. 
	 * @params: Takes in Nothing. 
	 * @returns: Returns the price from the property application. 
	 */
    public double getPrice() {
        return price;
    }

    /**
	 * Sets the price from the property application. 
	 * @params: Takes inthe price from the property application. 
	 * @returns: Returns 
	 */
    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    // For testing
    // public static void main(String[] args) {
    // JFrame frame = new JFrame();
    // PropertyApplication form = new PropertyApplication();
    // frame.add(form.display());
    // frame.setPreferredSize(new Dimension(624,768));

    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // frame.pack();
    // frame.setLocationRelativeTo(null);
    // frame.setVisible(true);
    // }
}
