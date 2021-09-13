/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import BookItemDAO.BookItemImpl;
import CardDAO.CartDAOImpl;
import Model.Cart;
import Model.ChoosenBookItem;
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
@WebServlet(name = "IncreDecreQuantity", urlPatterns = {"/IncreDecreQuantity"})
public class IncreDecreQuantity extends HttpServlet {
    
    private BookItemImpl bookItemImpl;
    private CartDAOImpl cartDAOImpl;
    public void init(){
        bookItemImpl = new BookItemImpl();
        cartDAOImpl = new CartDAOImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));
        
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        float sum = (float) request.getSession().getAttribute("sum");
        if(action.equals("insc")){
            for(ChoosenBookItem choosenBookItem : cart.getBookItems()){
                if(choosenBookItem.getBook().getId() == id){
                    int quantity = choosenBookItem.getQuantity();
                    quantity++;
                    choosenBookItem.setQuantity(quantity);
                    bookItemImpl.updateChoosenBookItem2(choosenBookItem, cart.getId(), id);
                    ArrayList<ChoosenBookItem> choosenBookItems = bookItemImpl.selectChoosenBookItemsByCartId(cart.getId());
                    cart.setBookItems(choosenBookItems);
                    sum += choosenBookItem.getPrice();
                    cart.setPrice(sum);
                    cartDAOImpl.updateCart(cart);
                    request.getSession().setAttribute("cart", cart);
                    request.getSession().setAttribute("sum", sum);
                    response.sendRedirect("cart");
                }
            }
        }
        
        if(action.equals("desc")){
            for(ChoosenBookItem choosenBookItem : cart.getBookItems()){
                if(choosenBookItem.getBook().getId() == id){
                    int quantity = choosenBookItem.getQuantity();
                    quantity--;
                    choosenBookItem.setQuantity(quantity);
                    bookItemImpl.updateChoosenBookItem2(choosenBookItem, cart.getId(), id);
                    ArrayList<ChoosenBookItem> choosenBookItems = bookItemImpl.selectChoosenBookItemsByCartId(cart.getId());
                    cart.setBookItems(choosenBookItems);
                    sum -= choosenBookItem.getPrice();
                    cart.setPrice(sum);
                    cartDAOImpl.updateCart(cart);
                    request.getSession().setAttribute("cart", cart);
                    request.getSession().setAttribute("sum", sum);
                    response.sendRedirect("cart");
                }
            }
        }
    }

}
