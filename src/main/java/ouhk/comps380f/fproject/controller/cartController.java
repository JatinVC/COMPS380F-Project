/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.fproject.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ouhk.comps380f.fproject.dao.CartRepository;
import ouhk.comps380f.fproject.model.Cart;



@Controller
@RequestMapping("/cart")
public class cartController {
    
    @Resource
    CartRepository cartRepo;
    
    Map<Integer, String> cart = new Hashtable<>();
    
    @RequestMapping
    public String viewCart() {
        return "viewCart";
    }
    @RequestMapping("/Zh")
    public String viewCartZh() {
        return "viewCartZh";
    }
    
    @GetMapping("addToCart")
    public String addToCart(@RequestParam String item,HttpServletRequest request) throws IOException {
        
        HttpSession session = request.getSession();
        
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new Hashtable<>());
        }       
        
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");;
        if (!cart.containsKey(item)) {
            cart.put(item, 0);
        }
        cart.put(item, cart.get(item) + 1);
        
        return "viewCart";
    }
    
    @RequestMapping("/emptyCart")
    public String emptyCart(WebRequest request,SessionStatus status){
        status.setComplete();
        request.removeAttribute("cart",WebRequest.SCOPE_SESSION);
        return "viewCart";
    }
    @RequestMapping("/checkout")
    public String checkout(WebRequest request,SessionStatus status){
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        for (Integer i : cart.keySet()) {
            Cart cartinfo = new Cart(i+1,i+1,i+1,cart.get(i),i,date);
            cartRepo.save(cartinfo);
        }
        status.setComplete();
        request.removeAttribute("cart",WebRequest.SCOPE_SESSION);
        return "viewCart";
    }
}
