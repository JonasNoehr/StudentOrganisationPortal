package de.hsba.bi.StuOrgPortal.course;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CourseEntry {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(optional = false)
    private Course course;

    private String courseName;

    private String courseDescription;

    // TODO Status

    // TODO muss größer als 0 sein
    private Integer maxParticipants;

    private String roomNumber;

    private BigDecimal courseAverage;

    public CourseEntry(String courseName, String courseDescription, Integer maxParticipants, String roomNumber) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.maxParticipants = maxParticipants;
        this.roomNumber = roomNumber;
    }
}
