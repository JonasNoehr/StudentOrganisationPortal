package de.hsba.bi.StuOrgPortal.user;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/*
  Bereitstellung von Methoden und initialem Admin
 */

@Service

public class UserService {

    private final UserRepository userRepository;

    private void userCreation (String name, String password, String role) {
        userRepository.save(new User(name, password, role));
    }

    @EventListener(ApplicationStartedEvent.class)

    public void adminInit() {
        userCreation("Administrator", "123456", User.ADMIN_ROLE);
    }
}

