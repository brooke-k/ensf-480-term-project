package ensf480.group14.billcontrol;

import java.util.Date;

public class BillingSystem {
    private double feeAmount;
    private Date paymentDate;
    private String paymentType;
    private Landlord landlord;

    // Constructor
    public BillingSystem(double feeAmount, Landlord landlord, Date paymentDate, String paymentType) {
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

    public Date getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

}
