/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import Model.Payment;
import Model.Shipment;
import PaymentDAO.PaymentDAOImpl;
import ShipmentDAO.ShipmentDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "orderServlet", urlPatterns = {"/order"})
public class orderServlet extends HttpServlet {
        
    private PaymentDAOImpl paymentDAOImpl;
    private ShipmentDAOImpl shipmentDAOImpl;
    
    public void init(){
        paymentDAOImpl = new PaymentDAOImpl();
        shipmentDAOImpl = new ShipmentDAOImpl();
    }
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response) 
           throws ServletException, IOException{ 
       ArrayList<Payment> payments =  paymentDAOImpl.selectAllPayment();
       request.getSession().setAttribute("listPayment", payments);
         ArrayList<Shipment> shipments = shipmentDAOImpl.selectAllShipments();
         request.getSession().setAttribute("listShipment", shipments);
       
       RequestDispatcher dispatcher = request.getRequestDispatcher("order.jsp");
       dispatcher.forward(request, response);
   }
     
     
     
     
}
