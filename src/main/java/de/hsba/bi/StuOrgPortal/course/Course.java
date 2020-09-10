package de.hsba.bi.StuOrgPortal.course;

import de.hsba.bi.StuOrgPortal.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
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

    @ManyToOne(optional = false)
    @Getter
    private User owner;

    @OneToMany(mappedBy = "course", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CourseEntry> entries;

    public Course(User owner) {
        this.owner = owner;
    }

    public List<CourseEntry> getEntries() {
        if (entries == null) {
            entries = new ArrayList<>();
        }
        return entries;
    }

    public boolean isOwnedByCurrentUser() {
        return this.owner != null && this.owner.getName().equals(User.getCurrentUsername());
    }
}
