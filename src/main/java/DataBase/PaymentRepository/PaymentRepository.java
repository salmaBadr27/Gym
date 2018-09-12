package DataBase.PaymentRepository;

import Models.Payment;
import java.util.ArrayList;

abstract public class PaymentRepository {

    abstract public ArrayList<Payment> getAllPayments();

    abstract public Payment getPaymentById(int id);

    abstract public Payment addPayment(Payment newPayment);

    abstract public Payment updatePayment(int id, Payment newPaymentInfo);

    abstract public Payment removePayment(int id);

    abstract public String getUpdatedValue(boolean condition, String newValue, String columnName);

}
