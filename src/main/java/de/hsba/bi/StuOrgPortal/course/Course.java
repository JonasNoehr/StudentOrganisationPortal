package de.hsba.bi.StuOrgPortal.course;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    @Getter
    @Id
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    private String name;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CourseEntry> entries;

    public List<CourseEntry> getEntries() {
        if (entries == null) {
            entries = new ArrayList<>();
        }
        return entries;
    }
}
