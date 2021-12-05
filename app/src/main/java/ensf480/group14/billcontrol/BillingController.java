package ensf480.group14.billcontrol;

import java.util.ArrayList;

public class BillingController {
    public ArrayList<String> personDetailsPaid;

    //Constructor 
    public BillingController(ArrayList personDetailsPaid)
	{
		personDetailsPaid =  new ArrayList<String>();
	}

    public ArrayList<String> getPersonDetailsPaid() {
        return this.personDetailsPaid;
    }

    public void setPersonDetailsPaid(ArrayList<String> personDetailsPaid) {
        this.personDetailsPaid = personDetailsPaid;
    }

    public void createPaidList(String person) {
        if(!personDetailsPaid.contains(person)) {
            personDetailsPaid.add(person);
        }
    }

    public void updatePaidList(String person, String newPerson) {
        int index = personDetailsPaid.indexOf(person);

        personDetailsPaid.set(index, newPerson);
    }

}
