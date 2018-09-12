package Models;

public class Payment {

    private int paymentId;
    private int userId;
    private int relatedPackageId;
    private int relatedPaymentId;
        private float paidAmount;

    public Payment(int paymentId, int userId, int relatedPackageId, int relatedPaymentId, float paidAmount) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.relatedPackageId = relatedPackageId;
        this.relatedPaymentId = relatedPaymentId;
        this.paidAmount = paidAmount;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getUserId() {
        return userId;
    }

    public int getRelatedPackageId() {
        return relatedPackageId;
    }

    public int getRelatedPaymentId() {
        return relatedPaymentId;
    }

    public float getPaidAmount() {
        return paidAmount;
    }

    @Override
    public String toString() {
        return "Payment{" + "paymentId=" + paymentId + ", userId=" + userId + ", relatedPackageId=" + relatedPackageId + ", relatedPaymentId=" + relatedPaymentId + ", paidAmount=" + paidAmount + '}';
    }




    
    
 



}
