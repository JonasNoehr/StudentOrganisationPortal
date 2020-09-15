package de.hsba.bi.StuOrgPortal.course;

import de.hsba.bi.StuOrgPortal.user.User;
import de.hsba.bi.StuOrgPortal.user.UserService;
import de.hsba.bi.StuOrgPortal.web.course.CourseEntryController;
import de.hsba.bi.StuOrgPortal.web.course.CourseShowController;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.swing.plaf.ComponentUI;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @ManyToOne
    @Setter
    private User lecturer;

    @ManyToMany
    private Set<User> participants;

    // Todo muss größer als 0 sein
    private Integer maxParticipants;

    private String roomNumber;

    private BigDecimal courseAverage;

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
        return getParticipants().stream().sorted().map(User::getName).collect(Collectors.joining(", "));
    }

    public boolean isParticipant(User user) {
        return this.participants != null && this.getParticipants().contains(user);
    }
}
