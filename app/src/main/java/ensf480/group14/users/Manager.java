package ensf480.group14.users;

import java.util.Date;

import ensf480.group14.billcontrol.BillingSystem;

public class Manager extends BillingSystem {

    public Manager(double feeAmount, Landlord landlord, Date paymentDate, String paymentType) {
        super(feeAmount, landlord, paymentDate, paymentType);
        // TODO Auto-generated constructor stub
    }

    /*
     * private String fullName;
     * private String ManagerID;
     * private String gender;
     * private Date birthday;
     * private String status;
     * 
     * // Constructor
     * public Manager(String fullName, String ManagerID, String gender, Date
     * birthday, String status) {
     * super();
     * this.fullName = fullName;
     * this.ManagerID = ManagerID;
     * this.gender = gender;
     * this.birthday = birthday;
     * this.status = status;
     * }
     * 
     * // Start of
     * // getters and setters
     * 
     * public String getFullName() {
     * return this.fullName;
     * }
     * 
     * public void setFullName(String fullName) {
     * this.fullName = fullName;
     * }
     * 
     * public String getManagerID() {
     * return this.ManagerID;
     * }
     * 
     * public void setManagerID(String ManagerID) {
     * this.ManagerID = ManagerID;
     * }
     * 
     * public String getGender() {
     * return this.gender;
     * }
     * 
     * public void setGender(String gender) {
     * this.gender = gender;
     * }
     * 
     * public Date getBirthday() {
     * return this.birthday;
     * }
     * 
     * public void setBirthday(Date birthday) {
     * this.birthday = birthday;
     * }
     * 
     * public String getStatus() {
     * return this.status;
     * }
     * 
     * public void setStatus(String status) {
     * this.status = status;
     * }
     * 
     * // Methods
     * public String retrieveData(String query) {
     * // Need to figure out how to do this
     * // Use Database classes somehow not quite sure how though
     * }
     * 
     * public void generateReport() {
     * report = new Report();
     * report.Report();
     * }
     * 
     * public void changeFee() {
     * double feeIncrement = 50.10;
     * double newFee = getFeeAmount();
     * newFee += feeIncrement;
     * setFeeAmount(newFee);
     * }
     * 
     * public void changeDuration() {
     * String newDuration "Extra 10 Days";
     * setPeriodDuration(newDuration);
     * }
     * 
     * public void changeStatus() {
     * String newStatus = "Listed";
     * setStatus(newStatus);
     * }
     */
}
