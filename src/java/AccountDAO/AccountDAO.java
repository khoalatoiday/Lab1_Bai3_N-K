/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccountDAO;

import Model.Account;
import Model.Customer;
import java.util.ArrayList;


public interface AccountDAO{
    public boolean checkLogin(Account account);
    public void register(Customer customer);
    public ArrayList<Account> viewAllAccount();
    public int sellectCustomerIdById(String username);
}

