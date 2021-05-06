/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.fproject.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ouhk.comps380f.fproject.model.FoodItem;

/**
 *
 * @author Jatin
 */
public interface ItemRepository {
    
    public long createItem(String itemName, int price, String description, int availability, List<MultipartFile> attachments) throws IOException;

    public List<FoodItem> getItems();

    public List<FoodItem> getItem(long id);

    public void updateItem(long itemId, String itemName, int price, String description, String availability, List<MultipartFile> attachments) throws IOException;

    public void deleteItem(long itemId);
}
