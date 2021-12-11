/**
 * File: LandlordDBControl.java
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
package ensf480.group14.dbcontrol;
/**
 * The import statements used in order for the code to work. 
 */
import java.util.HashSet;

import javax.swing.JOptionPane;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import ensf480.group14.external.Property;
import ensf480.group14.forms.PayInfoForm;
import ensf480.group14.forms.PropertyApplication;
import ensf480.group14.views.EditPropertyView;
/**
 * Controller for the user type Landlord and builds on the registered rentered.
 * It lets the landlord to change the property if it needs to be visible to the renters or not. 
 * Lets them also create the property they want to rent. 
 * Also lets the user pay the fee for their application when they are creating a property in the database.
 */
public class LandlordDBControl extends RegisteredRenterDBController {

	/**
	 * Default constructor 
	 */
	public LandlordDBControl() {
		super();
	}
	/**
	 * Updates the rental state to cancelled if the property has been removed 
	 * @params: The address of the property which is basically the unique id. 
	 * @returns: Nothing just interfaces with the database. 
	 */
	public void cancelProperty(String address) {
		Bson updates = Updates.combine(
				Updates.set("visible_to_renters", false),
				Updates.set("rental_state", "cancelled"));

		propertiesCollection.updateOne(new Document("address", address), updates,
				new UpdateOptions().upsert(true));
	}

	/**
	 * Updates the rental state to the renters to make it either visible or not. 
	 * @params: The address of the property which is basically the unique id, boolean visible to update it. 
	 * @returns: Nothing just interfaces with the database. 
	 */
	public void changePropertyState(String address, boolean visible) {
		Bson updates = Updates.combine(
			Updates.set("visible_to_renters", visible));


	propertiesCollection.updateOne(new Document("address", address), updates,
			new UpdateOptions().upsert(false));
	}

	/**
	 * Retrieves the property based on the primary key which is the address in this case. 
	 * @params: The address of the property which is basically unique. 
	 * @returns: The property of the landlord based on the address. 
	 */
	public Property getProperty(String address) {
		return Property.getProperty(getFirstObject("address", address, "properties"));
	}

	/**
	 * Adds the property to the database with creating the collection Property. 
	 * Also checks if the address is already present in the database or not and if it is will not 
	 * add it to the database. 
	 * @params: The property which has been created, and will be put into the database. 
	 * @returns: Nothing just interfaces with the database. 
	 */
	public boolean addPropertyToDatabase(Property property) {
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("address", property.getAddress());
		FindIterable<Document> findIter = propertiesCollection.find(searchQuery);
		MongoCursor<Document> resultCursor = findIter.iterator();
		if (resultCursor.hasNext()) { // Meaning the property already exists in the
										// database and should not be added as a duplicate
			resultCursor.close();
			return false;
		}

		resultCursor.close();

		propertiesCollection.insertOne(Property.toDocument(property)); //add prop same addy
		logCollection.insertOne(new Document()
			.append("date", java.time.LocalDate.now().toString())
			.append("type", "listing")
		);
		return true;
	}

	
	/**
	 * Lets the property to be edited not all of the attributes are able to changed the specific ones are below. 
	 * The main which cannot be changed is the address, and the city quadrant to prevent the landlord to 
	 * change his old property to the new property and avoid the application fee is stopped with this. 
	 * @params: The property which already exists in the database and belongs to the landlord. 
	 * @returns: Nothing just interfaces with the database. 
	 */
	public void editProperty(EditPropertyView prop, Property property) {
		boolean wasRented = property.isRented();
		
		Bson updates = Updates.combine(
				Updates.set("bathrooms", prop.getNumBath()),
				Updates.set("bedrooms", prop.getNumBed()),
				Updates.set("furnished", prop.isFurnished()),
				Updates.set("rent_cost", prop.getPrice()),
				Updates.set("visible_to_renters", !prop.isRented()),
				Updates.set("rental_state", prop.isRented() ? "rented" : "active"),
				Updates.set("rented", prop.isRented()));

		if(!wasRented && prop.isRented()){
			logCollection.insertOne(new Document()
				.append("_id", new ObjectId())
				.append("type", "rental")
				.append("date", java.time.LocalDate.now().toString())
				.append("landlord_name", property.getLandlordName())
				.append("address", property.getAddress())
				.append("landlord_email", property.getLandlordEmail())
			);
		}

		propertiesCollection.updateOne(new Document("address", prop.getAddress()), updates,
				new UpdateOptions().upsert(true));
	}

	/**
	 * This is for the landlord to pay for the fee for the application which they submit to the property system. 
	 * @params: Takes in the payment form to be displayed to the user, and the property application form .
	 * @returns: Returns a boolean object based on if they have paid the fee or not just created the application form. 
	 */
	public Boolean payFee(PayInfoForm paymentForm, Property propertyAppForm) {
		Bson updates = Updates.combine(
				Updates.set("rental_state", "active"),
				Updates.set("visible_to_renters", true),
				Updates.set("date_last_paid", java.time.LocalDate.now().toString()));
		UpdateResult res = propertiesCollection.updateOne(new Document("address", propertyAppForm.getAddress()),
				updates,
				new UpdateOptions().upsert(false));
				
		if (res.getMatchedCount() > 0) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * For the landlord addresses if they have multiple properties it will add them into a hashset, 
	 * this also allows the landlord to view their properties because we have assigned the landlord id to the property.  
	 * @params: Takes in a object of id which is for the landlord id. 
	 * @returns: Returns a hashset called props so we can call this landlord address in a view with their properties. 
	 */
	public static HashSet<String> getLandlordsAddresses(ObjectId id) {
		FindIterable<Document> findIter = propertiesCollection.find(new Document("landlord_id", id));
		MongoCursor<Document> resultCursor = findIter.iterator();

		HashSet<String> props = new HashSet<>(0);
		while (resultCursor.hasNext()) {
			props.add((String) resultCursor.next().get("address"));
		}

		return props;
	}
}