/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.fproject.model;

import java.io.Serializable;
import java.util.List;
import java.util.Collection;
/**
 *
 * @author Jatin
 */
public class FoodItem implements Serializable{
    private long id;
    private String foodName;
    private int price;
    private String description;
    private int quantity;
    private List<Attachment> attachments;
    private List<Comments> comments;


    public FoodItem(){
        //empty constructor
    }

    public FoodItem(long id, String foodName, int price, String description, int quantity){
        this.id = id;
        this.foodName = foodName;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;     
    }

    public String getFoodName(){
        return this.foodName;
    }

    public void setFoodName(String name){
        this.foodName = name;
    }

    public int getPrice(){
        return this.price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    public Attachment getAttachment(int index){
        return this.attachments.get(index);
    }

    public List<Attachment> getAttachments(){
        return this.attachments;
    }

    public void addAttachment(Attachment attachment){
        this.attachments.add(attachment);
    }

    public int getNumberOfAttachments(){
        return this.attachments.size();
    }

    public Comments getComment(int index){
        return this.comments.get(index);
    }

    public List<Comments> getComments(){
        return this.comments;
    }

    public int getNumberOfComments(){
        return this.comments.size();
    }
    
    public void addComment(Comments comment){
        this.comments.add(comment);
    }
}