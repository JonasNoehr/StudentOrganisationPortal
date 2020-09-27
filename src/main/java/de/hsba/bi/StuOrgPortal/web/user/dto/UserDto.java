package de.hsba.bi.StuOrgPortal.web.user.dto;

import de.hsba.bi.StuOrgPortal.Validation.PasswordMatches;
import de.hsba.bi.StuOrgPortal.Validation.ValidPassword;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@PasswordMatches
@Getter
@Setter
public class UserDto<setRole> {

    @Getter
    @Setter
    @NonNull
    @Size(min = 1)
    private String firstName;

    @Getter
    @Setter
    @NonNull
    @Size(min = 1)
    private String lastName;

    @Getter
    @Setter
    @ValidPassword
    private String password;

    @NonNull
    @Size(min = 1)
    private String matchingPassword;

    @Getter
    @Setter
    @Email
    @NonNull
    private String email;



}
