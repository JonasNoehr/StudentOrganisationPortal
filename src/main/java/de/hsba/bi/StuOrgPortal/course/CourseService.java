/* package de.hsba.bi.StuOrgPortal.course;

import de.hsba.bi.StuOrgPortal.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;
    private final UserService userService;

    public Course createCourse(String name) {
        Course course = new Course();
        course.setName(name);
        return repository.save(course);
    }

    public Course getCourse(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void addCourseEntry(Course course, CourseEntry entry) {
        entry.setCourse(course);
        course.getEntries().add(entry);
        repository.save(course);
    }

    public Collection<Course> getAll() {return repository.findAll();}

    public void delete(Long id) { repository.deleteById(id);}
} */
