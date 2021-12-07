package ensf480.group14.users;

import java.util.ArrayList;

import javax.imageio.spi.RegisterableService;
import javax.management.relation.RelationServiceNotRegisteredException;

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
	}

	public RegisteredRenter() {
		emailAddress = null;
		iD = null;
		dbcontroller = null;
		prefs = null;

	}

	public static getRegisteredRenter(Document renterDoc){
		RegisteredRenter newRenter = new RegisteredRenter();

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

	public ArrayList<Property> getLastMatchedProperties() {
		return lastMatchedProperties;
	}

	public void setLastMatchedProperties(ArrayList<Property> lastMatchedProperties) {
		this.lastMatchedProperties = lastMatchedProperties;
	}

	public String getiD() {
		return iD;
	}

	public void setiD(String iD) {
		this.iD = iD;
	}

	// public static RegisteredRenter toRegisteredRenter(Document renterDoc) {
	// RegisteredRenter renter = new RegisteredRenter();

	// }

}
