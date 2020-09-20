package de.hsba.bi.StuOrgPortal.user;

import de.hsba.bi.StuOrgPortal.web.error.UserAlreadyExists;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional

public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;


    @EventListener
    public void init() {
        createNewUser(1,"admin", "admin", "password", "admin@stuorgportal.de", User.ADMIN_ROLE);
    }

    @Override
    public User createNewUser(final User accountDto) {
        if (emailExists(accountDto.getEmail())) {
            throw new UserAlreadyExists("Es existiert bereits ein Account mit dieser E-Mail Adresse: " + accountDto.getEmail());
        }
        final User user = new User();

        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setRole(accountDto.getRole());
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findStudents() {
        return userRepository.findByRole(User.STUDENT_ROLE);
    }

    public User findCurrentUser() {
        return userRepository.findByName(User.getCurrentEmail());
    }

    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }

}
