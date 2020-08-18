package de.hsba.bi.StuOrgPortal.courses;

import de.hsba.bi.StuOrgPortal.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
public class Course {

    @GeneratedValue
    @Id
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @Basic(optional = false)
    @Column(unique = true)
    private String name;

    @Getter
    @Setter
    @Lob
    private String description;


    @Getter
    @Setter
    private Integer duration;

    @Getter
    @Setter
    private Integer maxStudents;


    @ManyToMany(mappedBy = "courses")
    private List<User> users;

    public Course(String name, String description, Integer duration, Integer maxStudents) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.maxStudents = maxStudents;
    }

}

    // um die courses wieder eigenständig von den user löschen zu können? getCourses fehlt dafür noch bei USER

    //@PreRemove
   // private void removeCoursessFromUsers() {
   //     for (User u : users) {
    //        u.getCourses().remove(this);
        // }


