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
