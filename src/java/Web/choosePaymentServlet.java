/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import Model.Payment;
import PaymentDAO.PaymentDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "choosePaymentServlet", urlPatterns = {"/choosePaymentServlet"})
public class choosePaymentServlet extends HttpServlet {
    
    private PaymentDAOImpl paymentDAOImpl;
    
    public void init(){
        paymentDAOImpl= new PaymentDAOImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int id = Integer.parseInt(request.getParameter("payment"));
         Payment payment = paymentDAOImpl.selectPaymentById(id);
         request.getSession().setAttribute("nameOfPayment", payment.getType());
        request.getSession().setAttribute("discount", payment.getDiscount());
         response.sendRedirect("order");
    }

}
