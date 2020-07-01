/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author angustylee
 */
public class Sale {
    
    private Integer saleID;
    private LocalDate date;
    private String status;
    
    private List<SaleItem> items;
    private Customer customer;
    
    public BigDecimal getTotal(){
        BigDecimal total = BigDecimal.ZERO;
        for (SaleItem item : items) {
            total = total.add(item.getItemTotal());
        }
    return total;
    
        }

    public List<SaleItem> getItems() {
        return items;
    }
    
    public void addItem(SaleItem item) {
        this.items.add(item);
    }

    public Integer getSaleID() {
        return saleID;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setSaleID(Integer saleID) {
        this.saleID = saleID;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Sale{" + "saleID=" + saleID + ", date=" + date + ", status=" + status + ", customer=" + customer + '}';
    }
    
    
       
    }

