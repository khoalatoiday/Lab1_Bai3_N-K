/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import BookItemDAO.BookItemImpl;
import CardDAO.CartDAOImpl;
import Model.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
@WebServlet("/cart")
public class cartServlet extends HttpServlet {

   
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) 
           throws ServletException, IOException{ 
       RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
       dispatcher.forward(request, response);
   }
   
 
   

}
