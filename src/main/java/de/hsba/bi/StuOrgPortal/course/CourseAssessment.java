package de.hsba.bi.StuOrgPortal.course;

import de.hsba.bi.StuOrgPortal.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CourseAssessment {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne
    @Setter
    private User user;

    private String userName;

    @ManyToOne
    @Setter
    private CourseEntry courseEntry;

    @Setter
    private String courseName;

    @Setter
    @Getter
    private Double assessment;

    @Setter
    private boolean assessmentSet;

    public boolean isAssessmentSet() {
        return this.assessmentSet;
    }

    public Double getAssessment() {
        return this.assessment;
    }

    public void averageHandling(Integer qtyUser, Integer assessments) {

    }

}
