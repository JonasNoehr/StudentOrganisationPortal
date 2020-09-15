package de.hsba.bi.StuOrgPortal.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void init() {
        createUser("Anne", "123456", User.STUDENT_ROLE);
        createUser("Benedikt", "123456", User.STUDENT_ROLE);
        createUser("Charlotte", "123456", User.STUDENT_ROLE);
        createUser("Xenia", "123456", User.STUDENT_ROLE);
        createUser("Yves", "123456", User.STUDENT_ROLE);
        createUser("Zoe", "123456", User.LECTURER_ROLE);
        createUser("admin", "password", User.ADMIN_ROLE);
    }

    private void createUser(String name, String password, String role) {
        userRepository.save(new User(name, passwordEncoder.encode(password), role));
    }
    public void createNewLecturerUser(String name, String password) {
        userRepository.save(new User(name, passwordEncoder.encode(password), User.LECTURER_ROLE));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findCurrentUser() {
        return userRepository.findByName(User.getCurrentUsername());
    }
}
