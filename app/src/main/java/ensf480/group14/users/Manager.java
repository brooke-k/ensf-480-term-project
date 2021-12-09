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
    private String userName;
    private ObjectId iD;
    private String email;

    private ManagerDBController dbController;

    public Manager() {
        super();
        userName = null;
        email = null;
        type = "manager";
    }

    // Constructor
    public Manager(String userName, String ManagerID, String gender, Date birthday, String status) {
        super();
        this.userName = userName;
        this.ManagerID = ManagerID;
        this.gender = gender;
        this.birthday = birthday;
        this.status = status;
    }
    // Start of
    // getters and setters

    public String getFullName() {
        return this.userName;
    }

    public void setFullName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void generateReport() {
        report = new Report();
        report.Report();
    }

    public void changeFee() {
        double feeIncrement = 50.10;
        double newFee = getFeeAmount();
        newFee += feeIncrement;
        setFeeAmount(newFee);
    }

    public void changeDuration() {
      String newDuration "Extra 10 Days";
      setPeriodDuration(newDuration);
      }

    public void changeStatus() {
        String newStatus = "Listed";
        setStatus(newStatus);
    }

}
