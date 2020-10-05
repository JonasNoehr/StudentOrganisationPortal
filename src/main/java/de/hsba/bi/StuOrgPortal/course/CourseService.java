package de.hsba.bi.StuOrgPortal.course;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.cglib.core.Predicate;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

        // Anlegen Testdaten beim Erststart
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
        course.setCourseEntrySet(true);
        repository.save(course);

        course = new Course(zoe);
        course.setName("Deutsch");
        course.setStatus(Course.STARTED_STATUS);
        entry = new CourseEntry("Deutsch", "Deutsch BWL", zoe, 25, "R102");
        addCourseEntry(course, entry);
        addParticipant(entry, anne);
        addParticipant(entry, benedikt);
        course.setCourseEntrySet(true);
        repository.save(course);

        course = new Course(zoe);
        course.setName("Deutsch 2");
        course.setStatus(Course.STARTED_STATUS);
        entry = new CourseEntry("Deutsch 2", "Deutsch", zoe, 25, "R302");
        addCourseEntry(course, entry);
        addParticipant(entry, anne);
        addParticipant(entry, benedikt);
        course.setCourseEntrySet(true);
        repository.save(course);

        course = new Course(zoe);
        course.setName("Englisch");
        course.setStatus(Course.DRAFT_STATUS);
        entry = new CourseEntry("Englisch", "Englisch BWL", zoe, 15, "R602");
        addCourseEntry(course, entry);
        addParticipant(entry, anne);
        addParticipant(entry, benedikt);
        course.setCourseEntrySet(true);
        repository.save(course);

        course = new Course(zoe);
        course.setName("Englisch 2");
        course.setStatus(Course.DRAFT_STATUS);
        entry = new CourseEntry("Englisch 2", "Englisch", zoe, 15, "R502");
        addCourseEntry(course, entry);
        addParticipant(entry, anne);
        addParticipant(entry, benedikt);
        course.setCourseEntrySet(true);
        repository.save(course);

        course = new Course(zoe);
        course.setName("Programmierung");
        course.setStatus(Course.ENDED_STATUS);
        entry = new CourseEntry("Programmierung", "Programmierung WI", zoe, 15, "R602");
        addCourseEntry(course, entry);
        addParticipant(entry, anne);
        addParticipant(entry, benedikt);
        setCourseGrades(entry);
        setCourseAssessments(entry);
        course.setCourseEntrySet(true);
        repository.save(course);
    }

    // Anlegen neuer Kurs, er ist dann im Status Entwurf
    public Course createCourse(Course course) {
        course.setStatus(Course.DRAFT_STATUS);
        return repository.save(course);
    }

    // Kurs Eintrag erstellen, der Dozent der den Kurs angelegt hat, wird als "owner" eingetragen
    public void addCourseEntry(Course course, CourseEntry entry) {
        entry.setCourse(course);
        entry.setLecturer(course.getOwner());
        course.getEntries().add(entry);
        course.setCourseEntrySet(true);
        repository.save(course);
    }

    // Änderungen an einem Kurs Eintrag werden gespeichert
    public void changeCourseEntry(CourseEntry entry) {
        entryRepository.save(entry);
    }

    // EIn Teilnehmer wird zum Kurs hinzugefügt
    public void addParticipant(CourseEntry entry, User user) {
        entry.getParticipants().add(user);
        entryRepository.save(entry);
    }

    // Ein Teilnehmer wird vom Kurs wieder entfernt
    public void removeParticipant(CourseEntry entry, User user) {
        entry.getParticipants().remove(user);
        entryRepository.save(entry);
    }

    // Status des Kurses auf Freigegeben gesetzt
    public void postCourseEntry(Course course) {
        course.setStatus(Course.POSTED_STATUS);
        repository.save(course);
    }

    // Status des Kurses auf Gestartet gesetzt
    public void startCourseEntry(Course course) {
        course.setStatus(Course.STARTED_STATUS);
        repository.save(course);
    }

    // Status des Kurses auf Beendet gesetzt
    public void endCourseEntry(Course course) {
        course.setStatus(Course.ENDED_STATUS);
        repository.save(course);
    }

    // Nachdem der Kurs beendet wurde und kein Teilnehmer mehr hinzugefügt oder entfernt werden kann,
    // wird für jeden Teilnehmer ein Eintrag in der Kurs Note erstellt
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

    // Note für den Studenten wird eingetragen
    public void setUserGrade(CourseGrade courseGrade, Double grade) {
        courseGrade.setGrade(grade);
        gradeRepository.save(courseGrade);
    }

    // Nachdem der Kurs beendet wurde und kein Teilnehmer mehr hinzugefügt oder entfernt werden kann,
    // wird für jeden Teilnehmer ein Eintrag in der Kurs Bewertung erstellt
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

    // Die Bewertung wird gespeichert
    public void setCourseAssessment(CourseAssessment courseAssessment, Double assessment) {
        courseAssessment.setAssessment(assessment);
        assessmentRepository.save(courseAssessment);
    }

    // Boolean um später zu prüfen, ob "setCourseAssessments" bereits durchlaufen wurde
    public void setCourseAssessmentsSet(CourseAssessment courseAssessment) {
        courseAssessment.setAssessmentSet(true);
        assessmentRepository.save(courseAssessment);
    }

    // Der Schnitt der Kursbewertung wird bei jeder neuen Bewertung neu berechnet und gespeichert
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

    // Der Notendurchschnitt wird bei jeder neuen Notenvergabe neu berechnet und gespeichert
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

    // Kurs über id suchen
    public Course getCourse(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Alle Kurse finden
    public Collection<Course> getAll() {return repository.findAll();}

    // Alle Kurs EInträge finden
    public Collection<CourseEntry> getAllEntries() {return entryRepository.findAll();}

    // Kurs löschen, über id
    public void delete(Long id) { repository.deleteById(id);}

    // Kurs EIntrag über id finden
    public CourseEntry findEntry(Long id) {
        return entryRepository.findById(id).orElse(null);
    }

    // Alle Noten eines Kurses finden
    public List<CourseGrade> findByEntryId(CourseEntry entry) {
        return gradeRepository.findByCourseEntry(entry);
    }

    // Alle Bewertungen eines Kurses finden
    public List<CourseAssessment> findAssessmentByEntryId(CourseEntry entry) {
        return assessmentRepository.findByCourseEntry(entry);
    }

    // Eine Note für einen bestimmten User eines bestimmten Kurses finden
    public CourseGrade findByEntryAndUser(CourseEntry entry, User user) {
        return gradeRepository.findByCourseEntryAndAndUser(entry, user);
    }

    // Kurs EIntrag über Kurs id finden
    public CourseEntry findCourseEntryByCourseId(Long courseId) {
        return entryRepository.findByCourseId(courseId);
    }

    // Kurse über Status filter finden
    public List<Course> findCourses(String filter) {
        return filter.isBlank() ? repository.findAll() : repository.findByStatus(filter);
    }

    // Bewertung eines bestimmten users und eines bestimmten Kurses finden
    public CourseAssessment findAssessmentByEntryAndUser(CourseEntry entry, User user) {
        return assessmentRepository.findByCourseEntryAndAndUser(entry, user);
    }
}
