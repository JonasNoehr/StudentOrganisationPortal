package de.hsba.bi.StuOrgPortal.course;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CourseEntry {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    private Long lecturerId;

    private Long studentId;

    @ManyToOne(optional = false)
    private Course course;

    private String title;

    private String description;

    private String status;

    private Integer roomNr;

    private Integer maxParticipants;

    private Double averageGrade;

    private Double averageRating;

    private String examType;

    private Double duration;

    private Integer weeklyHours;

    public CourseEntry(String title, String description, String status, Integer roomNr, Integer maxParticipants, Double averageGrade, Double averageRating, String examType, Double duration, Integer weeklyHours) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.roomNr = roomNr;
        this.maxParticipants = maxParticipants;
        this.averageGrade = averageGrade;
        this.averageRating = averageGrade;
        this.examType = examType;
        this.duration = duration;
        this.weeklyHours = weeklyHours;
    }
}
