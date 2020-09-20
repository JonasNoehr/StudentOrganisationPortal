package de.hsba.bi.StuOrgPortal.user;

import de.hsba.bi.StuOrgPortal.Validation.PasswordMatches;
import de.hsba.bi.StuOrgPortal.Validation.ValidPassword;
import lombok.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@Builder
@Entity
@PasswordMatches

public class User {

    public static String STUDENT_ROLE = "STUDENT";
    public static String LECTURER_ROLE = "LECTURER";
    public static String ADMIN_ROLE = "ADMIN";

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Getter
    @Setter
    @NonNull
    @Size(min = 1, message = "{Size.user.firstName}")
    private String firstName;

    @Getter
    @Setter
    @NonNull
    @Size(min = 1, message = "{Size.user.lastName}")
    private String lastName;

    @Getter
    @Setter
    @ValidPassword
    private String password;

    @Getter
    @Setter
    @NonNull
    @Size(min = 1)
    private String matchingPassword;

    @Getter
    @Setter
    @Email
    @NonNull
    private String email;

    @Getter
    @Setter
    private String role;



    public static String getCurrentEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return null;
    }

    public User(int id, String firstName, String lastName, String encode, String encode1, String email, String role) {
    }

    public User() {

    }


    /* @Override
    public int compareTo(User other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name;
    } */
}
