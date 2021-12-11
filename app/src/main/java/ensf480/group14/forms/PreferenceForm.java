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

/**
 *  The folder which the class lies in the project. 
 */
package ensf480.group14.forms;
/**
 * The import statements used in order for the code to work. 
 */

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

/**
 * This class basically creates a form which is based of the form class and is for creating the preference form which the user can basically save their search results. 
 * It is similar to search but does a different functionality insteads notifies the user if the property like this is in the database. 
 * Also has the attributes of the property which can be seen here. 
 * This class mostly just builds from using the frontend(JavaSwing effects which are being used to make it display nicely)
 * To make the GUI this is where the components are located in. 
 */
public class PreferenceForm implements Form {
	private String buildingType;
	private String cityQuadrant;
	private ObjectId renterID;
	private int numOfBedrooms;
	private double numOfBathrooms;
	private boolean furnished;
	private double maxPrice;
	private double minPrice;
	private ObjectId iD;

	/**
	 * Default constructor which intializes the attributes to null as a starting point. 
     * @params: Nothing. 
	 * @returns: Nothing. 
	 */
	public PreferenceForm() {
		buildingType = null;
		cityQuadrant = null;
		renterID = null;
		numOfBedrooms = 0;
		numOfBathrooms = 0;
		furnished = false;
		maxPrice = 0;
		minPrice = 0;
	}

	/**
	 * Is for adding the users choices into the database by using the preference form values which are stored their. 
	 * Basically updates it in the database by adding these in the following order. 
	 * @params: A preference form for the user is passed in. 
	 * @returns: A document/collection which is in the database. 
	 */
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

	/**
	 * Is for retrieveing the values from the pereference form the user has inputted in the user text fields and storing them 
	 * so in the other function we can add them to the database. 
	 * @params: A document/collection which is in the database. 
	 * @returns: A preference form for the user is returned.  
	 */
	public static PreferenceForm getPreferenceForm(Document pfDoc) {
		PreferenceForm return_pf = new PreferenceForm();
		return_pf.buildingType = pfDoc.get("building_type", String.class);
		return_pf.iD = pfDoc.get("_id", ObjectId.class);
		return_pf.cityQuadrant = pfDoc.get("city_quad", String.class);
		return_pf.numOfBedrooms = pfDoc.get("bedrooms", Integer.class);
		return_pf.numOfBathrooms = pfDoc.get("bathrooms", Double.class);
		return_pf.furnished = pfDoc.get("furnished", Boolean.class);
		return_pf.maxPrice = pfDoc.get("max_price", Double.class);
		return_pf.minPrice = pfDoc.get("min_price", Double.class);
		return_pf.renterID = pfDoc.get("renter_id", ObjectId.class);
		return return_pf;
	}

	/**
    * Diplaying the panels, and the layouts which were learnt in the JavaSwing slides in the lectures for a brief amount of 
    * of time. Takes in a listener to watch for an components being pressed by the user on this page. 
    * @params: The listener which is going with the user. 
    * @returns: A panel which can be displayed which contains all of these components. 
    */
	public JPanel display(Listener listener) {
		JPanel panel = new JPanel();
		buildingType = "House";
		cityQuadrant = "NW";
		numOfBedrooms = 0;
		numOfBathrooms = 0;
		furnished = false;
		maxPrice = 0;
		minPrice = 0;
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0, 1));
		panel.setBackground(Color.GRAY);
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel loginLabel = new JLabel("Preference Form");
		loginLabel.setFont(new Font("Serif", Font.BOLD, 35));
		loginLabel.setSize(40, 40);
		panel.add(loginLabel);

		panel.add(new JLabel("Building Type"));
		String buildingTypes[] = {"", "House", "Apartment", "TownHouse" };
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
		String cityQuadrants[] = { "","NW", "NE", "SW", "SE" };
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

		/**
	 * Gets the type of the property from the preference form. 
	 * @params: Takes in Nothing. 
	 * @returns: Returns the type of the property from the preference form. 
	 */
	public String getBuildingType() {
		return buildingType;
	}
	/**
	 * Sets the type of the property from the preference form. 
	 * @params: Takes in the type of the property from the preference form. 
	 * @returns: Returns Nothing.
	 */
	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}

	/**
	 * Gets the how many bedrooms of the property from the preference form. 
	 * @params: Takes in Nothing. 
	 * @returns: Returns the number of bedrooms of the property from the preference form.  
	 */
	public Integer getNumOfBedrooms() {
		return numOfBedrooms;
	}

	/**
	 * Sets how many bedrooms of the property from the preference form.  
	 * @params: Takes in how many bedrooms of the property from the preference form. 
	 * @returns: Returns Nothing. 
	 */
	public void setNumOfBedrooms(Integer numOfBedrooms) {
		this.numOfBedrooms = numOfBedrooms;
	}

	/**
	 * Gets the how many bathrooms of the property from the preference form. 
	 * @params: Takes in Nothing. 
	 * @returns: Returns the number of bathrooms of the property from the preference form.  
	 */
	public Double getNumOfBathrooms() {
		return numOfBathrooms;
	}

	/**
	 * Sets how many bathrooms of the property from the preference form.  
	 * @params: Takes in how many bathrooms of the property from the preference form. 
	 * @returns: Returns Nothing. 
	 */
	public void setNumOfBathrooms(Double numOfBathrooms) {
		this.numOfBathrooms = numOfBathrooms;
	}

		/**
	  * Checks if it's furnished or not the property from the preference form based on the user. 
	  * @params: Takes in nothing 
	  * @returns: Gives a boolean about the flag back. 
	  */
	public Boolean isFurnished() {
		return furnished;
	}

	/**
	 * Sets the furnsihed boolean from the preference form based on the user.
	 * @params: Takes in the furnsihed boolean and sets it from the preference form based on the user.
	 * @returns: Returns Nothing. 
	 */
	public void setFurnished(Boolean furnished) {
		this.furnished = furnished;
	}

	/**
	 * Gets the quadarant from the preference form based on the user. 
	 * @params: Takes in Nothing. 
	 * @returns: Returns the quadarant from the preference form based on the user. 
	 */
	public String getCityQuadrant() {
		return cityQuadrant;
	}

	/**
	 * Sets the quadarant preference form based on the user. 
	 * @params: Takes in the quadarant preference form based on the user. 
	 * @returns: Returns Nothing. 
	 */
	public void setCityQuadrant(String cityQuadrant) {
		this.cityQuadrant = cityQuadrant;
	}

	/**
	 * Gets the max price from the preference form based on the user. 
	 * @params: Takes in Nothing. 
	 * @returns: Returns the max price from the preference form based on the user. 
	 */
	public Double getMaxPrice() {
		return maxPrice;
	}

	/**
	 * Sets the max price from the preference form based on the user. 
	 * @params: Takes in the max price from the preference form based on the user. 
	 * @returns: Nothing
	 */
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	/**
	 * Gets the min price from the preference form based on the user. 
	 * @params: Takes in Nothing. 
	 * @returns: Returns the min price from the preference form based on the user. 
	 */
	public Double getMinPrice() {
		return minPrice;
	}

	/**
	 * Sets the min price from the preference form based on the user. 
	 * @params: Takes in the min price from the preference form based on the user. 
	 * @returns: Nothing
	 */
	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}
	/**
	 * Used for printing the preference form.
	 * @params: Nothing.
	 * @returns: Noting. 
	 */
	public void print() {
		System.out.println();
		System.out.println(PreferenceForm.toDocument(this).toString());
	}

	/**
	 * Sets the renter ID from the as an objectid.
	 * @params: Takes in the renter ID from the as an objectid.
	 * @returns: Nothing. 
	 */
	public void setRenterID(ObjectId renterID) {
		this.renterID = renterID;
	}

	/**
	 * Sets the id of the objectid type. 
	 * @params: Takes in the id of the objectid type. 
	 * @returns: Nothing.
	 */
	public void setiD(ObjectId iD) {
		this.iD = iD;
	}

	/**
	 * Gets the renter ID from the as an objectid.
	 * @params: Takes in nothing. 
	 * @returns: The object id which is for the renter. 
	 */
	public ObjectId getRenterID() {
		return renterID;
	}

	/**
	 * Gets the id of the objectid type. 
	 * @params: Takes in nothing. 
	 * @returns: The object id which is for the user.
	 */
	public ObjectId getID() {
		return iD;
	}

	
	/**
    * This is how we were testing the forms sepeartely when the whole application was still building. 
    */
	
	// For testing
	// public static void main(String[] args) {
	// 	JFrame frame = new JFrame();
	// 	PreferenceForm form = new PreferenceForm();
	// 	// frame.add(form.display());
	// 	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// 	frame.pack();
	// 	frame.setVisible(true);
	// }
}
