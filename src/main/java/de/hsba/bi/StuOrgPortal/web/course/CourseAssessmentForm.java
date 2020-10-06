package de.hsba.bi.StuOrgPortal.web.course;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CourseAssessmentForm {

    @NotNull(message = "Bitte eine Bewertung eingeben")
    @DecimalMin(value = "1.0", message = "Es muss eine Bewertung von 1 sichergestellt sein.")
    @DecimalMax(value = "5.0", message = "Es darf maximal eine Bewertung von 5 vergeben werden.")
    private Double assessment;

}
