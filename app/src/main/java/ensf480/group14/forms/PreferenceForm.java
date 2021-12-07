package ensf480.group14.forms;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.rmi.server.RemoteRef;
import java.util.Base64;
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

import com.mongodb.BasicDBObject;

public class PreferenceForm implements Form {
	private String buildingType;
	private String cityQuadrant;
	private String ID;
	private Integer numOfBedrooms;
	private Double numOfBathrooms;
	private Boolean furnished;
	private Double maxPrice;
	private Double minPrice;

	public PreferenceForm() {

	}

	public static PreferenceForm getPreferenceForm(Document pfDoc) {
		PreferenceForm return_pf = new PreferenceForm();
		return_pf.buildingType = pfDoc.get("building_type", String.class);
		return_pf.ID = pfDoc.get("_id", String.class);
		return_pf.cityQuadrant = pfDoc.get("city_quadrant", String.class);
		return_pf.numOfBathrooms = pfDoc.get("bedrooms", Double.class);
		return_pf.numOfBedrooms = pfDoc.get("bathrooms", Integer.class);
		return_pf.furnished = pfDoc.get("furnished", Boolean.class);
		return_pf.maxPrice = pfDoc.get("max_price", Double.class);
		return_pf.minPrice = pfDoc.get("min_price", Double.class);
		return return_pf;
	}

	public JPanel display() {
		JPanel panel = new JPanel();

		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0, 1));
		panel.setBackground(Color.GRAY);
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel loginLabel = new JLabel("Preference Form");
		loginLabel.setFont(new Font("Serif", Font.BOLD, 35));
		loginLabel.setSize(40, 40);
		panel.add(loginLabel);

		panel.add(new JLabel("Building Type"));
		String buildingTypes[] = { "House", "Apartment", "TownHouse" };
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
		String cityQuadrants[] = { "NW", "NE", "SW", "SE" };
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

		JButton submitButton = new JButton("Save");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (evt.getSource() == submitButton) {
					setBuildingType((buildingTypeField.getItemAt(buildingTypeField.getSelectedIndex())).toString());
					setCityQuadrant((cityQuadrantField.getItemAt(buildingTypeField.getSelectedIndex())).toString());
					setFurnished(furnishedField.isSelected());
					setMaxPrice(Double.parseDouble(maxPriceField.getText()));
					setMinPrice(Double.parseDouble(minPriceField.getText()));
					setNumOfBathrooms(Double.parseDouble(numOfBathroomsField.getText()));
					setNumOfBedrooms(Integer.parseInt(numOfBedroomsField.getText()));
				}
			}
		});
		panel.add(submitButton);

		return panel;
	}

	// For testing
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		PreferenceForm form = new PreferenceForm();
		frame.add(form.display());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}

	public Integer getNumOfBedrooms() {
		return numOfBedrooms;
	}

	public void setNumOfBedrooms(Integer numOfBedrooms) {
		this.numOfBedrooms = numOfBedrooms;
	}

	public Double getNumOfBathrooms() {
		return numOfBathrooms;
	}

	public void setNumOfBathrooms(Double numOfBathrooms) {
		this.numOfBathrooms = numOfBathrooms;
	}

	public Boolean isFurnished() {
		return furnished;
	}

	public void setFurnished(Boolean furnished) {
		this.furnished = furnished;
	}

	public String getCityQuadrant() {
		return cityQuadrant;
	}

	public void setCityQuadrant(String cityQuadrant) {
		this.cityQuadrant = cityQuadrant;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}
}
