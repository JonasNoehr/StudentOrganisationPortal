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

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void userCreation (Long id, String userName, String firstName, String lastName, String password, String role) {
        userRepository.save(new User(id, userName, firstName, lastName, password, role ));
    }

    @EventListener(ApplicationStartedEvent.class)

    public void adminInit() {
        userCreation(0L, "Administrator", "", "", "IAMGOD", "ADMIN" );
    }
}

