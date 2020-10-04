package de.hsba.bi.StuOrgPortal.web.user;

import de.hsba.bi.StuOrgPortal.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/index";
    }

    @GetMapping(path = "/registerLecturer")
    public String register() {
        return "users/registerLecturer";
    }

    @PostMapping
    public String create(String username, String password) {
        userService.createNewLecturerUser(username, password);
        return "redirect:/courses/";
    }
}