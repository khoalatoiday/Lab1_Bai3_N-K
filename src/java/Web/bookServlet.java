/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import BookItemDAO.BookItemImpl;
import Model.BookItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet("/")
public class bookServlet extends HttpServlet {

   private BookItemImpl bookItemImpl;
   
   public void init(){
       bookItemImpl = new BookItemImpl();
   }
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/books": 
                    listBooks(request, response);
                    break;
                    
                case "/searchByName":
                    searchBookByName(request, response);
                    break;
                    
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } 
    }

    private void listBooks(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
     ArrayList<BookItem> books = bookItemImpl.viewAllBookItem();
    request.setAttribute("listBooks", books); 
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp"); 
        dispatcher.forward(request, response); 
    }
    
    private void searchBookByName(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
       String txtSearch = request.getParameter("txt");
        ArrayList<BookItem> books = bookItemImpl.searchBookItemByName(txtSearch);
        request.setAttribute("listBooks", books);
        request.setAttribute("txtS", txtSearch);
         RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp"); 
        dispatcher.forward(request, response); 
    }

}
