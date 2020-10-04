package de.hsba.bi.StuOrgPortal.user;

import de.hsba.bi.StuOrgPortal.Validation.ValidPassword;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Setter
@Getter
@Entity(name ="Users")
public class User implements UserDetails {

    public static String STUDENT_ROLE = "STUDENT";
    public static String LECTURER_ROLE = "LECTURER";
    public static String ADMIN_ROLE = "ADMIN";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Size(min = 1, message = "Bitte Vornamen angeben")
    private String firstName;

    @NotNull
    @Size(min = 1, message = "Bitte Nachnamen angeben")
    private String lastName;

    @NotNull
    @ValidPassword
    private String password;

    @NotNull
    @ValidPassword
    private String matchingPassword;

    @Email
    @NotNull
    private String email;

    private String role;

    public User(int id, String email, String firstName, String lastName, String password, String role) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

    public User() {


    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [id=")
                .append(id)
                .append(", firstName=").append(firstName)
                .append(", lastName=").append(lastName)
                .append(", email=").append(email)
                .append(", role=").append(role)
                .append(", password=").append(password)
                .append(", matchingPassword=").append(matchingPassword)
                .append("]");
        return builder.toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
