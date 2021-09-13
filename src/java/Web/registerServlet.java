/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import AccountDAO.AccountDAOImpl;
import Model.Account;
import Model.Address;
import Model.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet("/register")
public class registerServlet extends HttpServlet {

   private AccountDAOImpl accountDAOImpl;

    public void init() {
        accountDAOImpl = new AccountDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int num = Integer.parseInt(request.getParameter("num"));
        String city = request.getParameter("city");
        String district = request.getParameter("district");
        String street = request.getParameter("street");
        
        ArrayList<Account> accounts =  accountDAOImpl.viewAllAccount();
        int choosenId;
        if(accounts.size()>0){
            choosenId = accounts.get(accounts.size()-1).getId();
            choosenId ++;
        }else{
            choosenId = 1;
        }

        Customer customer = new Customer();
        Account account = new Account(username,password);
        Address address = new Address(num,street,district,city);
        
        customer.setId(choosenId);
        customer.setName(name);
        customer.setGender(gender);
        customer.setAccount(account);
        customer.setAddress(address);
        try {
            accountDAOImpl.register(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("index.jsp");
    }
}
