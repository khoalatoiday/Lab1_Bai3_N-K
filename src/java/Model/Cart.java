/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Cart {
    private int id;
    private List<ChoosenBookItem> bookItems;
    private Customer customer;
    private int status;
    private float price;
    
    

    public Cart(int id, List<ChoosenBookItem> bookItems, Customer customer, int status) {
        this.id = id;
        this.bookItems = bookItems;
        this.customer = customer;
        this.status = status;
    }

    public Cart(int id, List<ChoosenBookItem> bookItems, Customer customer, int status, float price) {
        this.id = id;
        this.bookItems = bookItems;
        this.customer = customer;
        this.status = status;
        this.price = price;
    }

    
    
    public Cart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ChoosenBookItem> getBookItems() {
        return bookItems;
    }

    public void setBookItems(List<ChoosenBookItem> bookItems) {
        this.bookItems = bookItems;
    }

    

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    
    
    
}
