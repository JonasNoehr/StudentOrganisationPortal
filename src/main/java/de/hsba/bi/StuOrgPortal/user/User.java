package de.hsba.bi.StuOrgPortal.user;

import de.hsba.bi.StuOrgPortal.Validation.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@Entity
public class User {

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

}
