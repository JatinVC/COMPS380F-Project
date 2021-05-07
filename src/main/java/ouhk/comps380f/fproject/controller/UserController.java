package ouhk.comps380f.fproject.controller;

import java.io.IOException;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import ouhk.comps380f.fproject.dao.UserRepository;
import ouhk.comps380f.fproject.model.SystemUser;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    UserRepository UserRepo;

    @GetMapping({"","/list"})
    public String list(ModelMap model) {
        model.addAttribute("systemUsers", UserRepo.findAll());
        return "listUser";
    }

    public static class Form {

        private String username;
        private String password;
        private String[] roles;
        
        // ... getters and setters for each of the properties

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String[] getRoles() {
            return roles;
        }

        public void setRoles(String[] roles) {
            this.roles = roles;
        }
        
    }

    @GetMapping("/createUser")
    public ModelAndView create() {
        return new ModelAndView("adminCreateUser", "SystemUser", new Form());
    }

    @PostMapping("/createUser")
    public View create(Form form) throws IOException {
        SystemUser user = new SystemUser(form.getUsername(),
                form.getPassword(), form.getRoles()
        );
        UserRepo.save(user);
        return new RedirectView("/user/list", true);
    }
    @GetMapping("/userRegister")
    public ModelAndView register() {
        return new ModelAndView("registerUser", "SystemUser", new Form());
    }

    @PostMapping("/userRegister")
    public View register(Form form) throws IOException {
        String[] normalUser={"Role_User"};
        SystemUser user = new SystemUser(form.getUsername(),
                form.getPassword(), normalUser
        );
        UserRepo.save(user);
        return new RedirectView("/login", true);
    }
    
    @GetMapping("/delete/{username}")
    public View deleteUser(@PathVariable("username") String username) {
        UserRepo.delete(UserRepo.findById(username).orElse(null));
        return new RedirectView("/user/list", true);
    }
}
