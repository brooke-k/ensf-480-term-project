package ensf480.group14.external;

import org.bson.Document;

public class Property {
	private String iD;
	private String address;
	private String cityQuad;
	private String rentalState;
	private String landlordName;
	private String dateLastListed;
	private String dateRented;
	private Integer numBedrooms;
	private Double numBathrooms;
	private String landlordEmail;
	private Double listingPrice;
	private Double rentCost;
	private Boolean furnished;
	private Boolean visibleToRenters;

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
		this.listingPrice = property.listingPrice;
		this.rentCost = property.rentCost;
		this.visibleToRenters = property.visibleToRenters;
	}

	Property() {
		iD = null;

		address = null;
		cityQuad = null;
		rentalState = null;
		landlordName = null;
		dateRented = null;
		dateLastListed = null;

		numBedrooms = null;
		numBathrooms = null;
		landlordEmail = null;

		listingPrice = null;
		rentCost = null;
		visibleToRenters = false;
	}

	public Property(Document propertyDocument) {
		this(Property.toProperty(propertyDocument));

	}

	public Document toDoc() {
		return Property.toDoc(this);
	}

	static Property toProperty(Document propertyDoc) {
		Property returnProp = new Property();
		returnProp.address = propertyDoc.get("address", String.class);
		returnProp.iD = propertyDoc.get("_id", String.class);
		returnProp.cityQuad = propertyDoc.get("city_quad", String.class);
		returnProp.rentalState = propertyDoc.get("rental_state", String.class);
		returnProp.landlordName = propertyDoc.get("landlord_name", String.class);
		returnProp.landlordEmail = propertyDoc.get("landlord_email", String.class);
		returnProp.dateLastListed = propertyDoc.get("date_last_listed", String.class);
		returnProp.dateRented = propertyDoc.get("date_rented", String.class);
		returnProp.numBedrooms = propertyDoc.get("bedrooms", Integer.class);
		returnProp.numBathrooms = propertyDoc.get("bathrooms", Double.class);
		returnProp.listingPrice = propertyDoc.get("listing_cost", Double.class);
		returnProp.rentCost = propertyDoc.get("rent_cost", Double.class);
		returnProp.visibleToRenters = propertyDoc.get("visible_to_renters", Boolean.class);
		return returnProp;

	}

	static Document toDoc(Property prop) {
		Document propDoc = new Document("address", prop.getAddress());
		propDoc.append("_id", prop.getiD());
		propDoc.append("city_quad", prop.getCityQuad());
		propDoc.append("rental_state", prop.getRentalState());
		propDoc.append("landlord_name", prop.getLandlordName());
		propDoc.append("landlord_email", prop.getLandlordName());
		propDoc.append("date_last_listed", prop.getDateLastListed());
		propDoc.append("date_rented", prop.getDateRented());
		propDoc.append("bedrooms", prop.getNumBathrooms());
		propDoc.append("bathrooms", prop.getNumBedrooms());
		propDoc.append("listing_cost", prop.getListingPrice());
		propDoc.append("rent_cost", prop.getRentCost());
		propDoc.append("visible_to_renters", prop.isVisibleToRenters());
		return propDoc;
	}

	public String getiD() {
		return iD;
	}

	public void setiD(String iD) {
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

	public String getDateLastListed() {
		return dateLastListed;
	}

	public void setDateLastListed(String dateLastListed) {
		this.dateLastListed = dateLastListed;
	}

	public String getDateRented() {
		return dateRented;
	}

	public void setDateRented(String dateRented) {
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

	public String getLandlordID() {
		return landlordEmail;
	}

	public void setLandlordID(String landlordEmail) {
		this.landlordEmail = landlordEmail;
	}

	public Double getListingPrice() {
		return listingPrice;
	}

	public void setListingPrice(Double listingPrice) {
		this.listingPrice = listingPrice;
	}

	public Double getRentCost() {
		return rentCost;
	}

	public void setRentCost(Double rentCost) {
		this.rentCost = rentCost;
	}

	public boolean isVisibleToRenters() {
		return visibleToRenters;
	}

	public void setVisibleToRenters(boolean visibleToRenters) {
		this.visibleToRenters = visibleToRenters;
	}

	public String toString() {
		String asString = "\n\r";
		asString = asString + "Address: " + address + "\n\r";
		asString = asString + "ID: " + iD + "\n\r";
		asString = asString + "City quadrant: " + cityQuad + "\n\r";
		asString = asString + "Rental state: " + rentalState + "\n\r";
		asString = asString + "Number of bedrooms: " + numBedrooms + "\n\r";
		asString = asString + "Number of bathrooms: " + numBathrooms + "\n\r";
		asString = asString + "Rental cost per month: " + rentCost + "\n\r";
		asString = asString + "Date last listed: " + dateLastListed + "\n\r";
		asString = asString + "Date rented: " + dateRented + "\n\r";
		asString = asString + "Name of landlord: " + landlordName + "\n\r";
		asString = asString + "ID of landlord: " + landlordEmail + "\n\r";
		asString = asString + "Listing price: " + listingPrice + "\n\r";
		asString = asString + "Visible to public search: " + ((visibleToRenters.booleanValue()) ? "Yes" : "No")
				+ "\n\r";

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

}
