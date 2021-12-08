package ensf480.group14.dbcontrol;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import org.bson.Document;

import ensf480.group14.external.Property;

public class LandlordDBControl extends RegisteredRenterDBController {

	LandlordDBControl() {
		super();
	}

	protected void removePropertyFromDatabase(String address) {
		BasicDBObject searchQuery = new BasicDBObject();
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
}
