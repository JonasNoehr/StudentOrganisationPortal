package de.hsba.bi.StuOrgPortal.web.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class UserForm {

    // @NotBlank(message = "Bitte geben Sie einen Namen an")
    @Size.List({
            @Size(min = 3, message = "Bitte geben Sie mindestens 3 Zeichen ein"),
            @Size(max = 255, message = "Der Name darf nicht länger als 255 Zeichen sein")
    })
    private String name;

    @Size.List({
            @Size(min = 3, message = "Bitte geben Sie mindestens 3 Zeichen ein"),
            @Size(max = 255, message = "Das Passwort darf nicht länger als 255 Zeichen sein")
    })
    private String password;
}
