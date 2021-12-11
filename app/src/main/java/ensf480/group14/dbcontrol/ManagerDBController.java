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

/**
 *  The folder which the class lies in the project. 
 */
package ensf480.group14.dbcontrol;
/**
 * The import statements used in order for the code to work. 
 */
import java.util.ArrayList;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;

import org.bson.Document;
import org.bson.conversions.Bson;

import org.bson.types.ObjectId;
import ensf480.group14.external.Email;
import ensf480.group14.external.Property;
import ensf480.group14.forms.PreferenceForm;
import ensf480.group14.users.Landlord;
import ensf480.group14.users.RegisteredRenter;
import ensf480.group14.users.User;

/**
 * Controller for the user type Managers and builds on the functionalities of the landlord database controller. 
 * It lets the manager to access more than the users which are mentioned in the project document, they can adjust 
 * the period, fees for the user. Also let's them access all users and properties. If this was a hireachy model of 
 * the controllers they have the most access in the program. 
 */
public class ManagerDBController extends LandlordDBControl {
	/**
	 * Default constructor 
	 */
	public ManagerDBController() {
		super();
	}

	/**
	 * This prints the current users in the system and interfaces with the database to reterive them and find it. 
	 * @params: Nothing
	 * @returns: Nothing interfacing with the database in this function. 
	 */
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

	/**
	 * This prints the current emails in the system and interfaces with the database to reterive them and find it. 
	 * @params: Nothing
	 * @returns: Nothing interfacing with the database in this function. 
	 */
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

	/**
	 * This prints the current propeties in the system and interfaces with the database to reterive them and find it. 
	 * Used later in the code when the Manager wants to access all of the properties. 
	 * @params: Nothing
	 * @returns: Nothing interfacing with the database in this function. 
	 */
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

	/**
	 * This resets the users collection and email collection. 
	 * @params: Nothing
	 * @returns: Nothing interfacing with the database in this function. 
	 */
	private void resetUsers() {
		System.out.println();
		System.out.println("Removing all users from the database");
		Document all = new Document();
		usersCollection.deleteMany(all);
		emailCollection.deleteMany(all);
		System.out.println();
		System.out.println("Removed all users from the database");

	}

	/**
	 * This removes the user from the database based on the email which is passed in since the Manager has the ability 
	 * to kick out the user from using the system. 
	 * @params: Takes in the email of the user. 
	 * @returns: Nothing interfacing with the database in this function removes the user from the collection. 
	 */
	private void removeUserFromDatabase(String email) {
		BasicDBObject searchQuery = new BasicDBObject();
		System.out.println("Removing user with the email address \"" + email + "\" from database");
		usersCollection.deleteOne(Filters.eq("email", email));
		System.out.println("User with the email address \"" + email + "\" has been removed from the database");
	}

	/**
	 * This prints the preferences which are being stored in the database for the user. 
	 * @params: Nothing 
	 * @returns: Nothing interfacing with the database only. 
	 */
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

	/**
	 * This resets the propeties collection. 
	 * @params: Nothing
	 * @returns: Nothing interfacing with the database in this function. 
	 */
	private void resetProperties() {
		System.out.println();
		System.out.println("Removing all properties from the database");
		Document all = new Document();
		propertiesCollection.deleteMany(all);
		System.out.println();
		System.out.println("Removed all properties from the database");

	}

	
	/**
	 * Retrieves the users from the database which are in the user collection, checks for the types 
	 * and based on that will create a new user based on their type. 
	 * @params: Nothing
	 * @returns: Returns the arraylist of the users in the database. 
	 */
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

	/**
	 * Retrieves the emails from the database which are in the email collection, checks if there is an email 
	 * or not in the database. 
	 * @params: Nothing
	 * @returns: Returns the arraylist of the emails in the database. 
	 */
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

	/**
	 * Retrieves the properties from the database which are in the properties collection, checks if there is a property 
	 * or not in the database. 
	 * @params: Nothing
	 * @returns: Returns the arraylist of the properties in the database. 
	 */
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

	/**
	 * Retrieves the preferences from the database which are in the properties collection, checks if there is a preference 
	 * or not in the database. 
	 * @params: Nothing
	 * @returns: Returns the arraylist of the preferences in the database. 
	 */
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
	
	/**
	 * Retrieves the properties from the database which are in the preference collection, checks if there is a property like this 
	 * or not, based on the user liking which they fill out in the preference form. 
	 * @params: Takes in two parameters which are startDate and endDate to get in a range. 
	 * @returns: Returns the integer value of the number of properties in the database within a start date and end date. 
	 */
	public static int getNumPropertiesListedWithin(String startDate, String endDate) {
		FindIterable<Document> docIter = logCollection.find(new Document("type", "listing"));
		MongoCursor<Document> iter = docIter.iterator();
		if (!iter.hasNext()) {
			return 0;
		}
		String startYear = startDate.split("-")[0];
		String startMonth = startDate.split("-")[1];
		String startDay = startDate.split("-")[2];
		String endYear = endDate.split("-")[0];
		String endMonth = endDate.split("-")[1];
		String endDay = endDate.split("-")[2];

		int num = 0;
		while (iter.hasNext()) {
			Document res = iter.next();
			String currYear = res.get("date").toString().split("-")[0];
            String currMonth = res.get("date").toString().split("-")[1];
            String currDay = res.get("date").toString().split("-")[2];

			Date start = new Date(Integer.parseInt(startYear)-1900, Integer.parseInt(startMonth)-1, Integer.parseInt(startDay));
			Date end = new Date(Integer.parseInt(endYear)-1900, Integer.parseInt(endMonth)-1, Integer.parseInt(endDay));
			Date curr = new Date(Integer.parseInt(currYear)-1900, Integer.parseInt(currMonth)-1, Integer.parseInt(currDay));

			if(((end.getTime()/86400000) - (curr.getTime()/86400000)) > 0 && ((curr.getTime()/86400000) - (start.getTime()/86400000)) > 0){
				num++;
			}
		}

		return num;
	}

	/**
	 * Retrieves the properties from the database which are in the propeties collection, checks if there is a property like this 
	 * or not, based on the rented within a date. 
	 * @params: Takes in two parameters which are startDate and endDate to get in a range. 
	 * @returns: Returns the arraylist of the propeties in the database within a start date and end date. 
	 */
	public static ArrayList<Property> getPropertiesRentedWithin(String startDate, String endDate) {
		FindIterable<Document> docIter = logCollection.find(new Document("type", "rental"));
		MongoCursor<Document> iter = docIter.iterator();
		if (!iter.hasNext()) {
			return null;
		}
		String startYear = startDate.split("-")[0];
		String startMonth = startDate.split("-")[1];
		String startDay = startDate.split("-")[2];
		String endYear = endDate.split("-")[0];
		String endMonth = endDate.split("-")[1];
		String endDay = endDate.split("-")[2];
		ArrayList<Property> arr = new ArrayList<>(0);
		while (iter.hasNext()) {
			Document res = iter.next();
			String currYear = res.get("date").toString().split("-")[0];
            String currMonth = res.get("date").toString().split("-")[1];
            String currDay = res.get("date").toString().split("-")[2];

			Date start = new Date(Integer.parseInt(startYear)-1900, Integer.parseInt(startMonth)-1, Integer.parseInt(startDay));
			Date end = new Date(Integer.parseInt(endYear)-1900, Integer.parseInt(endMonth)-1, Integer.parseInt(endDay));
			Date curr = new Date(Integer.parseInt(currYear)-1900, Integer.parseInt(currMonth)-1, Integer.parseInt(currDay));

			if(((end.getTime()/86400000) - (curr.getTime()/86400000)) > 0 && ((curr.getTime()/86400000) - (start.getTime()/86400000)) > 0){
				arr.add(new Property(res.get("landlord_name").toString(), (ObjectId)res.get("_id"), res.get("address").toString()));
			}
		}

		return arr;
	}
	/**
	 * Retrieves the properties from the database which are in the propeties collection, checks if there is a property like this 
	 * or not, based on the rented within a date and checks their rental state as mentioned 
	 * in the document this is just for the active properties. 
	 * @params: Takes in nothing.
	 * @returns: Returns the arraylist of the propeties with rental state active which means to us that they are listed and payed. 
	 */
	public static ArrayList<Property> getActiveProperties() {
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
	 * Retrieves the current fee from the database which are in the fee collection.
	 * @params: Takes in nothing.
	 * @returns: Returns the fee amount which is currently in the database. 
	 */
	public static double getCurrentFee() {
		FindIterable<Document> docIter = feeCollection.find();
		MongoCursor<Document> iter = docIter.iterator();
		double val = (double) iter.next().get("fee");
		return val;
	}

	/**
	 * Sets the current fee into the database which will be updated in the fee colleciton. 
	 * @params: Takes in changedFee which the manager changes. 
	 * @returns: Nothing just sets in the database. 
	 */
	public void setNewFee(Double changedFee) {
		Bson updates = Updates.combine(
				Updates.set("fee", changedFee));
		feeCollection.updateOne(new Document(), updates, new UpdateOptions().upsert(true));
	}

	// /**
	//  * For testing, to clear the terminal of previous information.
	//  * Not being used in the code just for testing the functionalities in the class. 
	//  */
	// protected static void clearTerminal() {
	// 	System.out.println("\u001B[1;1H\u001B[2J");
	// }

	// public static void main(String args[]) {
	// 	ManagerDBController.clearTerminal();
	// 	ManagerDBController db = new ManagerDBController();
	// 	// db.printEmail();
	// 	RegisteredRenter testRenter;

	// 	db.resetProperties();

	// 	Property testProperty = new Property();
	// 	testProperty.setAddress("Test address 73");
	// 	testProperty.setCityQuad("EW");
	// 	// testProperty.setDateLastListed(Calendar());
	// 	testProperty.setFurnished(true);
	// 	// testProperty.setDateLastListed("01/01/00");
	// 	testProperty.setLandlordEmail("Return_To_Sender");
	// 	testProperty.setNumBathrooms(804595.0);
	// 	testProperty.setNumBedrooms(1);
	// 	testProperty.setRentCost(2.0);
	// 	testProperty.setRentalState("Haunted");
	// 	testProperty.setLandlordName("Mr. Aristotle Berkinghamshire");
	// 	// testProperty.setLandlordID("LMAO");
	// 	db.addPropertyToDatabase(testProperty);

	// 	// db.printProperties();

	// 	for (Property p : db.getAllProperties()) {
	// 		p.print();
	// 	}

	// 	// DatabaseController dbc = new DatabaseController();

	// }
}
