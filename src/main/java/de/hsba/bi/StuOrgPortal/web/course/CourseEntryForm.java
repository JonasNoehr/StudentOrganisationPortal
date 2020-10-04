package de.hsba.bi.StuOrgPortal.web.course;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class CourseEntryForm {

    @Size.List({
            @Size(min = 3, message = "Bitte geben Sie mindestens 3 Zeichen ein"),
            @Size(max = 255, message = "Der Name darf nicht länger als 255 Zeichen sein")
    })
    private String courseName;

    @NotEmpty(message = "Bitte eine Beschreibung eingeben")
    private String courseDescription;

    @NotNull(message = "Bitte eine Teilnehmerzahl eingeben")
    @Min(value = 1, message = "Es muss eine Mindestanzahl von 1 sichergestellt sein.")
    @Max(value = 35, message = "Es dürfen maximal 35 Teilnehmer teilnehmen.")
    private Integer maxParticipants;

    @NotEmpty(message = "Bitte eine Raum Nummer eingeben")
    private String roomNumber;
}
