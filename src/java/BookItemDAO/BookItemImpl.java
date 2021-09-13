/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookItemDAO;

import BookItemDAO.BookItemDAO;
import Model.BookItem;
import Model.Cart;
import Model.ChoosenBookItem;
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
public class BookItemImpl implements BookItemDAO{

    @Override
    public ArrayList<BookItem> viewAllBookItem() {
        String SELECT_ALL_BOOKS_SQL = "SELECT * from book";
        ArrayList<BookItem> books = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS_SQL);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                float price = rs.getFloat("price");
                String brand = rs.getString("brand");
                
                books.add(new BookItem(id,name,type,price,brand));
            }
        } catch (ClassNotFoundException ex) {
           ex.printStackTrace();
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return books;
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
    public ArrayList<BookItem> searchBookItemByName(String txtSearch) {
        String SELECT_ALL_BOOKS_SQL = "SELECT * from book WHERE name LIKE ?" ;
        ArrayList<BookItem> books = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS_SQL);
            preparedStatement.setString(1, "%" + txtSearch + "%");
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                float price = rs.getFloat("price");
                String brand = rs.getString("brand");
                
                books.add(new BookItem(id,name,type,price,brand));
            }
        } catch (ClassNotFoundException ex) {
       ex.printStackTrace();
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return books;
    }

    @Override
    public BookItem getBookById(int id) {
        String SELLECT_BOOK_BY_ID = "SELECT * from book WHERE id = ?";
        BookItem book = new BookItem();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(SELLECT_BOOK_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                String name = rs.getString("name");
                String type = rs.getString("type");
                float price =rs.getFloat("price");
                String brand = rs.getString("brand");
                book = new BookItem(id,name,type,price,brand);
            }
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        
        
        return book;
    }

    @Override
    public void insertChoosenBookItem(ChoosenBookItem choosenBookItem, int cartId) {
         String INSERT_CHOOSENBOOKITEM_SQL = "INSERT INTO choosenbookitem" +
                    "  (bookitemid, cartid, quantity, price) VALUES " +
                    " (?, ?, ?, ?);";   
        
        try {
             
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/books", "root", "");
                    
                    PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CHOOSENBOOKITEM_SQL);
               preparedStatement.setInt(1, choosenBookItem.getBook().getId());
               preparedStatement.setInt(2, cartId);
                preparedStatement.setInt(3, choosenBookItem.getQuantity());
                preparedStatement.setFloat(4, choosenBookItem.getPrice());
                
                System.out.println(preparedStatement);
                // Step 3: Execute the query or update query
                preparedStatement.executeUpdate();
                
          
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            printSQLException(ex);
        }
    }

    @Override
    public boolean updateChoosenBookItem(ChoosenBookItem cbi, int cartId) {
         boolean update = false;
        String UPDATE_CART_SQL = "UPDATE choosenBookItem set quantity = ? WHERE cartId = ? ";
         try {
             
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/books", "root", "");
                    
                    PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CART_SQL);
                preparedStatement.setInt(2, cartId);
                preparedStatement.setInt(1, cbi.getQuantity());
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
    public ArrayList<ChoosenBookItem> selectChoosenBookItemsByCartId(int cartid) {
        ArrayList<ChoosenBookItem> choosenBookItems = new ArrayList<>();
        String SELECT_CHOOSENBOONITEMS_SQL = "SELECT * from choosenbookitem WHERE cartid = ?";
        try {
             
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/books", "root", "");
                    
                    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CHOOSENBOONITEMS_SQL);
                preparedStatement.setInt(1, cartid);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("id");
                    int bookitemId = rs.getInt("BookItemId");
                    int quantity = rs.getInt("Quantity");
                    float price = rs.getFloat("Price");
                    BookItem book = getBookById(bookitemId);
                    
                    ChoosenBookItem choosenBookItem = new ChoosenBookItem(id,book,quantity,price);
                    choosenBookItems.add(choosenBookItem);
                }
    
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        
        return choosenBookItems;
    }

    @Override
    public boolean updateChoosenBookItem2(ChoosenBookItem cbi, int i, int i1) {
         boolean update = false;
        String UPDATE_CART_SQL = "UPDATE choosenBookItem set quantity = ? WHERE cartId = ? AND BookItemId = ? ";
         try {
             
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/books", "root", "");
                    
                    PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CART_SQL);
                preparedStatement.setInt(2, i);
                preparedStatement.setInt(1, cbi.getQuantity());
                preparedStatement.setInt(3, i1);
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
    public boolean deleteChoosenBookItem(int bookItemId, int cartId) {
        boolean update = false;
        String DELETE_BOOKITEM_SQL = "DELETE from choosenbookitem WHERE bookItemId = ? AND cartId = ?";
         try {
             
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/books", "root", "");
                    
                    PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOKITEM_SQL);
                preparedStatement.setInt(2, bookItemId);
                preparedStatement.setInt(1, cartId);
                System.out.println(preparedStatement);
                update = preparedStatement.executeUpdate() > 0;
    
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            printSQLException(ex);
        }
         return update;
    }
     
    
}
