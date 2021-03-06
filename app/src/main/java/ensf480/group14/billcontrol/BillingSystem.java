/**
 * File: BillingSystem.java
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

import java.util.Calendar;

import ensf480.group14.users.Landlord;

public class BillingSystem {
    private double feeAmount;
    private Calendar paymentDate;
    private String paymentType;
    private Landlord landlord;

    // Constructor
    public BillingSystem(double feeAmount, Landlord landlord, Calendar paymentDate, String paymentType) {
        this.feeAmount = feeAmount;
        this.landlord = landlord;
        this.paymentDate = paymentDate;
        this.paymentType = paymentType;
    }

    // Start of getters and setters
    public double getFeeAmount() {
        return this.feeAmount;
    }

    public void setFeeAmount(double feeAmount) {
        this.feeAmount = feeAmount;
    }

    public Calendar getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(Calendar paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Landlord getLandlord() {
        return this.landlord;
    }

    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }
}
