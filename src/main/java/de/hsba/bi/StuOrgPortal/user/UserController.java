package de.hsba.bi.StuOrgPortal.user;

import org.springframework.stereotype.Controller;

@Controller

public class UserController {

    private final UserService userService;

    public void UserService(UserService userService) {
        this.userService = userService;
    }
}
