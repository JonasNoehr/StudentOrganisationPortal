package de.hsba.bi.StuOrgPortal.web.course;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class CourseGradeForm {

    @NotNull(message = "Bitte eine Note eingeben")
    @DecimalMin(value = "1.0", message = "Es muss eine Note von 1.0 sichergestellt sein.")
    @DecimalMax(value = "5.0", message = "Es darf maximal eine Note von 5.0 vergeben werden.")
    private Double grade;

}
