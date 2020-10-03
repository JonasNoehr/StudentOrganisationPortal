package de.hsba.bi.StuOrgPortal.web.user.dto;

import de.hsba.bi.StuOrgPortal.Validation.PasswordMatches;
import de.hsba.bi.StuOrgPortal.Validation.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordMatches
@Getter
@Setter
public class UserDto{

    @NotNull (message = "Bitte den Vornamen angeben")
    @Size(min = 1)
    private String firstName;

    @NotNull (message = "Bitte den Nachnamen angeben")
    @Size(min = 1)
    private String lastName;

    @NotNull (message = "Bitte ein Passwort eingeben")
    @ValidPassword
    private String password;

    @NotNull (message = "Bitte den Passwort angeben")
    @Size(min = 1)
    private String matchingPassword;

    @Email
    @NotNull (message = "Bitte die E-Mail Adresse angeben")
    private String email;

    public UserDto() {
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", matchingPassword='" + matchingPassword + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
