package de.hsba.bi.StuOrgPortal.web.user.controller;

import de.hsba.bi.StuOrgPortal.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/studentRegistration")
    public String showForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
       return "register_form";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        System.out.println(user);
        return "successful";
    }


    /* User Index Mapping

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/index";
    }
     */



    /* Registration Mapping */



    /*

    @GetMapping("/studentRegistration")
    public String showRegistrationForm(WebRequest request, Model model) {
        final UserDto accountDto = new UserDto();
        model.addAttribute("user", accountDto);
        return "registration";
    }

    @GetMapping("/registerLecturer")
    public String showRegistrationForm2(WebRequest request, Model model) {
        final UserDto accountDto = new UserDto();
        model.addAttribute("user", accountDto);
        return "registration";
    }

    @PostMapping("/studentRegistration")
    public final ModelAndView createNewStudent(@ModelAttribute("user") @Valid UserDto userDto, final HttpServletRequest request, final Errors errors) {

        try {
            User registered = UserService.createNewStudent(userDto);
        } catch (final UserAlreadyExistException uaeEx) {
            ModelAndView mav = new ModelAndView("studentRegistration", "user", userDto);
            String errMessage = messages.getMessage("message.regError", null, request.getLocale());
            mav.addObject("message", errMessage);
            return mav;
        } catch (final RuntimeException ex) {
            return new ModelAndView("emailError", "user", userDto);
        }
        return new ModelAndView("successful", "user", userDto);
    }

    @PostMapping("/user/registerLecturer")
    public final ModelAndView createNewLecturer(@ModelAttribute("user") @Valid final UserDto userDto, final HttpServletRequest request, final Errors errors) {

        try {
            final User registered = UserService.createNewLecturer(userDto);
        } catch (final UserAlreadyExistException uaeEx) {
            ModelAndView mav = new ModelAndView("registerLecturer", "user", userDto);
            String errMessage = messages.getMessage("message.regError", null, request.getLocale());
            mav.addObject("message", errMessage);
            return mav;
        } catch (final RuntimeException ex) {
            return new ModelAndView("emailError", "user", userDto);
        }
        return new ModelAndView("successful", "user", userDto); */
}


