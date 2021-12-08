package ensf480.group14.dbcontrol;

import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import org.bson.Document;

import ensf480.group14.external.Email;
import ensf480.group14.external.Property;
import ensf480.group14.users.RegisteredRenter;
import ensf480.group14.users.User;

public class ManagerDBController extends LandlordDBControl {
	ManagerDBController() {
		super();
	}

	private void printUsers() {
		System.out.println();
		System.out.println("Current Users:");
		FindIterable<Document> docIterator = usersCollection.find();
		MongoCursor<Document> collectionIter = docIterator.iterator();
		while (collectionIter.hasNext()) {
			System.out.println(collectionIter.next());
		}
		System.out.println();

		collectionIter.close();

	}

	private void printEmail() {
		System.out.println();
		System.out.println("Current Emails:");
		FindIterable<Document> docIterator = emailCollection.find();
		MongoCursor<Document> collectionIter = docIterator.iterator();
		while (collectionIter.hasNext()) {
			System.out.println(collectionIter.next());
		}
		System.out.println();

		collectionIter.close();

	}

	protected void printProperties() {
		System.out.println();
		System.out.println("Current Properties:");
		FindIterable<Document> docIterator = propertiesCollection.find();
		MongoCursor<Document> collectionIter = docIterator.iterator();
		while (collectionIter.hasNext()) {
			System.out.println(collectionIter.next());
		}
		System.out.println();
		collectionIter.close();

	}

	private void resetUsers() {
		System.out.println();
		System.out.println("Removing all users from the database");
		Document all = new Document();
		usersCollection.deleteMany(all);
		emailCollection.deleteMany(all);
		System.out.println();
		System.out.println("Removed all users from the database");

	}

	private void removeUserFromDatabase(String email) {
		BasicDBObject searchQuery = new BasicDBObject();
		System.out.println("Removing user with the email address \"" + email + "\" from database");
		usersCollection.deleteOne(Filters.eq("email", email));
		System.out.println("User with the email address \"" + email + "\" has been removed from the database");
	}

	private void printPreferences() {
		System.out.println();
		System.out.println("Currently Store Preferences (All users):");
		FindIterable<Document> docIterator = preferenceCollection.find();
		MongoCursor<Document> collectionIter = docIterator.iterator();
		while (collectionIter.hasNext()) {
			System.out.println(collectionIter.next());
		}
		System.out.println();

		collectionIter.close();

	}

	private void resetProperties() {
		System.out.println();
		System.out.println("Removing all properties from the database");
		Document all = new Document();
		propertiesCollection.deleteMany(all);
		System.out.println();
		System.out.println("Removed all properties from the database");

	}

	public ArrayList<User> getAllUsers() {
		return null;
	}

	public ArrayList<Email> getAllEmails() {
		return null;
	}

	/**
	 * For testing, to clear the terminal of previous information.
	 */
	protected static void clearTerminal() {
		System.out.println("\u001B[1;1H\u001B[2J");
	}

	public static void main(String args[]) {
		ManagerDBController.clearTerminal();
		ManagerDBController db = new ManagerDBController();
		// db.printEmail();
		RegisteredRenter testRenter;

		db.resetProperties();

		Property testProperty = new Property();
		testProperty.setAddress("Test address 73");
		testProperty.setCityQuad("EW");
		testProperty.setDateLastListed("02/02/13");
		testProperty.setFurnished(true);
		testProperty.setDateLastListed("01/01/00");
		testProperty.setLandlordEmail("Return_To_Sender");
		testProperty.setNumBathrooms(804595.0);
		testProperty.setNumBedrooms(1);
		testProperty.setRentCost(2.0);
		testProperty.setRentalState("Haunted");
		testProperty.setLandlordName("Mr. Aristotle Berkinghamshire");
		// testProperty.setLandlordID("LMAO");
		db.addPropertyToDatabase(testProperty);

		// db.printProperties();

		for (Property p : db.getAllProperties()) {
			p.print();
		}

		// DatabaseController dbc = new DatabaseController();

	}
}
