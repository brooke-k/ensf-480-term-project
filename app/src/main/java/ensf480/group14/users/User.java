/**
 * File: User.java
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

import java.util.HashSet;

import org.bson.types.ObjectId;

public abstract class User {
	protected String type;

	protected ObjectId iD;

	protected void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	User() {
		type = "banana : )";
	}

	public void setId(ObjectId newId) {
		iD = newId;
	}

	public ObjectId getId() {
		return iD;
	}

	abstract public boolean owns(String address);

	abstract public void setEmail(String emailAddress);

	abstract public String getEmail();

	abstract public void setiD(ObjectId iD);

	abstract public void setFirstName(String firstName);

	abstract public void setLastName(String lastName);

	abstract public String getFirstName();

	abstract public String getLastName();

	abstract public ObjectId getiD();
	
	abstract public void setOwnedAddresses(HashSet<String> address);

};
