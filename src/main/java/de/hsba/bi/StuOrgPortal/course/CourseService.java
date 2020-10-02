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
    private final CourseGradeRepository gradeRepository;
    private final UserService userService;
    private final CourseAssessmentRepository assessmentRepository;

    @EventListener(ApplicationStartedEvent.class)
    public void init() {
        if (!userService.findAll().isEmpty()) {
            // prevent initialisation if DB is not empty
            return;
        }
        userService.init();
        List<User> users = userService.findAll();
        User zoe = users.get(5);
        User anne = users.get(0);
        User benedikt = users.get(1);

        Course course = new Course(zoe);
        course.setName("Mathe");
        course.setStatus(Course.POSTED_STATUS);
        CourseEntry entry = new CourseEntry("Mathematik", "Mathe für WI", zoe, 20, "R201");
        addCourseEntry(course, entry);
        addParticipant(entry, anne);
        addParticipant(entry, benedikt);

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

    public void endCourseEntry(Course course) {
        course.setStatus(Course.ENDED_STATUS);
        repository.save(course);
    }

    public void setCourseGrades(CourseEntry entry) {
        entry.nextUser = 0;
        for (int i = 0; i<entry.getParticipants().size();i++) {
            CourseGrade grade = new CourseGrade();
            grade.setCourseEntry(entry);
            grade.setUser(entry.getUserParticipant());
            grade.setCourseName(entry.toString());
            gradeRepository.save(grade);
        }
        entry.setCourseGradesSet(true);
        entryRepository.save(entry);
    }

    public void setUserGrade(CourseGrade courseGrade, Double grade) {
        courseGrade.setGrade(grade);
        gradeRepository.save(courseGrade);
    }

    public void setCourseAssessments(CourseEntry entry) {
        entry.nextUser = 0;
        for (int i = 0; i<entry.getParticipants().size();i++) {
            CourseAssessment assessment = new CourseAssessment();
            assessment.setCourseEntry(entry);
            assessment.setUser(entry.getUserParticipant());
            assessment.setCourseName(entry.toString());
            assessmentRepository.save(assessment);
        }
        entry.setCourseAssessmentsSet(true);
        entryRepository.save(entry);
    }

    public void setCourseAssessment(CourseAssessment courseAssessment, Double assessment) {
        courseAssessment.setAssessment(assessment);
        assessmentRepository.save(courseAssessment);
    }

    public void setCourseAssessmentsSet(CourseAssessment courseAssessment) {
        courseAssessment.setAssessmentSet(true);
        assessmentRepository.save(courseAssessment);
    }

    public void setAssessmentAverage(CourseEntry entry) {
        CourseAssessment[] assessmentArray = findAssessmentByEntryId(entry).toArray(CourseAssessment[]::new);
        CourseAssessment courseAssessment;
        Integer qtyUserWithAssessment = 0;
        Double userAssessments = 0.0;
        for (int i = 0; i<assessmentArray.length;i++) {
            courseAssessment = assessmentArray[i];
            if (courseAssessment.getAssessment() != null) {
                qtyUserWithAssessment++;
                userAssessments = userAssessments + courseAssessment.getAssessment();
            }
        }
        entry.setAssessmentAverage(userAssessments/qtyUserWithAssessment);
        entryRepository.save(entry);
    }

    public void setGradeAverage(CourseEntry entry) {
        CourseGrade[] gradeArray = findByEntryId(entry).toArray(CourseGrade[]::new);
        CourseGrade courseGrade;
        Integer qtyUserWithGrade = 0;
        Double userGrades = 0.0;
        for (int i = 0; i<gradeArray.length;i++) {
            courseGrade = gradeArray[i];
            if (courseGrade.getGrade() != null) {
                qtyUserWithGrade++;
                userGrades = userGrades + courseGrade.getGrade();
            }
        }
        entry.setGradeAverage(userGrades/qtyUserWithGrade);
        entryRepository.save(entry);
    }

    public Collection<Course> getAll() {return repository.findAll();}

    public Collection<CourseEntry> getAllEntries() {return entryRepository.findAll();}

    public void delete(Long id) { repository.deleteById(id);}

    public CourseEntry findEntry(Long id) {
        return entryRepository.findById(id).orElse(null);
    }

    public List<CourseGrade> findByEntryId(CourseEntry entry) {
        return gradeRepository.findByCourseEntry(entry);
    }

    public List<CourseAssessment> findAssessmentByEntryId(CourseEntry entry) {
        return assessmentRepository.findByCourseEntry(entry);
    }

    public CourseGrade findByEntryAndUser(CourseEntry entry, User user) {
        return gradeRepository.findByCourseEntryAndAndUser(entry, user);
    }

    public List<Course> findCourses(String filter) {
        return filter.isBlank() ? repository.findAll() : repository.findByStatus(filter);
    }

    public CourseAssessment findAssessmentByEntryAndUser(CourseEntry entry, User user) {
        return assessmentRepository.findByCourseEntryAndAndUser(entry, user);
    }
}
