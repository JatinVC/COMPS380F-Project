/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.fproject.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Jatin
 */
public class Comments implements Serializable{
    private long id;
    private long userId;
    private long itemId;
    private String content;
    private Date date;

    public Comments(){
        //this is an empty constructor
    }

    public Comments(long id, long userId, long itemId, String content){
        this.id = id;
        this.userId = userId;
        this.itemId = itemId;
        this.content = content;
        this.date = new Date();
    }

    //getters and setters
    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getUserId(){
        return this.userId;
    }

    public void setUserId(long userId){
        this.userId = userId;
    }

    public long getItemId(){
        return this.itemId;
    }

    public void setItemId(long itemId){
        this.itemId = itemId;
    }

    public String getContent(){
        return this.content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date){
        this.date = date;
    }
}
