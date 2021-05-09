/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.fproject.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {
    
   @GetMapping
    public String index() {
        return "redirect:/items/list";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/loginZh")
    public String loginZh() {
        return "loginZh";
    }
    
}