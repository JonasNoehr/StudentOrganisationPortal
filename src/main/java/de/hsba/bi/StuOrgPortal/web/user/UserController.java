package de.hsba.bi.StuOrgPortal.web.user;

import de.hsba.bi.StuOrgPortal.user.User;
import de.hsba.bi.StuOrgPortal.user.UserService;
import de.hsba.bi.StuOrgPortal.web.course.CourseEntryForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserFormConverter formConverter;

    // User wird eingeloggt
    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth instanceof AnonymousAuthenticationToken ? "login" : "redirect:/";
    }

    // Aufruf der Index Seite
    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/index";
    }

    // Aufruf Registrierungs Seite für einen Dozenten, vom admin ausgeführt
    @GetMapping(path = "/registerLecturer")
    public String registerLecturer() {
        return "users/registerLecturer";
    }

    // Anlegen eines neuen Dozenten
    @PostMapping
    public String createLecturer(String username, String password) {
        userService.createNewLecturerUser(username, password);
        return "redirect:/courses/";
    }

    // Registreirung eines neuen STudenten
    @PostMapping(path = "/register")
    public String createStudent(String name, Model model, @ModelAttribute("userForm") @Valid UserForm userForm, BindingResult userBindingResult) {
        // wenn Nutzer bereits existiert
        if (userService.userExists(name) != null) {
            String msg = "Nutzer existiert bereits!";
            model.addAttribute("msg", msg);
            return "users/registerStudent";
        }
        if (userBindingResult.hasErrors()) {
            return "users/registerStudent";
        }
        userService.createNewStudentUser(formConverter.update(new User(), userForm));
        return "users/successfulRegistration";
    }
}
