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
public class Address {
    private int id;
    private int num;
    private String street;
    private String district;
    private String city;

    public Address(int num, String street, String district, String city) {
        this.num = num;
        this.street = street;
        this.district = district;
        this.city = city;
    }

    
    
    public Address(int id, int num, String street, String district, String city) {
        this.id = id;
        this.num = num;
        this.street = street;
        this.district = district;
        this.city = city;
    }

    public Address() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    
}
