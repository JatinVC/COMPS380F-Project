/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.fproject.model;

import java.io.Serializable;
import java.util.Base64;

/**
 *
 * @author Jatin
 */
public class Attachment implements Serializable {

    private long itemId;
    private long id;
    private String mimeContentType;
    private String attachmentName;
        private String picdata;
    // the below will be converted to a base64 String
    private byte[] contents;
    private String contentsString;


    public Attachment() {
        //empty constructor
    }

    public Attachment(long id, long itemid, String picName, String mimeContentType, String picdata) {
        this.id = id;
        this.itemId = itemid;
        this.attachmentName = picName;
        this.mimeContentType = mimeContentType;
        this.contentsString = picdata;
    }

    public String getPicdata() {
        return this.picdata;
    }

    public void setPicdata(String picdata) {
        this.picdata = picdata;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getItemId() {
        return this.itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getAttachmentName() {
        return this.attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getMimeContentType() {
        return this.mimeContentType;
    }

    public void setMimeContentType(String mimeContentType) {
        this.mimeContentType = mimeContentType;
    }

    public byte[] getContents() {
        return this.contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }

    public String getContentsString() {
        return this.contentsString;
    }

    public void setContentsString(byte[] contents) {
        this.contentsString = Base64.getEncoder().encodeToString(contents);
    }

    public void setContentsString(String contents) {
        this.contentsString = contents;
    }
}
