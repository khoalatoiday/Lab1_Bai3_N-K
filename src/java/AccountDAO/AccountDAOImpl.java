/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccountDAO;

import AccountDAO.AccountDAO;
import Model.Account;
import Model.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class AccountDAOImpl implements  AccountDAO{

    @Override
    public boolean checkLogin(Account account)  {
        boolean status = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            try (Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/books", "root", "");
                    
                    
                    PreparedStatement preparedStatement = connection
                            .prepareStatement("select * from account where username = ? and password = ? ")) {
                preparedStatement.setString(1, account.getUsername());
                preparedStatement.setString(2, account.getPassword());
                ResultSet rs = preparedStatement.executeQuery();
                status = rs.next();
                account.setId(rs.getInt("id"));
                
            } catch (SQLException e) {
                printSQLException(e);
            }
            return status;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
        return status;
    }

    @Override
    public void register(Customer customer) {
        
        
         String INSERT_CUSTOMER_SQL = "INSERT INTO customer" +
                    "  (id,name, gender) VALUES " +
                    " (?, ?, ?);";   
        
        try {
             
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/books", "root", "");
                    
                    PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_SQL);
                preparedStatement.setInt(1, customer.getId());
                preparedStatement.setString(2, customer.getName());
                preparedStatement.setString(3, customer.getGender());
                
                System.out.println(preparedStatement);
                // Step 3: Execute the query or update query
                preparedStatement.executeUpdate();
                
          
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        
        
        String INSERT_ACCOUNT_SQL = "INSERT INTO account" + " (customerId, username, password) VALUES " + "(?, ?, ?)";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT_SQL);
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getAccount().getUsername());
            preparedStatement.setString(3, customer.getAccount().getPassword());
            
            System.out.println(preparedStatement);
            
            preparedStatement.executeUpdate();
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        
        
        String INSERT_ADDRESS_SQL = "INSERT INTO address" + "(customerId, num, city, district, street) VALUES " + "(?, ?, ?, ?, ?)";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "");
            PreparedStatement preparedStatement = connection.prepareCall(INSERT_ADDRESS_SQL);
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setInt(2, customer.getAddress().getNum());
            preparedStatement.setString(3, customer.getAddress().getCity());
            preparedStatement.setString(4, customer.getAddress().getDistrict());
            preparedStatement.setString(5, customer.getAddress().getStreet());
            
             System.out.println(preparedStatement);
            
            preparedStatement.executeUpdate();
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            printSQLException(ex);
        }
       
        }

    @Override
    public ArrayList<Account> viewAllAccount() {
        ArrayList < Account > accounts = new ArrayList < > ();
        String SELECT_ALL_ACCOUNTS = "SELECT * FROM account";
        
         try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/books", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ACCOUNTS);
             ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                
                accounts.add(new Account(id, username, password));
            }
          
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return accounts;
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
    public int sellectCustomerIdById(String username) {
        String SELLECT_CUSTOMERID_BY_ID = "SELECT customerId from account WHERE username = ?";
        int customerId = -1;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(SELLECT_CUSTOMERID_BY_ID);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                customerId = rs.getInt("customerId");
            }
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return customerId;
    }
    
    /*
        public int sellectCustomerIdById2(int id) {
        String SELLECT_CUSTOMERID_BY_ID = "SELECT customerId from account WHERE id = ?";
        int customerId = -1;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(SELLECT_CUSTOMERID_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                customerId = rs.getInt("customerId");
            }
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return customerId;
    }
*/
}
