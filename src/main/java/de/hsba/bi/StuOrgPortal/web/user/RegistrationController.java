package de.hsba.bi.StuOrgPortal.web.user;

import de.hsba.bi.StuOrgPortal.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor

public class RegistrationController {

    @GetMapping(path = "/registerLecturer")
    public String register() {
        return "users/registerLecturer";
    }

    @GetMapping("/users/UserRegistration")
    public String showRegistrationForm(WebRequest request, Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

}
