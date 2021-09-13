/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardDAO;

import Model.Cart;
import Model.Customer;

/**
 *
 * @author Admin
 */
public interface CartDAO {
    public void addToCart(Customer customer, float totalPrice);
   public boolean updateCart(Cart cart);
   public int sellectCartIdByCustomerId(int id);
   public Cart selectCartById(int id);
   public boolean  deleteCartById(int id);
   public boolean updateFinishCart(Cart cart);
}
