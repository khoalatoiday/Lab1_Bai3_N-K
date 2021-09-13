/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import Model.Shipment;
import ShipmentDAO.ShipmentDAOImpl;
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
@WebServlet(name = "chooseShipmentServlet", urlPatterns = {"/chooseShipmentServlet"})
public class chooseShipmentServlet extends HttpServlet {

    private ShipmentDAOImpl shipmentDAOImpl;
    
    public void init(){
        shipmentDAOImpl = new ShipmentDAOImpl();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int idShipment = Integer.parseInt(request.getParameter("shipment"));
         Shipment shipment = shipmentDAOImpl.selectShipmentById(idShipment);
         
         request.getSession().setAttribute("priceOfShipment", shipment.getPrice());
         request.getSession().setAttribute("address", shipment.getName());
          response.sendRedirect("order");
    }

}
