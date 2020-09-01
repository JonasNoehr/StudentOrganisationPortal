package de.hsba.bi.StuOrgPortal.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.UserDetailsAwareConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Basic;
import javax.persistence.Entity;
/*
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    public static String ADMIN_ROLE = "ADMIN";
    public static String LECTURER_ROLE = "LECTURER";
    public static String STUDENT_ROLE = "STUDENT";

    public static String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return null;
    }

    private final String role;

    @Basic(optional = false)
    private String password;

    @Basic(optional = false)
    private String name;

    public User(String name) {
        this.name = name;
    }
    public User(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    @Override
    public int compareTo(User other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
*/