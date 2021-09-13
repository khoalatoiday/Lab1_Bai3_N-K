/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import CardDAO.CartDAOImpl;
import Model.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "finishOrder", urlPatterns = {"/finishOrder"})
public class finishOrder extends HttpServlet {
    private CartDAOImpl cartDAOImpl;
    
    public void init(){
        cartDAOImpl = new CartDAOImpl();
    }
    
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
       Cart cart = (Cart) request.getSession().getAttribute("cart");
       cart.setStatus(1);
       cartDAOImpl.updateFinishCart(cart);
       request.getSession().removeAttribute("sum");
       request.getSession().removeAttribute("cart");
       response.sendRedirect("books");
   }

}
