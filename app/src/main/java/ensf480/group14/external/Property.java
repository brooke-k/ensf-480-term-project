/**
 * File: Property.java
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
package ensf480.group14.external;
/**
 * The import statements used in order for the code to work. 
 */
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * This class basically creates a property object with the following attributes and is used when 
 * creating a new property object we can use the default constructors to instantiate them. Also
 * keeps a track of attributes. Also has the option to wheter to make it visible to the renters or not. 
 */
public class Property {
	private ObjectId iD;
	private String address;
	private String cityQuad;
	private String rentalState;
	private String landlordName;
	private String dateLastListed;
	private String dateRented;
	private Integer numBedrooms;
	private Double numBathrooms;
	private String landlordEmail;
	private Double rentCost;
	private Boolean rented;
	private Boolean furnished;
	private String type;
	private Boolean visibleToRenters;
	private ObjectId landlordID;
	private String dateLastPaid;

	/**
	 * Constructor takes a property object in and instantiates the variables. 
	 * @params: Takes in the property object
	 * @returns: Nothing. 
	 */
	private Property(Property property) {
		this.iD = property.iD;
		this.address = property.address;
		this.cityQuad = property.cityQuad;
		this.rentalState = property.rentalState;
		this.landlordName = property.landlordName;
		this.dateLastListed = property.dateLastListed;
		this.dateRented = property.dateRented;
		this.numBedrooms = property.numBedrooms;
		this.numBathrooms = property.numBathrooms;
		this.landlordEmail = property.landlordEmail;
		this.rentCost = property.rentCost;
		this.rented = property.rented;
		this.visibleToRenters = property.visibleToRenters;
		this.type = property.type;
		this.landlordID = property.landlordID;
		this.furnished = property.furnished;
		this.dateLastPaid = property.dateLastPaid;
	}

	/**
	 * Default constructor which sets the attributes which are null, or false based on the values. 
	 * @params: Takes in Nothing. 
	 * @returns: Nothing. 
	 */
	public Property() {

		address = null;
		cityQuad = null;
		rentalState = null;
		landlordName = null;
		dateRented = null;
		dateLastListed = null;
		dateLastPaid = null;

		numBedrooms = null;
		numBathrooms = null;
		landlordEmail = null;
		landlordID = null;
		type = null;
		furnished = false;

		rentCost = null;
		rented = false;
		visibleToRenters = false;
	}

	/**
	 * Constructor which gets the property collection. 
	 * @params: Takes in the property document. 
	 * @returns: Nothing. 
	 */
	public Property(Document propertyDocument) {
		this(Property.getProperty(propertyDocument));

	}

	/**
	 * Constructor which instanties the property attributes based on the rentalState. 
	 * @params: Takes in the attributes for the property which are associated with the property. 
	 * @returns: Nothing. 
	 */
	public Property(String address, String cityQuad, int numBed,
			double numBath, boolean furnished, double price, String type) {
		this.address = address;
		this.cityQuad = cityQuad;
		this.numBedrooms = Integer.valueOf(numBed);
		this.numBathrooms = Double.valueOf(numBath);
		this.furnished = Boolean.valueOf(furnished);
		this.rentCost = Double.valueOf(price);
		this.rented = false;
		this.rentalState = "suspended";
		this.dateLastListed = java.time.LocalDate.now().toString();
		this.dateRented = "";
		this.visibleToRenters = false;
		this.type = type;
		this.dateLastPaid = "";
	}

	/**
	 * Constructor which instanties the for the landlord. 
	 * @params: Takes in the attributes for the landlordName and id, and address for the property. 
	 * @returns: Nothing. 
	 */
	public Property(String landlordName, ObjectId id, String address){
		this.landlordName = landlordName;
		this.iD = id;
		this.address = address;
	}

	/**
	 * Gets the property specifically based on the document of the property, 
	 * @params: Takes in the collection of property. 
	 * @returns: Returns the property which matches all of these attributes. 
	 */
	public static Property getProperty(Document propertyDoc) {
		Property returnProp = new Property();
		returnProp.address = propertyDoc.get("address", String.class);
		returnProp.iD = propertyDoc.get("_id", ObjectId.class);
		returnProp.cityQuad = propertyDoc.get("city_quad", String.class);
		returnProp.rentalState = propertyDoc.get("rental_state", String.class);
		returnProp.landlordName = propertyDoc.get("landlord_name", String.class);
		returnProp.landlordID = propertyDoc.get("landlord_id", ObjectId.class);
		returnProp.landlordEmail = propertyDoc.get("landlord_email", String.class);
		returnProp.type = propertyDoc.get("type", String.class);
		returnProp.dateLastListed = propertyDoc.get("date_last_listed", String.class);
		returnProp.dateRented = propertyDoc.get("date_rented", String.class);
		returnProp.numBedrooms = propertyDoc.get("bedrooms", Integer.class);
		returnProp.numBathrooms = propertyDoc.get("bathrooms", Double.class);
		returnProp.rentCost = propertyDoc.get("rent_cost", Double.class);
		returnProp.rented = propertyDoc.get("rented", Boolean.class);
		returnProp.visibleToRenters = propertyDoc.get("visible_to_renters", Boolean.class);
		returnProp.furnished = propertyDoc.get("furnished", Boolean.class);
		returnProp.dateLastPaid = propertyDoc.get("date_last_paid", String.class);
		return returnProp;

	}
	
	/**
	 * Adds the property information into the Property object. 
	 * @params: Takes in the property object which is used. 
	 * @returns: Returns the collection of the property. 
	 */
	public static Document toDocument(Property prop) {
		Document propDoc = new Document("address", prop.getAddress());
		if (prop.getiD() != null) {
			propDoc.append("_id", prop.getiD());
		}
		propDoc.append("city_quad", prop.getCityQuad());
		propDoc.append("rental_state", prop.getRentalState());
		propDoc.append("landlord_name", prop.getLandlordName());
		propDoc.append("landlord_email", prop.getLandlordEmail());
		propDoc.append("date_last_listed", prop.getDateLastListed());
		propDoc.append("date_rented", prop.getDateRented());
		propDoc.append("bathrooms", prop.getNumBathrooms());
		propDoc.append("bedrooms", prop.getNumBedrooms());
		propDoc.append("rent_cost", prop.getRentCost());
		propDoc.append("rented", prop.isRented());
		propDoc.append("visible_to_renters", prop.isVisibleToRenters());
		propDoc.append("type", prop.getType());
		propDoc.append("furnished", prop.isFurnished());
		propDoc.append("landlord_id", prop.getLandlordID());
		propDoc.append("date_last_paid", prop.getDateLastPaid());
		return propDoc;
	}

	/**
	 * Gets the id of the property. 
	 * @params: Takes in Nothing. 
	 * @returns: Returns the id of the property.  
	 */
	public ObjectId getiD() {
		return iD;
	}

	/**
	 * Sets the id of the property. 
	 * @params: Takes in the id of the property. 
	 * @returns: Returns Nothing. 
	 */
	public void setiD(ObjectId iD) {
		this.iD = iD;
	}

	/**
	 * Gets the address of the property. 
	 * @params: Takes in Nothing. 
	 * @returns: Returns the address of the property.  
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Sets the address of the property. 
	 * @params: Takes in the address of the property. 
	 * @returns: Returns Nothing. 
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the quadarant in which it is in of the property. 
	 * @params: Takes in Nothing. 
	 * @returns: Returns the quadarant of the property.  
	 */
	public String getCityQuad() {
		return cityQuad;
	}

	/**
	 * Sets the quadarant in which it is in of the property. 
	 * @params: Takes in the quadarant in which it is in of the property. 
	 * @returns: Returns Nothing. 
	 */
	public void setCityQuad(String cityQuad) {
		this.cityQuad = cityQuad;
	}

	/**
	 * Gets the rental state of the property. 
	 * @params: Takes in Nothing. 
	 * @returns: Returns the rental state of the property.  
	 */
	public String getRentalState() {
		return rentalState;
	}

	/**
	 * Sets the rental state of the property. 
	 * @params: Takes in the rental state of the property. 
	 * @returns: Returns Nothing. 
	 */
	public void setRentalState(String rentalState) {
		this.rentalState = rentalState;
	}

	/**
	 * Gets the landlordname of the landlord.
	 * @params: Takes in Nothing. 
	 * @returns: Returns the landlordname of the landlord.  
	 */
	public String getLandlordName() {
		return landlordName;
	}

	/**
	 * Sets the landlordname of the landlord. 
	 * @params: Takes in landlordname of the landlord. 
	 * @returns: Returns Nothing. 
	 */
	public void setLandlordName(String landlordName) {
		this.landlordName = landlordName;
	}

	/**
	 * Gets the date listed of the property. 
	 * @params: Takes in Nothing. 
	 * @returns: Returns the date listed of the property.  
	 */
	public String getDateLastListed() {
		return dateLastListed;
	}

	/**
	 * Sets the date listed of the property. 
	 * @params: Takes in the date listed of the property. 
	 * @returns: Returns Nothing. 
	 */
	public void setDateLastListed(String dateLastListed) {
		this.dateLastListed = dateLastListed;
	}

	/**
	 * Gets the date when the property is rented.
	 * @params: Takes in Nothing. 
	 * @returns: Returns the date when the property is rented.
	 */
	public String getDateRented() {
		return dateRented;
	}

	/**
	 * Sets the date when the property is rented.
	 * @params: Takes in the date when the property is rented.
	 * @returns: Returns Nothing. 
	 */
	public void setDateRented(String dateRented) {
		this.dateRented = dateRented;
	}

	/**
	 * Gets the how many bedrooms of the property. 
	 * @params: Takes in Nothing. 
	 * @returns: Returns the number of bedrooms of the property.  
	 */
	public Integer getNumBedrooms() {
		return numBedrooms;
	}

	/**
	 * Sets how many bedrooms of the property. 
	 * @params: Takes in how many bedrooms of the property. 
	 * @returns: Returns Nothing. 
	 */
	public void setNumBedrooms(Integer numBedrooms) {
		this.numBedrooms = numBedrooms;
	}

	/**
	 * Gets the how many bathrooms of the property. 
	 * @params: Takes in Nothing. 
	 * @returns: Returns the number of bathrooms of the property.  
	 */
	public Double getNumBathrooms() {
		return numBathrooms;
	}

	/**
	 * Sets how many bathrooms of the property.  
	 * @params: Takes in how many bathrooms of the property.  
	 * @returns: Returns Nothing. 
	 */
	public void setNumBathrooms(Double numBathrooms) {
		this.numBathrooms = numBathrooms;
	}

	/**
	 * Gets the landlordid of the landlord
	 * @params: Takes in Nothing. 
	 * @returns: Returns the landlordid of landlord.  
	 */
	public ObjectId getLandlordID() {
		return landlordID;
	}

	/**
	 * Sets the landlordid of the landlord.
	 * @params: Takes in the landlordid of the landlord.,
	 * @returns: Returns Nothing. 
	 */
	public void setLandlordID(ObjectId landlordID) {
		this.landlordID = landlordID;
	}

	/**
	 * Gets the rental cost of the property. 
	 * @params: Takes in Nothing. 
	 * @returns: Returns the rental cost of the property.  
	 */
	public Double getRentCost() {
		return rentCost;
	}

	/**
	 * Sets the rental cost of the property. 
	 * @params: Takes in the rental cost of the property. 
	 * @returns: Returns Nothing. 
	 */
	public void setRentCost(Double rentCost) {
		this.rentCost = rentCost;
	}

 	/**
	  * Checks if it's visible to renters or not. 
	  * @params: Takes in nothing 
	  * @returns: Gives a boolean about the flag back. 
	  */
	public Boolean isVisibleToRenters() {
		return visibleToRenters;
	}

	/**
	 * Sets the visible renters boolean. 
	 * @params: Takes in the visible renters boolean and sets it. 
	 * @returns: Returns Nothing. 
	 */
	public void setVisibleToRenters(Boolean visibleToRenters) {
		this.visibleToRenters = visibleToRenters;
	}

	/**
	 * Just converting the strings into appending for showing in the views. 
	 * @params: Takes nothing. 
	 * @returns: A string. 
	 */
	public String toString() {
		String asString = "\n\r";
		asString = asString + "    Name of landlord: " + landlordName + "\n\r";
		asString = asString + "    ID: " + iD + "\n\r";
		asString = asString + "    Address: " + address + "\n\r";

		return asString;
	}
	/**
	 * Used for printing when debugging. 
	 * @params: Nothing.
	 * @returns: Nothing. 
	 */
	public void print() {
		System.out.println(this.toString());
	}

	/**
	  * Checks if it's furnished or not the property. 
	  * @params: Takes in nothing 
	  * @returns: Gives a boolean about the flag back. 
	  */
	public Boolean isFurnished() {
		return furnished.booleanValue();
	}

	/**
	 * Sets the furnsihed boolean. 
	 * @params: Takes in the furnsihed boolean and sets it. 
	 * @returns: Returns Nothing. 
	 */
	public void setFurnished(boolean furnished) {
		this.furnished = Boolean.valueOf(furnished);
	}

	/**
	 * Gets the landlord's email. 
	 * @params: Takes in Nothing. 
	 * @returns: Returns the landlordemail. 
	 */
	public String getLandlordEmail() {
		return landlordEmail;
	}

	/**
	 * Sets the landlord's email. 
	 * @params: Takes in the landlord's email. 
	 * @returns: Returns Nothing.
	 */
	public void setLandlordEmail(String landlordEmail) {
		this.landlordEmail = landlordEmail;
	}

	/**
	 * Gets the type of the property. 
	 * @params: Takes in Nothing. 
	 * @returns: Returns the type of the property.  
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type of the property. 
	 * @params: Takes in the type of the property. 
	 * @returns: Returns Nothing.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	  * Checks if it's rented or not the property. 
	  * @params: Takes in nothing 
	  * @returns: Gives a boolean about the flag back. 
	  */
	public boolean isRented() {
		return rented;
	}

	/**
	  * Sets if it's rented or not the property. 
	  * @params: Takes in the rented boolean to set it.
	  * @returns: Returns Nothing. 
	  */
	public void setRented(boolean rented) {
		this.rented = rented;
	}

	/**
	 * Gets the date last paid of the property. 
	 * @params: Takes in Nothing. 
	 * @returns: Returns the date last paid of property.  
	 */
	public String getDateLastPaid() {
		return dateLastPaid;
	}

	/**
	 * Sets the date last paid of the property. 
	 * @params: Takes in the date last paid of the property. 
	 * @returns: Returns Nothing.
	 */
	public void setDateLastPaid(String dateLastPaid) {
		this.dateLastPaid = dateLastPaid;
	}

}