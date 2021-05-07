/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.fproject.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import ouhk.comps380f.fproject.dao.CommentRepository;
import ouhk.comps380f.fproject.dao.ItemRepository;
import ouhk.comps380f.fproject.dao.PictureRepository;
import ouhk.comps380f.fproject.model.Comments;
import ouhk.comps380f.fproject.model.FoodItem;

/**
 *
 * @author Jatin
 */
@Controller
@RequestMapping("/items")
public class ItemController {

    @Resource
    private ItemRepository itemRepo;
    @Resource
    private PictureRepository pictureRepo;
    @Resource
    private CommentRepository commentRepo;

    private long ITEM_ID_SEQUENCE = 1;

    // TODO only admins are allowed on this route.
    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("addItem", "itemForm", new FoodForm());
    }

    public static class FoodForm implements Serializable {

        private String name;
        private int price;
        private String description;
        private List<MultipartFile> attachments;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getPrice() {
            return this.price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public List<MultipartFile> getAttachments() {
            return this.attachments;
        }

        public void setAttachments(List<MultipartFile> attachments) {
            this.attachments = attachments;
        }
    }

    // TODO add this item to the Database after addition, only admins are allowed in this route.
    @PostMapping("/create")
    public View create(FoodForm form) throws IOException {
        FoodItem item = new FoodItem();
        item.setFoodName(form.getName());
        item.setPrice(form.getPrice());
        item.setDescription(form.getDescription());
        long itemId = itemRepo.createItem(item.getFoodName(), item.getPrice(), item.getDescription(), item.getQuantity());
        item.setId(itemId);
        for (MultipartFile filePart : form.getAttachments()) {
            //create items here
            pictureRepo.createPicture(filePart.getOriginalFilename(), filePart.getContentType(), itemId, filePart.getInputStream());
        }
        return new RedirectView("/", true);
    }

    private synchronized long getNextItemId() {
        return this.ITEM_ID_SEQUENCE++;
    }

    @GetMapping("/list")
    public String itemInfo(ModelMap model) {
        model.addAttribute("Items", itemRepo.getItems());
        return "itemInfo";
    }

    @GetMapping("/setAvaToFalse/{idForSet2}")
    public View setAvaToFalse(@PathVariable("idForSet2") int idForSet2) {
        itemRepo.setAvaToFalse(idForSet2);
        RedirectView rv = new RedirectView();
        rv.setContextRelative(true);
        rv.setUrl("/items/itemInfo/{idForSet2}");
        return rv;
    }

    @GetMapping("/setAvaToTrue/{idForSet}")
    public View setAvaToTrue(@PathVariable("idForSet") int idForSet) {
        itemRepo.setAvaToTrue(idForSet);
        RedirectView rv = new RedirectView();
        rv.setContextRelative(true);
        rv.setUrl("/items/itemInfo/{idForSet}");
        return rv;
    }

    @GetMapping("/{id}")
    public String item(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("Items", itemRepo.getItem(id));
        model.addAttribute("pictures", pictureRepo.getAttachments(id));
        model.addAttribute("comments", commentRepo.getComments(id));
        return "itemPage";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editItem(ModelMap model, @PathVariable("id") long id) throws IOException{
        Map<String, Object> models = new HashMap<>();
        List<FoodItem> item = itemRepo.getItem(id);
        models.put("item", item.get(0));
        models.put("itemForm", new FoodForm());
        return new ModelAndView("editItemForm", models);
    }

    @PostMapping("/{id}/edit")
    public View updateItem(FoodForm form, @PathVariable("id") Long id) throws IOException{
        itemRepo.updateItem(id, form.getName(), form.getPrice(), form.getDescription(), true);
        return new RedirectView("/items/"+id.toString());
    }

    @GetMapping("/{id}/delete")
    public View deleteItem(@PathVariable("id") int id) {
        itemRepo.deleteItem(id);
        return new RedirectView("/items/list", true);
    }

    @GetMapping("/viewCart")
    public String viewCart() {
        return "viewCart";
    }

    @GetMapping("/{itemId}/comment")
    public ModelAndView commentForm(@PathVariable("itemId") long itemId) {
        return new ModelAndView("commentForm", "commentForm", new CommentForm());
    }

    private static class CommentForm implements Serializable {

        private String commentContent;
        private Date date;

        //getters and settings
        public String getCommentContent() {
            return this.commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public Date getDate() {
            return this.date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }

    // @PostMapping("/{itemId}/comment")
    // public View create(CommentForm form, @PathVariable("itemId") long itemId) throws IOException {
    //     Comments comment = new Comments();
    //     comment.setContent(form.getCommentContent());
    //     comment.setDate(new Date());
    //     comment.setItemId(itemId);
    //     comment.setUserId(getNextItemId());
    //     //add the comment to the database.
    //     commentRepo.createComment(comment.getUserId(), comment.getItemId(), comment.getContent(), (java.sql.Date) comment.getDate());
    //     return new RedirectView("/", true);
    // }
}
