package de.hsba.bi.StuOrgPortal.course;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import de.hsba.bi.StuOrgPortal.user.User;
import de.hsba.bi.StuOrgPortal.user.UserService;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;
    private final UserService userService;

    @EventListener(ApplicationStartedEvent.class)
    public void init() {
        if (!userService.findAll().isEmpty()) {
            // prevent initialisation if DB is not empty
            return;
        }
        userService.init();
        List<User> users = userService.findAll();
        User anne = users.get(0);
        User benedikt = users.get(1);
        User charlotte = users.get(2);

        Course course = new Course();
        course.setName("Mathe");
        addCourseEntry(course, new CourseEntry("Mathematik", "Mathe für WI", 20, "R201"));

        repository.save(course);
    }

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
}
