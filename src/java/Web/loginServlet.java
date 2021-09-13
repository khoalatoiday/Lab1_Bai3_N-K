/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import AccountDAO.AccountDAOImpl;
import Model.Account;
import Model.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet("/user-login")
public class loginServlet extends HttpServlet {
    private AccountDAOImpl accountDAOImpl;
    
    public void init() {
        accountDAOImpl = new AccountDAOImpl();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
       
       Account account = new Account();
       account.setUsername(username);
       account.setPassword(password);
       
       
        
        if (accountDAOImpl.checkLogin(account)) {
            request.getSession().setAttribute("auth", account);
            response.sendRedirect("books");
        } else {
            response.sendRedirect("login.jsp");
        } 
    }
}
