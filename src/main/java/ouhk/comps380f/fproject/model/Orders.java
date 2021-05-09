/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.fproject.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Lkii
 */
@Entity
@Table(name = "orders")
public class Orders implements Serializable{
    @Id
    @Column(updatable = false)
    private String userName;
    private String item;
    private java.sql.Date orderDate;
    
    public Orders(String userName, String item,java.sql.Date orderDate) {
        this.userName = userName;
        this.item = item;
        this.orderDate = orderDate;
    }
    public Orders(){
    
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
    
    public java.sql.Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(java.sql.Date orderDate) {
        this.orderDate = orderDate;
    }
}
