package de.hsba.bi.StuOrgPortal.web;

import de.hsba.bi.StuOrgPortal.web.user.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String index() {
        return "redirect:/courses/";
    }

    @RequestMapping("/login")
    public String login() {
        return "users/login";
    }

    @RequestMapping("/information")
    public String information() {
        return "/information";
    }

    @RequestMapping("/privacy")
    public String privacy() {return "/privacy";}

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "users/registerStudent";
    }
}
