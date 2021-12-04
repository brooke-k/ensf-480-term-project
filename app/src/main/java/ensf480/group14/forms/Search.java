package ensf480.group14.forms;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Search implements Form {
    private String buildingType;
    private int numOfBedrooms;
    private double numOfBathrooms;
    private boolean furnished;
    private String cityQuadrant;
    private double maxPrice;
    private double minPrice;

    public Search() {

    }

    public JPanel display() {
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
        String buildingTypes[] = {"House", "Apartment", "TownHouse"};
        JComboBox buildingTypeField = new JComboBox<String>(buildingTypes);
        panel.add(buildingTypeField);

        panel.add(new JLabel("Number of Bedrooms"));
        JTextField numOfBedroomsField = new JTextField();
        numOfBedroomsField.setSize(190, 20);
        numOfBedroomsField.setMaximumSize(new Dimension(190, 20));
        panel.add(numOfBedroomsField);

        panel.add(new JLabel("Number of Bathrooms"));
        JTextField numOfBathroomsField = new JTextField();
        numOfBathroomsField.setSize(190, 20);
        numOfBathroomsField.setMaximumSize(new Dimension(190, 20));
        panel.add(numOfBathroomsField);

        JCheckBox furnishedField = new JCheckBox("Furnished");
        furnishedField.setBackground(Color.GRAY);
        panel.add(furnishedField);

        panel.add(new JLabel("City Quadrant"));
        String cityQuadrants[] = {"NW", "NE", "SW", "SE"};
        JComboBox cityQuadrantField = new JComboBox<String>(cityQuadrants);
        panel.add(cityQuadrantField);

        panel.add(new JLabel("Max Price"));
        JTextField maxPriceField = new JTextField();
        maxPriceField.setSize(190, 20);
        maxPriceField.setMaximumSize(new Dimension(190, 20));
        panel.add(maxPriceField);

        panel.add(new JLabel("Min Price"));
        JTextField minPriceField = new JTextField();
        minPriceField.setSize(190, 20);
        minPriceField.setMaximumSize(new Dimension(190, 20));
        panel.add(minPriceField);

        panel.add(Box.createRigidArea(new Dimension(1, 5)));

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                if(evt.getSource() == submitButton){
                    setBuildingType((buildingTypeField.getItemAt(buildingTypeField.getSelectedIndex())).toString());
                    setCityQuadrant((cityQuadrantField.getItemAt(buildingTypeField.getSelectedIndex())).toString());
                    setFurnished(furnishedField.isSelected());
                    setMaxPrice(Double.parseDouble(maxPriceField.getText()));
                    setMinPrice(Double.parseDouble(minPriceField.getText()));
                    setNumOfBathrooms(Integer.parseInt(numOfBathroomsField.getText()));
                    setNumOfBedrooms(Integer.parseInt(numOfBedroomsField.getText()));
                }
            }
        });
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

    public boolean isFurnished() {
        return furnished;
    }

    public void setFurnished(boolean furnished) {
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
        Search form  = new Search();
        frame.add(form.display());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
