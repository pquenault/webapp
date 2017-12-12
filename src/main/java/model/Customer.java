/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author paul
 */
public class Customer {

    private final int customerId;
    private final String discountCodeId;
    private final String zip;
    private final String name;
    private final String adressLine1;
    private final String adressLine2;
    private final String city;
    private final String state;
    private final String phone;
    private final String fax;
    private final String email;
    private final int creditLimit;

    public Customer(int customerId, String discountCodeId, String zip,
            String name, String adressLine1, String adressLine2, String city,
            String state, String phone, String fax, String email, int creditLimit) {
        this.customerId = customerId;
        this.discountCodeId = discountCodeId;
        this.zip = zip;
        this.name = name;
        this.adressLine1 = adressLine1;
        this.adressLine2 = adressLine2;
        this.city = city;
        this.state = state;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.creditLimit = creditLimit;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getDiscountCodeId() {
        return discountCodeId;
    }

    public String getZip() {
        return zip;
    }

    public String getName() {
        return name;
    }

    public String getAdressLine1() {
        return adressLine1;
    }

    public String getAdressLine2() {
        return adressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPhone() {
        return phone;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }

    public int getCreditLimit() {
        return creditLimit;
    }
}
