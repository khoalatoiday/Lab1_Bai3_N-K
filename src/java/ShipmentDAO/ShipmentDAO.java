/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShipmentDAO;

import Model.Shipment;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface ShipmentDAO {
    public ArrayList<Shipment> selectAllShipments();
    public Shipment selectShipmentById(int id);
}
