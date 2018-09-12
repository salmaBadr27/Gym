
package DataBase.PaymentRepository;

import Models.Payment;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class PaymentMySqlRepository extends PaymentRepository {
    
    private Connection connection;
    private Statement statement;
    private ResultSet result;

    public PaymentMySqlRepository(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }


    @Override
    public ArrayList<Payment> getAllPayments() {
         ArrayList allPayment= new ArrayList();
        try {
            String selectQuery = "select * from payment";
            result = statement.executeQuery(selectQuery);
            if (result.next() == false) {
                System.out.print("no records founds");
            } else {
                do {
                    int paymentId = result.getInt("pyment_id");
                    float amount = result.getFloat("amount");
                    int packageID = result.getInt("related_package_id");
                    int paymentID = result.getInt("related_payment_id");
                    int userID = result.getInt("userID");
                    Payment payment = new Payment(paymentId,userID,packageID,paymentID,amount);
                    allPayment.add(payment);
                } while (result.next());
            }
        } catch (SQLException ex) {
            System.out.print("some thing went wrong" + ex);

        }
        return allPayment;
    }

    @Override
    public Payment getPaymentById(int id) {
 try {
            String selectQuery = "select * from payment where payment_id = '"+id+"'";
            result = statement.executeQuery(selectQuery);
            if (result.next() == false) {
                System.out.print("no records founds");
            } else {
                do {
                    int paymentId = result.getInt("pyment_id");
                    float amount = result.getFloat("amount");
                    int packageID = result.getInt("related_package_id");
                    int paymentID = result.getInt("related_payment_id");
                    int userID = result.getInt("userID");
                    Payment returnedpayment = new Payment(paymentId,userID,packageID,paymentID,amount);
                  return returnedpayment;
                } while (result.next());
            }
        } catch (SQLException ex) {
            System.out.print("some thing went wrong" + ex);

        } 
    return null;
    }

    @Override
    public Payment addPayment(Payment newPayment) {
        // which values will be inserted amount bs ? 
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Payment updatePayment(int id, Payment newPaymentInfo) {
        // update amount bs brdo ? 
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Payment removePayment(int id) {
        try {
          Payment removedPayment = getPaymentById(id);
          statement.executeUpdate("Delete from payment where payment_id = '" + id + "'");
          return removedPayment;
        } catch (SQLException ex) {
            System.out.print("some thing went wrong" + ex);
        } 
    return null;
    }

    @Override
    public String getUpdatedValue(boolean condition, String newValue, String columnName) {
           try {
            if (condition) {
                return "'" + columnName + "'='" + newValue + "'";
            }
        } catch (Exception e) {
            System.out.print("error" + e);
        }
        return null;
    }

    }
    

