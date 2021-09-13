/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardDAO;

import Model.Cart;
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
public class CartDAOImpl implements CartDAO{

    @Override
    public void addToCart(Customer customer, float totalPrice) {
        String INSERT_CART_SQL = "INSERT INTO cart (customerId, TotalPrice, Status) VALUES (?,?,0) ";
         try {
             
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/books", "root", "");
                    
                    PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CART_SQL);
                preparedStatement.setInt(1, customer.getId());
                preparedStatement.setFloat(2, totalPrice);
                
                preparedStatement.executeUpdate();
    
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            printSQLException(ex);
        }
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
    public boolean updateCart(Cart cart) {
        boolean updateCart = false;
        String UPDATE_CART_SQL = "UPDATE cart set TotalPrice = ? WHERE customerId = ? ";
         try {
             
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/books", "root", "");
                    
                    PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CART_SQL);
                preparedStatement.setInt(2, cart.getCustomer().getId());
                preparedStatement.setFloat(1, cart.getPrice());
                System.out.println(preparedStatement);
                updateCart = preparedStatement.executeUpdate() > 0;
    
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            printSQLException(ex);
        }
         return updateCart;
    }

    @Override
    public int sellectCartIdByCustomerId(int i) {
       String SELLECT_CARTBYCUSTOMERID_SQL = "SELECT id from cart WHERE customerId = ? AND status = 0";
       int id = -1;
         try {
             
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/books", "root", "");
                    
                    PreparedStatement preparedStatement = connection.prepareStatement(SELLECT_CARTBYCUSTOMERID_SQL);
                preparedStatement.setInt(1, i);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                id = rs.getInt("id");
            }
    
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            printSQLException(ex);
        }
         return id;
    }

    @Override
    public Cart selectCartById(int id) {
       String SELLECT_CARTBYID_SQL = "SELECT * from cart WHERE id = ? ";
       Cart cart = new Cart();
         try {
             
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/books", "root", "");
                    
                    PreparedStatement preparedStatement = connection.prepareStatement(SELLECT_CARTBYID_SQL);
                preparedStatement.setInt(1, id);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                float price = rs.getFloat("TotalPrice");
                int status = rs.getInt("status");
                
                cart.setPrice(price);
                cart.setStatus(status);
                
            }
    
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            printSQLException(ex);
        }
         return cart;
    }

    @Override
    public boolean deleteCartById(int i) {
        boolean update = false;
        String DELETE_CARTBYID_SQL = "DELETE from cart WHERE id = ?";
         try {
             
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/books", "root", "");
                    
                    PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CARTBYID_SQL);
              
                preparedStatement.setInt(1, i);
                System.out.println(preparedStatement);
                update = preparedStatement.executeUpdate() > 0;
    
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            printSQLException(ex);
        }
         return update;
    }

    @Override
    public boolean updateFinishCart(Cart cart) {
        boolean updateCart = false;
        String UPDATE_CART_SQL = "UPDATE cart set status = 1 WHERE id = ? ";
         try {
             
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/books", "root", "");
                    
                    PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CART_SQL);
                preparedStatement.setFloat(1, cart.getId());
                System.out.println(preparedStatement);
                updateCart = preparedStatement.executeUpdate() > 0;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            printSQLException(ex);
        }
         return updateCart;
    }

    
}
