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

public abstract class User {
	protected String type;

	protected void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	User() {
		type = "default";
	}
};
