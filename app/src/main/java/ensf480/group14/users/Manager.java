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

import java.util.Date;

import org.bson.types.ObjectId;

import ensf480.group14.billcontrol.BillingSystem;
import ensf480.group14.dbcontrol.ManagerDBController;

public class Manager extends User {
    private ObjectId iD;
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

    public void generateReport() {
        report = new Report();
        report.Report();
    }

    public ObjectId getiD() {
        return iD;
    }

    public void setiD(ObjectId iD) {
        this.iD = iD;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
