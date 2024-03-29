package de.hsba.bi.StuOrgPortal.course;

import de.hsba.bi.StuOrgPortal.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CourseGrade {

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
    private Double grade;

    public Double getGrade() {
        return this.grade;
    }

}
