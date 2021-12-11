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

import org.bson.Document;
import org.bson.types.ObjectId;

import ensf480.group14.dbcontrol.LandlordDBControl;

public class Landlord extends User {
	private String firstName;
	private String lastName;
	private String email;

	private LandlordDBControl dbcontrol;

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

	public Landlord() {
		this.type = "landlord";
	}

	public Landlord(Document next) {
		setEmail(next.get("email").toString());
		setType("landlord");
		setFirstName(next.get("first_name").toString());
		setLastName(next.get("last_name").toString());
	}

	public static Landlord getLandlord(Document lldoc) {
		Landlord ll = new Landlord();
		ll.setEmail(lldoc.get("email").toString());
		ll.setFirstName(lldoc.get("first_name").toString());
		ll.setLastName(lldoc.get("last_name").toString());
		ll.ownedAddresses = LandlordDBControl.getLandlordsAddresses((ObjectId) lldoc.get("_id"));
		ll.setType(lldoc.get("type").toString());
		ll.setiD((ObjectId) lldoc.get("_id"));
		return ll;
	}

	public static Document toDocument(Landlord landlord) {
		Document doc = new Document("_id", landlord.getiD());
		doc.append("first_name", landlord.getFirstName());
		doc.append("last_name", landlord.getLastName());
		doc.append("owned_addresses", landlord.getOwnedAddresses());
		doc.append("type", landlord.getType());
		doc.append("email", landlord.getEmail());
		return doc;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void setiD(ObjectId iD) {
		super.setId(iD);
	}

	@Override
	public ObjectId getiD() {
		return super.getId();
	}

	public HashSet<String> getOwnedAddresses() {
		return ownedAddresses;
	}

	public void setOwnedAddresses(HashSet<String> ownedAddresses) {
		this.ownedAddresses = ownedAddresses;
	}

	public void addOwnedAddress(String newAddress) {
		ownedAddresses.add(newAddress);
	}

	public void removeOwnedAddress(String addressToRemove) {
		ownedAddresses.remove(addressToRemove);
	}
}
