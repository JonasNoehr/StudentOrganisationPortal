package de.hsba.bi.StuOrgPortal.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserAdapter userAdapter;

    @EventListener(ApplicationStartedEvent.class)
    public User adminOnAppStart() {

        final User user = new User();

        user.setFirstName("admin");
        user.setLastName("admin");
        user.setPassword("password");
        user.setEmail("admin@stuorgportal.de");
        user.setRole(User.ADMIN_ROLE);
        return userRepository.save(user);
    }


    public User createNewStudent(int id, String email, String firstName, String lastName, String password, String role) {
       /* if (emailExists(User.getEmail())) {
            throw new UserAlreadyExistException("Es existiert bereits ein Account mit dieser E-Mail Adresse: " + User.getEmail());
        } */

        final User user = new User();

        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setRole(User.STUDENT_ROLE);

        return userRepository.save(new User());
    }

    /*
    @Override
    public User createNewLecturer(final UserDto accountDto) {
        if (emailExists(accountDto.getEmail())) {
            throw new UserAlreadyExistException("Es existiert bereits ein Account mit dieser E-Mail Adresse: " + accountDto.getEmail());
        }
        final User user = new User();

        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setRole("LECTURER");
        return userRepository.save(user);
    }
    */

    private boolean emailExists(String email) {
        return userRepository.findByName(email) != null;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findStudents() {
        return userRepository.findByRole(User.STUDENT_ROLE);
    }

    public User findCurrentUser() {

        return userRepository.findByName(userAdapter.getUsername());
    }

    public User findByName(final String email)    {
        return userRepository.findByName(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return null;
    }



}
