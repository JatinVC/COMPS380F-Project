/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.fproject.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author Jatin
 */
public class FoodItem implements Serializable{
    private long id;
    private String name;
    private int price;
    private Map<String, Attachment> attachments = new Hashtable<>();

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getPrice(){
        return this.price;
    }

    public void setPrice(int price){
        this.price = price;
    }
    
    public Attachment getAttachment(String name){
        return this.attachments.get(name);
    }

    public Collection<Attachment> getAttachments(){
        return this.attachments.values();
    }

    public void addAttachment(Attachment attachment){
        this.attachments.put(attachment.getName(), attachment);
    }

    public int getNumberOfAttachments(){
        return this.attachments.size();
    }
}
