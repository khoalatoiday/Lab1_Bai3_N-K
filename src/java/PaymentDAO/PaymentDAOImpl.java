/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaymentDAO;

import Model.Payment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class PaymentDAOImpl implements PaymentDAO{

    @Override
    public ArrayList<Payment> selectAllPayment() {
        ArrayList < Payment > payments = new ArrayList < > ();
        String SELECT_ALL_PAYMENTS = "SELECT * FROM payment";
        
         try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/books", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PAYMENTS);
             ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
               int id = rs.getInt("id");
               String type = rs.getString("type");
               int discount = rs.getInt("discount");
               
               Payment payment = new Payment();
               payment.setId(id);
               payment.setType(type);
               payment.setDiscount(discount);
               
               payments.add(payment);
            }
          
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return payments;
    }

    private void printSQLException(SQLException ex) {
       for (Throwable eThrowable: ex) {
            if (eThrowable instanceof SQLException) {
                eThrowable.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) ex).getSQLState());
                System.err.println("Error Code: " + ((SQLException) ex).getErrorCode());
                System.err.println("Message: " + ex.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    @Override
    public Payment selectPaymentById(int id) {
        Payment payment = new Payment();
       String SELECT_PAYMENTBYID_SQL = "SELECT * FROM payment WHERE id = ?";
        
         try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/books", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAYMENTBYID_SQL);
            preparedStatement.setInt(1, id);
             ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
               
               String type = rs.getString("type");
               int discount = rs.getInt("discount");
              
               
              
               payment.setId(id);
               payment.setType(type);
               payment.setDiscount(discount);
               
               
            }
          
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            printSQLException(ex);
        }
         return payment;
    }
    
}
