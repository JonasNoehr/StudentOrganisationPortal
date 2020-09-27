package de.hsba.bi.StuOrgPortal.web.user.controller;

import de.hsba.bi.StuOrgPortal.user.User;
import de.hsba.bi.StuOrgPortal.user.UserService;
import de.hsba.bi.StuOrgPortal.user.UserServiceII;
import de.hsba.bi.StuOrgPortal.web.error.UserAlreadyExistException;
import de.hsba.bi.StuOrgPortal.web.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    private MessageSource messages;

    @Autowired
    private UserServiceII UserService;

    /* User Index Mapping*/

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/index";
    }


    /* Registration Mapping */

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
        return new ModelAndView("successful", "user", userDto);
    }
}

