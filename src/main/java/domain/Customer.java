/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;

/**
 *
 * @author angustylee
 */
public class Customer {
    
    private Integer customerID;
    private String username;
    private String firstName;
    private String surname; 
    private String password;
    private String emailAddress;
    private String shippingAddress;

    public Customer(Integer customerID, String username, String firstName, String surname, String password, String emailAddress, String shippingAddress) {
        this.customerID = customerID;
        this.username = username;
        this.firstName = firstName;
        this.surname = surname;
        this.password = password;
        this.emailAddress = emailAddress;
        this.shippingAddress = shippingAddress;
    }
 
    public Customer() {
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerID=" + customerID + ", username=" + username + ", firstName=" + firstName + ", surname=" + surname + ", password=" + password + ", emailAddress=" + emailAddress + ", shippingAddress=" + shippingAddress + '}';
    }
    
    
    
}
