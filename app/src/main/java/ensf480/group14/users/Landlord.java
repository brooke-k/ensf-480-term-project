/**
 * File: Landlord.java
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

public class Landlord extends User {

	private HashSet<String> ownedAddresses;

	public boolean owns(String address) {
		return ownedAddresses.contains(address);
	}

	public void addAddress(String address) {
		ownedAddresses.add(address);
	}

	public void removeAddress(String address) {
		ownedAddresses.remove(address);
	}
}
