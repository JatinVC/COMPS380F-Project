/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.fproject.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Lkii
 */
@Entity
@Table(name = "order")
public class Cart implements Serializable{
    
    @Id
    private int id;
    private int orderNumber;
    private int userId;
    private int itemId;
    private String itemName;
    private int quantity; 
    private java.sql.Date date;
    
    public Cart(int orderNumber, int userId, int itemId, String itemName, int quantity,java.sql.Date date) {
        
        this.orderNumber = orderNumber;
        this.userId = userId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.date = date;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }
    
    public Cart() {
        
    }
    
    public int getOrderNumber() {
        return orderNumber;
    }

    public String getItemName() {
        return itemName;
    }


    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUserId() {
        return userId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }
    
}
