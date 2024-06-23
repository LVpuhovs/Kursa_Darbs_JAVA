package lv.venta.controller;

import lv.venta.model.MyUser;
import lv.venta.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String getLogin() {
        return "login-page";
    }

    @PostMapping("/login")
    public String login( MyUser user, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login-page";
        }else{
            model.addAttribute("user", user);
            return "redirect:/driver/all";
        }
    }

    @GetMapping("/signup")
    public String GetSignup(Model model) {
        model.addAttribute("user", new MyUser());
        return "signup-page";
    }

    @PostMapping("/signup")
    public String postSignup(@ModelAttribute MyUser user, Model model) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        model.addAttribute("user", user);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String getLogout() {
        return "redirect:/login?logout";
    }
}
