package de.hsba.bi.StuOrgPortal.course;

/*
import de.hsba.bi.StuOrgPortal.user.UserService;

 */
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }
    //private final CourseEntryRepository entryRepository;
    //private final UserService userService;

    @EventListener(ApplicationStartedEvent.class)
    public void init() {
        /*
        userService.init();
        List<User> users = userService.findAll();
        User anne = users.get(0);
        User benedikt = users.get(1);
        User charlotte = users.get(2);

         */

        // add example Course for testing
        Course course = new Course();
        //course.setName("Mathe");
        addCourseEntry(course, new CourseEntry("Mathe", "Mathe für WI",  "Open", 252, 25, 2.3,4.6,"Presentation", 0.5, 10));
        //course.getEntries().add(new CourseEntry("Mathe", "Mathe",  "Open", 252, 25, 2.3,4.6,"Presentation", 0.5, 10));

        repository.save(course);
    }
    public Course createCourse(String name) {
        Course course = new Course();
        //course.setName(name);
        return repository.save(course);
    }

    /*
    public Course save(Course course) {
        return repository.save(course);
    }

     */

    public Course getCourse(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void addCourseEntry(Course course, CourseEntry entry) {
        //entry.setCourse(course);
        course.getEntries().add(entry);
    }

    public List<Course> findCourses(String search) {
        //User user = userService.findCurrentUser();
        //return search.isBlank() ? repository.findByOwner(User) : repository.findByEntryDescription(search, user);
        return repository.findByEntryDescription(search);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    /*
    public CourseEntry findEntry(Long id) {
        return repository.findById(id).orElse(null);
    }

    public CourseEntry save(Course course) {
        return entryRepository.save(entry);
    }

     */
}
