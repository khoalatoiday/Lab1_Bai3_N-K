/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookItemDAO;

import Model.BookItem;
import Model.ChoosenBookItem;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface BookItemDAO {
   
    public BookItem getBookById(int id);
    public ArrayList<BookItem> viewAllBookItem();
    public ArrayList<BookItem> searchBookItemByName(String txtSearch);
    public void insertChoosenBookItem(ChoosenBookItem choosenBookItem, int cartId);
    public boolean updateChoosenBookItem(ChoosenBookItem choosenBookItem, int cartId);
    public boolean updateChoosenBookItem2(ChoosenBookItem choosenBookItem, int cartId, int bookItemId);
    public ArrayList<ChoosenBookItem> selectChoosenBookItemsByCartId(int id);
    public boolean deleteChoosenBookItem(int bookItemId, int cartId);
}
