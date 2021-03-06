/**
 * File: Manager.java
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

import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;

import org.bson.Document;
import org.bson.types.ObjectId;

import ensf480.group14.dbcontrol.ManagerDBController;
import ensf480.group14.dbcontrol.Report;

public class Manager extends User {
    private String email;

    private ManagerDBController dbController;

    public Manager() {
        super();
        email = null;
        type = "manager";
        iD = new ObjectId();
        dbController = new ManagerDBController();
    }

    // Constructor
    public Manager(String email, ObjectId iD) {
        super();
        this.email = email;
        this.iD = iD;
        this.type = "manager";
        dbController = new ManagerDBController();
    }

    // Start of
    // getters and setters

    public static Document toDocument(Manager man) {
        Document newDoc = new Document("email", man.getEmail());
        newDoc.append("_id", man.getiD());
        newDoc.append("type", man.type);
        return newDoc;
    }

    public static Manager getManager(Document manDoc) {
        Manager man = new Manager();
        man.email = (String) manDoc.get("email");
        man.type = (String) manDoc.get("type");
        man.iD = (ObjectId) manDoc.get("_id");
        return man;
    }

    public void generateReport(String startDate, String endDate) {
        try {
            Report report = new Report(startDate, endDate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setiD(ObjectId iD) {
        super.setId(iD);
    }

    @Override
    public ObjectId getiD() {
        return super.getId();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean owns(String address) {
        return false;
    }

    @Override
    public void setFirstName(String firstName) {

    }

    @Override
    public void setLastName(String lastName) {

    }

    @Override
    public String getType() {
        return super.getType();
    }

    @Override
    protected void setType(String type) {
        super.setType(type);
    }

    @Override
    public String getFirstName() {
        return "";
    }

    @Override
    public String getLastName() {
        return "";
    }

    @Override
    public void setOwnedAddresses(HashSet<String> address) {
    }

    @Override
    public String getLastLogin() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setLastLogin(String lastLogin) {
        // TODO Auto-generated method stub
        
    }

}
