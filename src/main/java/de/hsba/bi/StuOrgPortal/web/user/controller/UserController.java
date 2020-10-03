package de.hsba.bi.StuOrgPortal.web.user.controller;

import de.hsba.bi.StuOrgPortal.user.UserService;
import de.hsba.bi.StuOrgPortal.user.UserServiceII;
import de.hsba.bi.StuOrgPortal.web.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private MessageSource messages;

    @Autowired
    private UserServiceII UserService;

    @Autowired
    private UserService userService;

    public UserController() {
        super();
    }

    /* User Index Mapping*/

    @GetMapping("index")
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/index";
    }


    /* Registration Mapping */


    @PostMapping(path = "/registerStudent")
    public String createNewStudent(Model model, @ModelAttribute("user") @Valid UserDto user, BindingResult entryBinding) {

        if (entryBinding.hasErrors()) {
            return "user/error";
        }
        System.out.println(user);
        return "redirect:/successful";
    }

    // Müll, braucht man ja vll doch noch

    /*
    @PostMapping("/user/registerLecturer")
    public final ModelAndView createNewLecturer(@ModelAttribute("user") @Valid final UserDto userDto, final HttpServletRequest request, final Errors errors) {

        try {
            final User registered = userService.createNewLecturer(userDto);
        } catch (final UserAlreadyExistException uaeEx) {
            ModelAndView mav = new ModelAndView("user/registerLecturer", "user", userDto);
            String errMessage = messages.getMessage("message.regError", null, request.getLocale());
            mav.addObject("message", errMessage);
            return mav;
        } catch (final RuntimeException ex) {
            return new ModelAndView("user/error", "user", userDto);
        }
        return new ModelAndView("user/successful", "user", userDto);
    } */


    /*
    @PostMapping("/studentRegistration")
    public GenericResponse createNewStudent(@Valid UserDto) {

       return new GenericResponse("successful");

    } */






















}

