package de.hsba.bi.StuOrgPortal.course;

import de.hsba.bi.StuOrgPortal.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CourseEntry {

    public Integer next;
    public Integer nextUser;

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(optional = false)
    private Course course;

    private String courseName;

    private String courseDescription;

    @ManyToOne
    @Setter
    private User lecturer;

    @ManyToMany
    @OrderBy
    private Set<User> participants;

    // Todo muss größer als 0 sein
    private Integer maxParticipants;

    private String roomNumber;

    private BigDecimal courseAverage;

    @Setter
    private boolean courseGradesSet;

    @Setter
    private boolean courseAssessmentsSet;

    @Setter
    private Double assessmentAverage;

    @Setter
    @Getter
    private Double gradeAverage;

    public CourseEntry(String courseName, String courseDescription, User lecturer, Integer maxParticipants, String roomNumber) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.lecturer = lecturer;
        this.maxParticipants = maxParticipants;
        this.roomNumber = roomNumber;
    }

    public Set<User> getParticipants() {
        if (participants == null) {
            participants = new HashSet<>();
        }
        return participants;
    }

    public String getParticipantNames() {
        String participantName = new String();
        User[] participantNames = participants.stream().sorted().toArray(User[]::new);
        for (int i = next; i<participantNames.length;) {
            participantName = participantNames[i].toString();
            next = next +1;
            return participantName;
        }
        return participantName;
    }

    public User getUserParticipant() {
        User userParticipant;
        User[] userParticipants = participants.stream().sorted().toArray(User[]::new);
        for (int i = nextUser; i < userParticipants.length; ) {
            userParticipant = userParticipants[i];
            nextUser = nextUser + 1;
            return userParticipant;
        }
        return null;
    }

    public Double getGradeAverage(){
        return this.gradeAverage;
    }

    public boolean isParticipant(User user) {
        return this.participants != null && this.getParticipants().contains(user);
    }

    public boolean isGreaterMaxParticipants() {
        if (this.getParticipants().size()+1 > this.maxParticipants) {
            return true;
        }
        return false;
    }

    public boolean isCourseGradesSet() {
        return this.courseGradesSet;
    }

    public boolean isCourseAssessmentsSet() {
        return this.courseAssessmentsSet;
    }
}
