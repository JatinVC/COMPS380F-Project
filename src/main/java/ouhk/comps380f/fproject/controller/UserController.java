package ouhk.comps380f.fproject.controller;

import java.io.IOException;
import static java.lang.System.console;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @GetMapping({"/list"})
    public String list(ModelMap model, HttpServletRequest request) {
        if (!request.isUserInRole("ROLE_ADMIN")) {
            return "noAuthority";
        }
        model.addAttribute("systemUsers", UserRepo.findAll());
        return "listUser";
    }

    public static class Form {

        private String username;
        private String password;
        private String[] roles;
        private String fullName;
        private String phoneNumber;
        private String address;

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

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

    }

    @GetMapping("/createUser")
    public ModelAndView create(HttpServletRequest request) {
        if (!request.isUserInRole("ROLE_ADMIN")) {
            return new ModelAndView("noAuthority");
        }
        return new ModelAndView("adminCreateUser", "SystemUser", new Form());
    }

    @PostMapping("/createUser")
    public View create(Form form) throws IOException {

        SystemUser user = new SystemUser(form.getUsername(),
                form.getPassword(), form.getRoles(), form.getFullName(), form.getPhoneNumber(), form.getAddress()
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
        String[] normalUser = {"ROLE_USER"};
        SystemUser user = new SystemUser(form.getUsername(),
                form.getPassword(), normalUser, form.getFullName(), form.getPhoneNumber(), form.getAddress()
        );
        UserRepo.save(user);
        return new RedirectView("/login", true);
    }

    @GetMapping("/update/{username}")
    public ModelAndView userUpdate(@PathVariable("username") String username, HttpServletRequest request) {
        Form updateForm = new Form();
        if (!request.isUserInRole("ROLE_ADMIN")) {
            SystemUser user = UserRepo.findById(username).get();
            String truePassword = user.getPassword();
            updateForm.setPassword(truePassword.replaceAll("\\{noop\\}", ""));
            updateForm.setFullName(user.getFullName());
            updateForm.setPhoneNumber(user.getPhoneNumber());
            updateForm.setAddress(user.getAddress());
            return new ModelAndView("userUpdate", "SystemUser", updateForm);
        } else {
            SystemUser user = UserRepo.findById(username).get();
            updateForm.setUsername(user.getUsername());
            String truePassword = user.getPassword();
            updateForm.setPassword(truePassword.replaceAll("\\{noop\\}", ""));
            updateForm.setFullName(user.getFullName());
            updateForm.setPhoneNumber(user.getPhoneNumber());
            updateForm.setAddress(user.getAddress());

            return new ModelAndView("adminUpdate", "SystemUser", updateForm);
        }
    }

    @PostMapping("/update/{username}")
    public View userUpdate(@PathVariable("username") String username, Form form) throws IOException {
        SystemUser olduser = UserRepo.findById(username).get();

        SystemUser user = new SystemUser(olduser.getUsername(),
                form.getPassword(), form.getRoles(), form.getFullName(), form.getPhoneNumber(), form.getAddress()
        );
        UserRepo.save(user);

        return new RedirectView("/user/list", true);
    }

    @GetMapping("/delete/{username}")
    public View deleteUser(@PathVariable("username") String username, HttpServletRequest request) {
        if (!request.isUserInRole("ROLE_ADMIN")) {
            return new RedirectView("/user/noAuthority", true);
        }
        UserRepo.delete(UserRepo.findById(username).orElse(null));
        return new RedirectView("/user/list", true);
    }

    @GetMapping("/noAuthority")
    public String noAuthority() {
        return "noAuthority";
    }
}
