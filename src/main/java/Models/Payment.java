package Models;

import java.util.Date;

public class Payment {

    private int paymentId;
    private Date paymentDate;
    private String paymentAmount;
    private String description;

    public Payment(int paymentId, Date paymentDate, String paymentAmount, String description) {
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.description = description;
    }

    
    public int getPaymentId() {
        return paymentId;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Payment{" + "paymentId=" + paymentId + ", paymentDate=" + paymentDate + ", paymentAmount=" + paymentAmount + ", description=" + description + '}';
    }

}
