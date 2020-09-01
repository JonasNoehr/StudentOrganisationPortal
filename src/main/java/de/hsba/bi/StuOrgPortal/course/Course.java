package de.hsba.bi.StuOrgPortal.course;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*
import org.springframework.core.annotation.Order;
import de.hsba.bi.StuOrgPortal.user.User;

 */
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    @Getter
    @Id
    @GeneratedValue
    private Long id;

    @Basic(optional = false)
    @Getter
    @Setter
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    @OrderBy
    private List<CourseEntry> entries;
    /*
    public Course(User owner) {this.owner = owner;}
     */
    public List<CourseEntry> getEntries() {
        if (entries == null) {
            entries = new ArrayList<>();
        }
        return entries;
    }

    /*
    public String setName(String nameSet) {
        this.name = nameSet;
        return name;
    }

     */
    /*
    public boolean isOwnedByCurrentUser() {
        return this.owner != null && this.owner.getName().equals(User.getCurrentUsername);
    }

     */
}
