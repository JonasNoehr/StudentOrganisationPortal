package de.hsba.bi.StuOrgPortal.web;

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
    public String login(Model model) {

        return "users/login";
    }

    @RequestMapping("/register")
    public String register(Model model) {

        return "users/registerStudent";
    }
}
