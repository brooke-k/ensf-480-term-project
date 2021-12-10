/**
 * File: PreferenceForm.java
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

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.bson.Document;
import org.bson.types.ObjectId;

import ensf480.group14.eventListeners.Listener;

public class PreferenceForm implements Form {
	private String buildingType;
	private String cityQuadrant;
	private ObjectId renterID;
	private Integer numOfBedrooms;
	private Double numOfBathrooms;
	private Boolean furnished;
	private Double maxPrice;
	private Double minPrice;
	private ObjectId iD;

	public PreferenceForm() {
		buildingType = null;
		cityQuadrant = null;
		renterID = null;
		numOfBedrooms = null;
		numOfBathrooms = null;
		furnished = null;
		maxPrice = null;
		minPrice = null;
	}

	public static Document toDocument(PreferenceForm preferenceForm) {
		Document prefDoc = new Document("_id", preferenceForm.getID());
		prefDoc.append("building_type", preferenceForm.getBuildingType());
		prefDoc.append("city_quad", preferenceForm.getCityQuadrant());
		prefDoc.append("bedrooms", preferenceForm.getNumOfBedrooms());
		prefDoc.append("bathrooms", preferenceForm.getNumOfBathrooms());
		prefDoc.append("furnished", preferenceForm.isFurnished());
		prefDoc.append("max_price", preferenceForm.getMaxPrice());
		prefDoc.append("min_price", preferenceForm.getMinPrice());
		prefDoc.append("renter_id", preferenceForm.getRenterID());
		return prefDoc;
	}

	public static PreferenceForm getPreferenceForm(Document pfDoc) {
		PreferenceForm return_pf = new PreferenceForm();
		return_pf.buildingType = pfDoc.get("building_type", String.class);
		return_pf.iD = pfDoc.get("_id", ObjectId.class);
		return_pf.cityQuadrant = pfDoc.get("city_quad", String.class);
		return_pf.numOfBathrooms = pfDoc.get("bedrooms", Double.class);
		return_pf.numOfBedrooms = pfDoc.get("bathrooms", Integer.class);
		return_pf.furnished = pfDoc.get("furnished", Boolean.class);
		return_pf.maxPrice = pfDoc.get("max_price", Double.class);
		return_pf.minPrice = pfDoc.get("min_price", Double.class);
		return_pf.renterID = pfDoc.get("renter_id", ObjectId.class);
		return return_pf;
	}

	public JPanel display(Listener listener) {
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
				setNumOfBedrooms(Integer.parseInt(numOfBedroomsField.getText()));
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
				setNumOfBathrooms(Double.parseDouble(numOfBathroomsField.getText()));
			}
		});
		panel.add(numOfBathroomsField);

		JCheckBox furnishedField = new JCheckBox("Furnished");
		furnishedField.setBackground(Color.GRAY);
		furnishedField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
			}

			public void focusLost(FocusEvent e) {
				setFurnished(furnishedField.isSelected());
			}
		});
		panel.add(furnishedField);

		panel.add(new JLabel("City Quadrant"));
		String cityQuadrants[] = { "NW", "NE", "SW", "SE" };
		JComboBox cityQuadrantField = new JComboBox<String>(cityQuadrants);
		cityQuadrantField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
			}

			public void focusLost(FocusEvent e) {
				setCityQuadrant((cityQuadrantField.getItemAt(buildingTypeField.getSelectedIndex())).toString());
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
				setMaxPrice(Double.parseDouble(maxPriceField.getText()));
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
				setMinPrice(Double.parseDouble(minPriceField.getText()));
			}
		});
		panel.add(minPriceField);

		panel.add(Box.createRigidArea(new Dimension(1, 5)));

		JButton submitButton = new JButton("Save Preference");
		submitButton.addActionListener(listener);
		panel.add(submitButton);

		return panel;
	}

	// For testing
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		PreferenceForm form = new PreferenceForm();
		// frame.add(form.display());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
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

	public void print() {
		System.out.println();
		System.out.println(PreferenceForm.toDocument(this).toString());
	}

	public void setRenterID(ObjectId renterID) {
		this.renterID = renterID;
	}

	public void setiD(ObjectId iD) {
		this.iD = iD;
	}

	public ObjectId getRenterID() {
		return renterID;
	}

	public ObjectId getID() {
		return iD;
	}
}
