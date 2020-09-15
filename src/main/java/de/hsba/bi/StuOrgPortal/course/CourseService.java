package de.hsba.bi.StuOrgPortal.course;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.cglib.core.Predicate;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import de.hsba.bi.StuOrgPortal.user.User;
import de.hsba.bi.StuOrgPortal.user.UserService;
import org.thymeleaf.expression.Lists;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;
    private final CourseEntryRepository entryRepository;
    private final UserService userService;

    @EventListener(ApplicationStartedEvent.class)
    public void init() {
        if (!userService.findAll().isEmpty()) {
            // prevent initialisation if DB is not empty
            return;
        }
        userService.init();
        List<User> users = userService.findAll();
        User zoe = users.get(5);

        Course course = new Course(zoe);
        course.setName("Mathe");
        course.setStatus(Course.POSTED_STATUS);
        addCourseEntry(course, new CourseEntry("Mathematik", "Mathe für WI", zoe, 20, "R201"));

        repository.save(course);
    }

    public Course createCourse(String name, User currentUser) {
        Course course = new Course(currentUser);
        course.setName(name);
        course.setStatus(Course.DRAFT_STATUS);
        return repository.save(course);
    }

    public Course getCourse(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void addCourseEntry(Course course, CourseEntry entry) {
        entry.setCourse(course);
        entry.setLecturer(course.getOwner());
        course.getEntries().add(entry);
        repository.save(course);
    }

    public void addParticipant(CourseEntry entry, User user) {
        entry.getParticipants().add(user);
        entryRepository.save(entry);
    }

    public void removeParticipant(CourseEntry entry, User user) {
        entry.getParticipants().remove(user);
        entryRepository.save(entry);
    }

    public void postCourseEntry(Course course) {
        course.setStatus(Course.POSTED_STATUS);
        repository.save(course);
    }

    public void startCourseEntry(Course course) {
        course.setStatus(Course.STARTED_STATUS);
        repository.save(course);
    }

    public Collection<Course> getAll() {return repository.findAll();}

    public void delete(Long id) { repository.deleteById(id);}

    public CourseEntry findEntry(Long id) {
        return entryRepository.findById(id).orElse(null);
    }
}
