/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Admin
 */
public class Shipment {
    private int id;
    private float price;
    private String name;

    public Shipment(int id, float price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }
    
    

    public Shipment(int id, float price) {
        this.id = id;
        this.price = price;
    }

    public Shipment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
