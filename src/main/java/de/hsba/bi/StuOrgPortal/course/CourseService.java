package de.hsba.bi.StuOrgPortal.course;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void init() {
        // add example Course for testing
        Course course = new Course();
        course.setName("Mathe");
        course.getEntries().add(new CourseEntry("Mathe", "Mathe für WI",  "Open", 252, 25, 2.3,4.6,"Presentation", 0.5, 10));
        course.getEntries().add(new CourseEntry("Mathe", "Mathe",  "Open", 252, 25, 2.3,4.6,"Presentation", 0.5, 10));

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
        course.getEntries().add(entry);
    }

    public Collection<Course> getAll() {
        return repository.findAll();
    }
}
