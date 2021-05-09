/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.fproject.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import ouhk.comps380f.fproject.model.Orders;
import ouhk.comps380f.fproject.dao.OrdersRepository;

@Controller
@RequestMapping("/cart")
public class cartController {

    @Resource
    OrdersRepository orderRepo;

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
    public String addToCart(@RequestParam String item, HttpServletRequest request) throws IOException {

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
    public String emptyCart(WebRequest request, SessionStatus status) {
        status.setComplete();
        request.removeAttribute("cart", WebRequest.SCOPE_SESSION);
        return "viewCart";
    }
    @RequestMapping("Zh/emptyCart")
    public String emptyCartZh (WebRequest request, SessionStatus status) {
        status.setComplete();
        request.removeAttribute("cart", WebRequest.SCOPE_SESSION);
        return "viewCartZh";
    }

    @RequestMapping("/checkout")
    public String checkout(WebRequest request, SessionStatus status,HttpServletRequest request1,Principal principal) throws InterruptedException {
        
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        HttpSession session = request1.getSession();
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");;
        try {
            Orders cartinfo = new Orders(principal.getName(),cart.toString(),date);
            orderRepo.save(cartinfo);
        }
        catch(NullPointerException npe){
            return "viewCart";
        }
        
        status.setComplete();
        request.removeAttribute("cart", WebRequest.SCOPE_SESSION);
        return "viewCart";
    }
    @RequestMapping("Zh/checkout")
    public String checkoutZh(WebRequest request, SessionStatus status,HttpServletRequest request1,Principal principal) {
        
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        HttpSession session = request1.getSession();
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");;
        try {
            Orders cartinfo = new Orders(principal.getName(),cart.toString(),date);
            orderRepo.save(cartinfo);
        }
        catch(NullPointerException npe){
            return "viewCart";
        }
        
        status.setComplete();
        request.removeAttribute("cart", WebRequest.SCOPE_SESSION);
        return "viewCartZh";
    }
}
