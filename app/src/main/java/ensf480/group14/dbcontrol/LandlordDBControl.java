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

package ensf480.group14.dbcontrol;

import java.util.HashSet;

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

public class LandlordDBControl extends RegisteredRenterDBController {

	public LandlordDBControl() {
		super();
	}

	protected void removePropertyFromDatabase(String address) {
		System.out.println("Removing property with the address \"" + address + "\" from database");
		propertiesCollection.deleteOne(Filters.eq("address", address));
		System.out.println("Property with the address \"" + address + "\" has been removed from the database");
	}

	public void changePropertyState(String address, String newState) {
		// TODO
	}

	public Property getProperty(String address) {
		return Property.getProperty(getFirstObject("address", address, "properties"));
	}

	public void addPropertyToDatabase(Property property) {
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("address", property.getAddress());
		FindIterable<Document> findIter = propertiesCollection.find(searchQuery);
		MongoCursor<Document> resultCursor = findIter.iterator();
		if (resultCursor.hasNext()) { // Meaning the property already exists in the
										// database and should not be added as a duplicate
			System.out.println("A property with the address \"" + property.getAddress() +
					"\" already exists.");
			System.out.println(
					"The property with address \"" + property.getAddress() + "\" was not added to the database.");
			resultCursor.close();
			return;
		}

		resultCursor.close();

		propertiesCollection.insertOne(Property.toDocument(property));
	}

	public void editProperty(EditPropertyView prop) {
		Bson updates = Updates.combine(
				Updates.set("bathrooms", prop.getNumBath()),
				Updates.set("bedrooms", prop.getNumBed()),
				Updates.set("furnished", prop.isFurnished()),
				Updates.set("rent_cost", prop.getPrice()),
				Updates.set("visible_to_renters", !prop.isRented()),
				Updates.set("rented", prop.isRented()));

		propertiesCollection.updateOne(new Document("address", prop.getAddress()), updates,
				new UpdateOptions().upsert(true));
	}

	public Boolean payFee(PayInfoForm paymentForm, Property propertyAppForm) {
		Bson updates = Updates.combine(
				Updates.set("visible_to_renters", true));
		UpdateResult res = propertiesCollection.updateOne(new Document("address", propertyAppForm.getAddress()),
				updates,
				new UpdateOptions().upsert(false));
		if (res.getMatchedCount() > 0) {
			return true;
		} else {
			return false;
		}
	}

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
