package ensf480.group14.users;

import java.util.ArrayList;

import ensf480.group14.dbcontrol.DatabaseController;
import ensf480.group14.external.Property;
import ensf480.group14.forms.PreferenceForm;

public class RegisteredRenter {

	String emailAddress;
	Integer renterId;
	DatabaseController dbcontroller;
	PreferenceForm prefs;
	ArrayList<Property> lastMatchedProperties;

	public RegisteredRenter(String emailAddress, Integer renterId, DatabaseController dbcontroller) {
		this.emailAddress = emailAddress;
		this.renterId = renterId;
		this.dbcontroller = dbcontroller;
	}

}
