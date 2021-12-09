/**
 * File: RegisteredRenter.java
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

package ensf480.group14.users;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;

import ensf480.group14.dbcontrol.RegisteredRenterDBController;
import ensf480.group14.external.Property;
import ensf480.group14.forms.PreferenceForm;

public class RegisteredRenter extends User {

	String emailAddress;
	RegisteredRenterDBController dbcontroller;
	PreferenceForm prefs;
	ArrayList<Property> lastMatchedProperties;
	ObjectId iD;

	public RegisteredRenter(String emailAddress, ObjectId renterId, String type) {
		this.emailAddress = emailAddress;
		this.iD = renterId;
		this.type = type;
		this.dbcontroller = new RegisteredRenterDBController();
	}

	public RegisteredRenter() {
		emailAddress = null;
		dbcontroller = null;
		prefs = null;
		this.iD = new ObjectId();
		type = "registered_renter";
	}

	public RegisteredRenter(int def) {
		emailAddress = null;
		dbcontroller = null;
		prefs = null;
		this.iD = new ObjectId();
		type = "banana";

	}

	public static RegisteredRenter getRegisteredRenter(Document renterDoc) {
		RegisteredRenter newRenter = new RegisteredRenter();
		newRenter.setEmailAddress(renterDoc.get("email").toString());
		// newRenter.setPrefs(prefs);
		newRenter.setType(renterDoc.get("type").toString());
		newRenter.setDbcontroller(new RegisteredRenterDBController());
		newRenter.setiD((ObjectId) renterDoc.get("_id"));
		return newRenter;

	}

	public static Document toDocument(RegisteredRenter rRenter) {
		Document finalDoc = rRenter.dbcontroller.getFirstObject("email", rRenter.getEmailAddress(), "users");
		return finalDoc;
	}

	public void print() {
		System.out.printf("\n\r PRINTING USER: %s", iD);
		System.out.printf("\n\r email: %s\n\r type: %s\n\r ID: %s\n\r", emailAddress, type, iD);
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public RegisteredRenterDBController getDbcontroller() {
		return dbcontroller;
	}

	public void setDbcontroller(RegisteredRenterDBController dbcontroller) {
		this.dbcontroller = dbcontroller;
	}

	public PreferenceForm getPrefs() {
		return prefs;
	}

	public void setPrefs(PreferenceForm prefs) {
		this.prefs = prefs;
	}

	public ObjectId getiD() {
		return iD;
	}

	public void setiD(ObjectId iD) {
		this.iD = iD;
	}

	public void addPreference(PreferenceForm prefForm) {
		if (this.iD == null) {
			return;
		}

		prefForm.setRenterID(this.iD);
		dbcontroller.addPreferenceFormToDatabase(prefForm);

	}

	public boolean owns(String address){
		return false;
	}

	@Override
	public void setFirstName(String firstName) {
		
	}

	@Override
	public void setLastName(String lastName) {
		
	}

	@Override
	public void setEmail(String emailAddress) {
		
	}

}
