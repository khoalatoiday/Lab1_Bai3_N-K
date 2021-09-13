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
public class Customer {
    private int id;
    private String name;
    private String gender;
    private Address address;
    private Account account;

    public Customer(int id, String name, String gender, Address address, Account account) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.account = account;
    }

    public Customer(int id, String name, String gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }
    
    

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    
}
