/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import AccountDAO.AccountDAOImpl;
import BookItemDAO.BookItemDAO;
import BookItemDAO.BookItemImpl;
import CardDAO.CartDAOImpl;
import CustomerDAO.CustomerDAOImpl;
import Model.Account;
import Model.BookItem;
import Model.ChoosenBookItem;
import Model.Cart;
import Model.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "cartServlet", urlPatterns = {"/addToCart"})
public class addToCartServlet extends HttpServlet {
    
    private BookItemImpl bookItemImpl;
   private CartDAOImpl cartDAOImpl;
   private CustomerDAOImpl customerDAOImpl;
   private AccountDAOImpl accountDAOImpl;
    
    public void init(){
        bookItemImpl = new BookItemImpl();
     cartDAOImpl = new CartDAOImpl();
     customerDAOImpl = new CustomerDAOImpl();
     accountDAOImpl = new AccountDAOImpl();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        Account account = (Account) request.getSession().getAttribute("auth");
        
       int customerId = accountDAOImpl.sellectCustomerIdById(account.getUsername());
       
       Customer customer = customerDAOImpl.sellectCustomerById(customerId);
       
        int quantity = 1;
        int id;
        float totalPrice;
        if(request.getParameter("id") != null){
            id = Integer.parseInt(request.getParameter("id"));
           BookItem book = bookItemImpl.getBookById(id);
           if(book != null){
               if(request.getParameter("quantity") != null){
                   quantity = Integer.parseInt(request.getParameter("quantity"));
               }
               HttpSession httpSession = request.getSession(true);
               
               if(httpSession.getAttribute("cart") == null){
                   totalPrice = 0;
                   Cart cart = new Cart();
                   List<ChoosenBookItem> bookitems = new ArrayList<ChoosenBookItem>();
                   ChoosenBookItem bookItem = new ChoosenBookItem();
                  // bookItem.setId(id);
                   bookItem.setBook(book);
                   bookItem.setPrice(book.getPrice());
                   bookItem.setQuantity(quantity);
                   totalPrice += (book.getPrice() * bookItem.getQuantity());
                   bookitems.add(bookItem);
                   cart.setBookItems(bookitems);
                   cart.setPrice(totalPrice);
                   cartDAOImpl.addToCart(customer,totalPrice);
                   int cartId = cartDAOImpl.sellectCartIdByCustomerId(customerId);
                   cart.setId(cartId);
                   httpSession.setAttribute("sum", cart.getPrice());
                   httpSession.setAttribute("cart", cart);
                    bookItemImpl.insertChoosenBookItem(bookItem, cartId);
               }else{
                   Cart cart = (Cart) httpSession.getAttribute("cart");
                   List<ChoosenBookItem> bookItems = cart.getBookItems();
                   boolean check = false;
                   totalPrice = cart.getPrice();
                   for(ChoosenBookItem bookItem: bookItems){
                       if(bookItem.getBook().getId() == book.getId()){
                           bookItem.setQuantity(bookItem.getQuantity() + quantity);
                           bookItemImpl.updateChoosenBookItem(bookItem, cart.getId());
                           check = true;
                           totalPrice += book.getPrice();  
                       }
                   }
                   if(check == false){
                       ChoosenBookItem bookItem = new ChoosenBookItem();
                       //bookItem.setId(book.getId());
                       bookItem.setBook(book);
                       bookItem.setPrice(book.getPrice());
                       bookItem.setQuantity(quantity);
                       bookItemImpl.insertChoosenBookItem(bookItem, cart.getId());
                       totalPrice += book.getPrice();
                       bookItems.add(bookItem);
                   }
                   cart.setPrice(totalPrice);
                   cart.setCustomer(customer);
                   cartDAOImpl.updateCart(cart);
                   httpSession.setAttribute("sum", cart.getPrice());
                   httpSession.setAttribute("cart", cart);
                  
               }
           }
        }
       
   response.sendRedirect("books");
    }
  

}
