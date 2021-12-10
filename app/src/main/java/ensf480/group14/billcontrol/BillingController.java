/**
 * File: BillingController.java
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

package ensf480.group14.billcontrol;

import java.util.ArrayList;


public class BillingController {
    public ArrayList<String> personDetailsPaid;

    public BillingController(ArrayList<String> personDetailsPaid) {
        personDetailsPaid = new ArrayList<String>();
    }

    public ArrayList<String> getPersonDetailsPaid() {
        return this.personDetailsPaid;
    }

    public void setPersonDetailsPaid(ArrayList<String> personDetailsPaid) {
        this.personDetailsPaid = personDetailsPaid;
    }

    public void createPaidList(String person) {
        if (!personDetailsPaid.contains(person)) {
            personDetailsPaid.add(person);
        }
    }

    public void updatePaidList(String person, String newPerson) {
        int index = personDetailsPaid.indexOf(person);

        personDetailsPaid.set(index, newPerson);
    }

}
