/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.fproject.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ouhk.comps380f.fproject.dao.OrdersRepository;
import ouhk.comps380f.fproject.dao.UserRepository;
import ouhk.comps380f.fproject.model.Orders;

@Controller
@RequestMapping("/orderHistory")
public class historyController {

    @Resource
    OrdersRepository OrderRepo;
    
    @GetMapping("")
    public String orderHistory(ModelMap model,Principal principal) {
        List<Orders> allOrder =OrderRepo.findAll();
        List<Orders> historyList = new ArrayList<>();
        System.out.println("Allorder: "+allOrder);
        for(int i=0; i<allOrder.size();i++){
            System.out.println("Number: "+allOrder.size());
            System.out.println("Object: "+allOrder.get(i));
            if(allOrder.get(i).getUserName().equals(principal.getName())){
                System.out.println("Object: "+allOrder.get(i));
                System.out.println("Name: "+allOrder.get(i).getUserName());
                historyList.add(allOrder.get(i));
            }          
        }
        model.addAttribute("userOrder", historyList);
        return "orderHistory";
    }
}
