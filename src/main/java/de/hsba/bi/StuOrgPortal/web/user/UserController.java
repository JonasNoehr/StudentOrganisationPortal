package de.hsba.bi.StuOrgPortal.web.user;

import de.hsba.bi.StuOrgPortal.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth instanceof AnonymousAuthenticationToken ? "login" : "redirect:/";
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/index";
    }

    @GetMapping(path = "/registerLecturer")
    public String registerLecturer() {
        return "users/registerLecturer";
    }

    @GetMapping(path = "/registerStudent")
    public String registerStudent() {
        return "users/registerStudent";
    }

    @PostMapping
    public String createLecturer(String username, String password) {
        userService.createNewLecturerUser(username, password);
        return "redirect:/courses/";
    }

    @PostMapping(path = "/register")
    public String createStudent(String username, String password) {
        userService.createNewStudentUser(username, password);
        return "users/successfulRegistration";
    }
}
