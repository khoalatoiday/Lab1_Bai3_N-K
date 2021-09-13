/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShipmentDAO;

import Model.Shipment;
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
public class ShipmentDAOImpl implements ShipmentDAO{

    @Override
    public ArrayList<Shipment> selectAllShipments() {
        ArrayList < Shipment > shipments = new ArrayList < > ();
        String SELECT_ALL_SHIPMENTS = "SELECT * FROM shipment";
        
         try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/books", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SHIPMENTS);
            ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
               int id = rs.getInt("id");
               String name = rs.getString("name");
               float price = rs.getFloat("Price");
               
               Shipment shipment = new Shipment(id,price,name);
               
               shipments.add(shipment);
               
            }
          
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
             printSQLException(ex);
        } 
        return shipments;
    }

    @Override
    public Shipment selectShipmentById(int id) {
       Shipment shipment = new Shipment();
       String SELECT_PAYMENTBYID_SQL = "SELECT * FROM shipment WHERE id = ?";
        
         try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/books", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAYMENTBYID_SQL);
            preparedStatement.setInt(1, id);
             System.out.println(preparedStatement);
             ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
               
               int i = rs.getInt("id");
               String name = rs.getString("name");
              float price = rs.getFloat("price");
               shipment = new Shipment(i,price,name);
               
            }
          
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            printSQLException(ex);
        }
         return shipment;
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
