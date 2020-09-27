package de.hsba.bi.StuOrgPortal.user;

import de.hsba.bi.StuOrgPortal.Validation.ValidPassword;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
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
    @Email
    @NonNull
    private String email;

    @Getter
    @Setter
    private String role;




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
                .append("]");
        return builder.toString();
    }
}
