package ensf480.group14.users;

import java.util.ArrayList;

import org.bson.Document;

import ensf480.group14.dbcontrol.DatabaseController;
import ensf480.group14.external.Property;
import ensf480.group14.forms.PreferenceForm;

public class RegisteredRenter extends User {

	String emailAddress;
	DatabaseController dbcontroller;
	PreferenceForm prefs;
	ArrayList<Property> lastMatchedProperties;
	String iD;

	public RegisteredRenter(String emailAddress, String renterId, String type) {
		this.emailAddress = emailAddress;
		this.iD = renterId;
		this.type = type;
		this.dbcontroller = new DatabaseController();
	}

	private RegisteredRenter() {
		emailAddress = null;
		iD = null;
		dbcontroller = null;
		prefs = null;

	}

	public static RegisteredRenter getRegisteredRenter(Document renterDoc) {
		RegisteredRenter newRenter = new RegisteredRenter();
		newRenter.setEmailAddress(renterDoc.get("email").toString());
		// newRenter.setPrefs(prefs);
		newRenter.setType(renterDoc.get("type").toString());
		newRenter.setDbcontroller(new DatabaseController());
		newRenter.setiD(renterDoc.get("_id").toString());
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

	public DatabaseController getDbcontroller() {
		return dbcontroller;
	}

	public void setDbcontroller(DatabaseController dbcontroller) {
		this.dbcontroller = dbcontroller;
	}

	public PreferenceForm getPrefs() {
		return prefs;
	}

	public void setPrefs(PreferenceForm prefs) {
		this.prefs = prefs;
	}

	// public ArrayList<Property> getLastMatchedProperties() {
	// return lastMatchedProperties;
	// }

	// public void setLastMatchedProperties(ArrayList<Property>
	// lastMatchedProperties) {
	// this.lastMatchedProperties = lastMatchedProperties;
	// }

	public String getiD() {
		return iD;
	}

	public void setiD(String iD) {
		this.iD = iD;
	}

}
