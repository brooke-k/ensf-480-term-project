/**
 * File: ManagerDBController.java
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

import java.util.ArrayList;
import java.util.Calendar;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;

import org.bson.Document;
import org.bson.conversions.Bson;

import ensf480.group14.external.Email;
import ensf480.group14.external.Property;
import ensf480.group14.forms.PreferenceForm;
import ensf480.group14.users.Landlord;
import ensf480.group14.users.RegisteredRenter;
import ensf480.group14.users.User;

public class ManagerDBController extends LandlordDBControl {
	public ManagerDBController() {
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
		FindIterable<Document> docIter = usersCollection.find();
		MongoCursor<Document> iter = docIter.iterator();
		if (!iter.hasNext()) {
			return null;
		}
		ArrayList<User> arr = new ArrayList<>(0);

		while (iter.hasNext()) {
			Document us = iter.next();
			if(us.get("type").equals("landlord")){
				arr.add(new Landlord(us));
			}else if (us.get("type").equals("registered_renter")){
				arr.add(new RegisteredRenter(us));
			}
		}

		return arr;
	}

	public ArrayList<Email> getAllEmails() {
		FindIterable<Document> docIter = emailCollection.find();
		MongoCursor<Document> iter = docIter.iterator();
		if (!iter.hasNext()) {
			return null;
		}
		ArrayList<Email> arr = new ArrayList<>(0);

		while (iter.hasNext()) {
			arr.add(Email.getEmail(iter.next()));
		}

		return arr;
	}

	public ArrayList<Property> getAllProperties() {
		FindIterable<Document> docIter = propertiesCollection.find();
		MongoCursor<Document> iter = docIter.iterator();
		if (!iter.hasNext()) {
			return null;
		}
		ArrayList<Property> arr = new ArrayList<>(0);

		while (iter.hasNext()) {
			arr.add(Property.getProperty(iter.next()));
		}

		return arr;
	}

	public ArrayList<PreferenceForm> getAllPreferences() {
		FindIterable<Document> docIter = preferenceCollection.find();
		MongoCursor<Document> iter = docIter.iterator();
		if (!iter.hasNext()) {
			return null;
		}
		ArrayList<PreferenceForm> arr = new ArrayList<>(0);
		while (iter.hasNext()) {
			arr.add(PreferenceForm.getPreferenceForm(iter.next()));
		}

		return arr;
	}

	public ArrayList<Property> getPropertiesListedWithin(String startDate, String endDate) {
		FindIterable<Document> docIter = preferenceCollection.find();
		MongoCursor<Document> iter = docIter.iterator();
		if (!iter.hasNext()) {
			return null;
		}
		ArrayList<Property> arr = new ArrayList<>(0);
		Property temp;
		while (iter.hasNext()) {
			temp = Property.getProperty(iter.next());
			if (temp.getDateLastListed().compareTo(startDate) >= 0
					&& temp.getDateLastListed().compareTo(endDate) <= 0) {
				arr.add(temp);
			}
		}
		return arr;
	}

	public ArrayList<Property> getPropertiesRentedWithin(String startDate, String endDate) {
		FindIterable<Document> docIter = propertiesCollection.find();
		MongoCursor<Document> iter = docIter.iterator();
		if (!iter.hasNext()) {
			return null;
		}
		ArrayList<Property> arr = new ArrayList<>(0);
		Property temp;
		while (iter.hasNext()) {
			temp = Property.getProperty(iter.next());
			if (temp.getDateRented().compareTo(startDate) >= 0
					&& temp.getDateRented().compareTo(endDate) <= 0) {
				arr.add(temp);
			}
		}
		return arr;
	}

	public ArrayList<Property> getActiveProperties() {
		FindIterable<Document> docIter = propertiesCollection.find();
		MongoCursor<Document> iter = docIter.iterator();
		if (!iter.hasNext()) {
			return null;
		}
		ArrayList<Property> arr = new ArrayList<>(0);
		Property temp;
		while (iter.hasNext()) {
			temp = Property.getProperty(iter.next());
			if (temp.getRentalState().equals("active")) {
				arr.add(temp);
			}
		}
		return arr;
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
		// testProperty.setDateLastListed(Calendar());
		testProperty.setFurnished(true);
		// testProperty.setDateLastListed("01/01/00");
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

	public double getCurrentFee() {
		FindIterable<Document> docIter = feeCollection.find();
		MongoCursor<Document> iter = docIter.iterator();
		double val = (double) iter.next().get("fee");
		return val;
	}

	public void setNewFee(Double changedFee) {
		Bson updates = Updates.combine(
				Updates.set("fee", changedFee));
		feeCollection.updateOne(new Document(), updates, new UpdateOptions().upsert(true));
	}

	public double getCurrentPeriod() {
		FindIterable<Document> docIter = feeCollection.find();
		MongoCursor<Document> iter = docIter.iterator();
		double val = (double) iter.next().get("period");
		return val;
	}

	public void setCurrentPeriod(Double changedPeriod) {
		Bson updates = Updates.combine(
				Updates.set("period", changedPeriod));
		feeCollection.updateOne(new Document(), updates, new UpdateOptions().upsert(true));
	}
}
