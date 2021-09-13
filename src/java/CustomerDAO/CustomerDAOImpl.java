/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerDAO;

import Model.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class CustomerDAOImpl implements  CustomerDAO{

    @Override
    public Customer sellectCustomerById(int id) {
        String SELLECT_CUSTOMER_BY_ID = "SELECT * from customer WHERE id = ?";
        Customer customer = new Customer();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
           Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(SELLECT_CUSTOMER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                String name = rs.getString("name");
                String gender = rs.getString("gender");
          
                customer = new Customer(id,name,gender);
            }
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) { 
            printSQLException(ex);
        }
        
        
        return customer;
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
    
}
