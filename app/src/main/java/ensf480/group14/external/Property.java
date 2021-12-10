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

package ensf480.group14.external;

import java.util.Calendar;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Property {
	private ObjectId iD;
	private String address;
	private String cityQuad;
	private String rentalState;
	private String landlordName;
	private Calendar dateLastListed;
	private Calendar dateRented;
	private Integer numBedrooms;
	private Double numBathrooms;
	private String landlordEmail;
	private Double rentCost;
	private Boolean rented;
	private Boolean furnished;
	private String type;
	private Boolean visibleToRenters;
	private ObjectId landlordID;

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
	}

	public Property() {

		address = null;
		cityQuad = null;
		rentalState = null;
		landlordName = null;
		dateRented = null;
		dateLastListed = null;

		numBedrooms = null;
		numBathrooms = null;
		landlordEmail = null;
		landlordID = null;
		type = null;

		rentCost = null;
		rented = false;
		visibleToRenters = false;
	}

	public Property(Document propertyDocument) {
		this(Property.getProperty(propertyDocument));

	}

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
		returnProp.dateLastListed = propertyDoc.get("date_last_listed", Calendar.class);
		returnProp.dateRented = propertyDoc.get("date_rented", Calendar.class);
		returnProp.numBedrooms = propertyDoc.get("bedrooms", Integer.class);
		returnProp.numBathrooms = propertyDoc.get("bathrooms", Double.class);
		returnProp.rentCost = propertyDoc.get("rent_cost", Double.class);
		returnProp.rented = propertyDoc.get("rented", Boolean.class);
		returnProp.visibleToRenters = propertyDoc.get("visible_to_renters", Boolean.class);
		return returnProp;

	}

	public static Document toDocument(Property prop) {
		Document propDoc = new Document("address", prop.getAddress());
		if (prop.getiD() != null) {
			propDoc.append("_id", prop.getiD());
		}
		propDoc.append("city_quad", prop.getCityQuad());
		propDoc.append("rental_state", prop.getRentalState());
		propDoc.append("landlord_name", prop.getLandlordName());
		propDoc.append("landlord_email", prop.getLandlordName());
		propDoc.append("date_last_listed", prop.getDateLastListed());
		propDoc.append("date_rented", prop.getDateRented());
		propDoc.append("bathrooms", prop.getNumBathrooms());
		propDoc.append("bedrooms", prop.getNumBedrooms());
		propDoc.append("rent_cost", prop.getRentCost());
		propDoc.append("rented", prop.isRented());
		propDoc.append("visible_to_renters", prop.isVisibleToRenters());
		propDoc.append("type", prop.getType());
		propDoc.append("landlord_id", prop.getLandlordID());
		return propDoc;
	}

	public ObjectId getiD() {
		return iD;
	}

	public void setiD(ObjectId iD) {
		this.iD = iD;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCityQuad() {
		return cityQuad;
	}

	public void setCityQuad(String cityQuad) {
		this.cityQuad = cityQuad;
	}

	public String getRentalState() {
		return rentalState;
	}

	public void setRentalState(String rentalState) {
		this.rentalState = rentalState;
	}

	public String getLandlordName() {
		return landlordName;
	}

	public void setLandlordName(String landlordName) {
		this.landlordName = landlordName;
	}

	public Calendar getDateLastListed() {
		return dateLastListed;
	}

	public void setDateLastListed(Calendar dateLastListed) {
		this.dateLastListed = dateLastListed;
	}

	public Calendar getDateRented() {
		return dateRented;
	}

	public void setDateRented(Calendar dateRented) {
		this.dateRented = dateRented;
	}

	public Integer getNumBedrooms() {
		return numBedrooms;
	}

	public void setNumBedrooms(Integer numBedrooms) {
		this.numBedrooms = numBedrooms;
	}

	public Double getNumBathrooms() {
		return numBathrooms;
	}

	public void setNumBathrooms(Double numBathrooms) {
		this.numBathrooms = numBathrooms;
	}

	public ObjectId getLandlordID() {
		return landlordID;
	}

	public void setLandlordID(ObjectId landlordID) {
		this.landlordID = landlordID;
	}

	public Double getRentCost() {
		return rentCost;
	}

	public void setRentCost(Double rentCost) {
		this.rentCost = rentCost;
	}

	public Boolean isVisibleToRenters() {
		return visibleToRenters;
	}

	public void setVisibleToRenters(Boolean visibleToRenters) {
		this.visibleToRenters = visibleToRenters;
	}

	public String toString() {
		String asString = "\n\r";
		asString = asString + "Name of landlord: " + landlordName + "\n\r";
		asString = asString + "ID: " + iD + "\n\r";
		asString = asString + "Address: " + address + "\n\r";

		return asString;
	}

	public void print() {
		System.out.println(this.toString());
	}

	public Boolean isFurnished() {
		return furnished.booleanValue();
	}

	public void setFurnished(boolean furnished) {
		this.furnished = Boolean.valueOf(furnished);
	}

	public String getLandlordEmail() {
		return landlordEmail;
	}

	public void setLandlordEmail(String landlordEmail) {
		this.landlordEmail = landlordEmail;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isRented() {
		return rented;
	}
	public void setRented(boolean rented) {
		this.rented = rented;
	}

}
