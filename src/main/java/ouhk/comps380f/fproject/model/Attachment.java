/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.fproject.model;

import java.io.Serializable;

/**
 *
 * @author Jatin
 */
public class Attachment implements Serializable{
    private String name;
    private String mimeContentType;
    private byte[] contents;
    
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getMimeContentType(){
        return this.mimeContentType;
    }

    public void setMimeContentType(String mimeContentType){
        this.mimeContentType = mimeContentType;
    }

    public byte[] getContents(){
        return this.contents;
    }

    public void setContents(byte[] contents){
        this.contents = contents;
    }
}
