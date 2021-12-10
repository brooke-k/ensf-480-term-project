/**
 * File: Search.java
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
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ensf480.group14.eventListeners.Listener;

public class Search implements Form {
    private String buildingType;
    private int numOfBedrooms;
    private double numOfBathrooms;
    private String furnished;
    private String cityQuadrant;
    private double maxPrice;
    private double minPrice;
    Pattern pat;
    Matcher mat;
    Pattern patBed;
    boolean notNumerical;

    public Search() {
        pat = Pattern.compile("[^0-9.]");
        patBed = Pattern.compile("[^0-9]");
        notNumerical = false;
    }

    public JPanel display(Listener listener) {

        buildingType = "";
        numOfBedrooms = 0;
        numOfBathrooms = 0;
        furnished = "";
        cityQuadrant = "";
        maxPrice  = 0;
        minPrice = 0;
        JPanel panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.setBackground(Color.GRAY);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel loginLabel = new JLabel("Search");
        loginLabel.setFont(new Font("Serif", Font.BOLD, 35));
        loginLabel.setSize(40, 40);
        panel.add(loginLabel);

        panel.add(new JLabel("Building Type"));
        String buildingTypes[] = { "","House", "Apartment", "TownHouse" };
        JComboBox buildingTypeField = new JComboBox<String>(buildingTypes);
        buildingTypeField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {

                
                setBuildingType((buildingTypeField.getItemAt(buildingTypeField.getSelectedIndex())).toString());
            }
        });
        panel.add(buildingTypeField);

        panel.add(new JLabel("Number of Bedrooms"));
        JTextField numOfBedroomsField = new JTextField();
        numOfBedroomsField.setSize(190, 20);
        numOfBedroomsField.setMaximumSize(new Dimension(190, 20));
        numOfBedroomsField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                mat = patBed.matcher(numOfBedroomsField.getText());
                notNumerical = mat.find();
                if(notNumerical){
                    setNumOfBedrooms(-1);
                } else {
                    setNumOfBedrooms(Integer.parseInt(numOfBedroomsField.getText()));
                }
            }
        });
        panel.add(numOfBedroomsField);

        panel.add(new JLabel("Number of Bathrooms"));
        JTextField numOfBathroomsField = new JTextField();
        numOfBathroomsField.setSize(190, 20);
        numOfBathroomsField.setMaximumSize(new Dimension(190, 20));
        numOfBathroomsField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                mat = pat.matcher(numOfBathroomsField.getText());
                notNumerical = mat.find();
                if(notNumerical){
                    setNumOfBathrooms(-1);
                } else {
                    setNumOfBathrooms(Double.parseDouble(numOfBathroomsField.getText()));
                }
            }
        });
        panel.add(numOfBathroomsField);

        panel.add(new JLabel("Furnishing"));
        String furnishedStates[] = {"", "Unfurnished", "Furnished"};
        JComboBox furnishedField = new JComboBox<String>(furnishedStates);
        furnishedField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                setFurnished(furnishedField.getItemAt(furnishedField.getSelectedIndex()).toString());
            }
        });
        panel.add(furnishedField);

        panel.add(new JLabel("City Quadrant"));
        String cityQuadrants[] = {"", "NW", "NE", "SW", "SE" };
        JComboBox cityQuadrantField = new JComboBox<String>(cityQuadrants);
        cityQuadrantField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                setCityQuadrant((cityQuadrantField.getItemAt(cityQuadrantField.getSelectedIndex())).toString());
            }
        });
        panel.add(cityQuadrantField);

        panel.add(new JLabel("Max Price"));
        JTextField maxPriceField = new JTextField();
        maxPriceField.setSize(190, 20);
        maxPriceField.setMaximumSize(new Dimension(190, 20));
        maxPriceField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                mat = pat.matcher(maxPriceField.getText());
                notNumerical = mat.find();
                if(notNumerical){
                    setMaxPrice(-1);
                } else {
                    setMaxPrice(Integer.parseInt(maxPriceField.getText()));
                }
      
            }
        });
        panel.add(maxPriceField);

        panel.add(new JLabel("Min Price"));
        JTextField minPriceField = new JTextField();
        minPriceField.setSize(190, 20);
        minPriceField.setMaximumSize(new Dimension(190, 20));
        minPriceField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                mat = pat.matcher(maxPriceField.getText());
                notNumerical = mat.find();
                if(notNumerical){
                    setMinPrice(-1);
                } else {
                    setMinPrice(Double.parseDouble(minPriceField.getText()));
                }
            }
        });
        panel.add(minPriceField);

        panel.add(Box.createRigidArea(new Dimension(1, 5)));

        JButton submitButton = new JButton("Search");
        submitButton.addActionListener(listener);
        panel.add(submitButton);

        return panel;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public int getNumOfBedrooms() {
        return numOfBedrooms;
    }

    public void setNumOfBedrooms(int numOfBedrooms) {
        this.numOfBedrooms = numOfBedrooms;
    }

    public double getNumOfBathrooms() {
        return numOfBathrooms;
    }

    public void setNumOfBathrooms(double numOfBathrooms) {
        this.numOfBathrooms = numOfBathrooms;
    }

    public String getFurnished() {
        return furnished;
    }

    public void setFurnished(String furnished) {
        this.furnished = furnished;
    }

    public String getCityQuadrant() {
        return cityQuadrant;
    }

    public void setCityQuadrant(String cityQuadrant) {
        this.cityQuadrant = cityQuadrant;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    // For testing
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Search form = new Search();
        // frame.add(form.display());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
