package de.hsba.bi.StuOrgPortal.user;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/*
  Bereitstellung von Methoden und initialem Admin
 */
/*
@RequiredArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void init() {
        createUser("Anne", "123456", User.STUDENT_ROLE);
        createUser("Peter", "123456", User.STUDENT_ROLE);
        createUser("Heiz", "123456", User.STUDENT_ROLE);
        createUser("Lisa", "123456", User.STUDENT_ROLE);
        createUser("Dieter", "123456", User.LECTURER_ROLE);
        createUser("Sönke", "123456", User.LECTURER_ROLE);
        createUser("Admin", "password", User.ADMIN_ROLE);
    }

    private void createUser (String name, String password, String role) {
        userRepository.save(new User(name, passwordEncoder.encode(password), role));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findUsers() {
        return userRepository.findByRole(User.STUDENT_ROLE);
    }

    public User findCurrentUser() {
        return userRepository.findByName(User.getCurrentUsername());
    }
}
*/
