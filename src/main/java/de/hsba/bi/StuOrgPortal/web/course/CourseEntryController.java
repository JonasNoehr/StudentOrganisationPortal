package de.hsba.bi.StuOrgPortal.web.course;

import de.hsba.bi.StuOrgPortal.course.CourseAssessment;
import de.hsba.bi.StuOrgPortal.course.CourseEntry;
import de.hsba.bi.StuOrgPortal.course.CourseService;
import de.hsba.bi.StuOrgPortal.user.User;
import de.hsba.bi.StuOrgPortal.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/entries/{id}")
@RequiredArgsConstructor
public class CourseEntryController {

    private final CourseService courseService;
    private final UserService userService;

    // Aufruf Kurs index
    @GetMapping
    public String index(Model model) {
        model.addAttribute("course", courseService.getAll());
        return "courses/index";
    }

    // hinzufügen eines neuen Teilnehmers in einem Kurs
    @PostMapping(path = "/participate")
    public String participate(@PathVariable("id") Long id) {
        CourseEntry entry = courseService.findEntry(id);
        User user = userService.findCurrentUser();
        // wenn maximal Teilnehmerzahl erreicht ist
        if (entry.isGreaterMaxParticipants()) {
            return "/messages/reachedMaxParticipants";
        }
        // wenn Teilnehmer bereits eingeschrieben ist
        if (entry.isParticipant(user)) {
            return "/messages/alreadySubscribed";
        }
        courseService.addParticipant(entry, user);
        return "/messages/successfulParticipation";
    }

    // Teilnehmer meldet sich vom Kurs ab
    @PostMapping(path = "/unsubscribe")
    public String unsubscribe(@PathVariable("id") Long id) {
        CourseEntry entry = courseService.findEntry(id);
        User user = userService.findCurrentUser();
        if (entry.isParticipant(user)) {
            courseService.removeParticipant(entry, user);
            return "/messages/successfulUnsubscription";
        }
        // wenn der Teilnehmer gar nicht im Kurs angemeldet war
        return "/messages/noSubscription";
    }

    // Anlegen Datensätze in Kurs Noten für jeden Teilnehmer
    @PostMapping(path = "/setGrades")
    public String setGrades(@PathVariable("id") Long id) {
        CourseEntry entry = courseService.findEntry(id);
        // wenn dies bereits geschehen ist, wird direkt auf die Notenvergabe Seite weitergeleitet
        if (entry.isCourseGradesSet()) {
            return "redirect:/entries/" + id +"/setUserGrades";
        }
        courseService.setCourseGrades(entry);
        return "redirect:/entries/" + id +"/setUserGrades";
    }

    // Anlegen Datensätze in Kurs Bewertung für jeden Teilnehmer
    @PostMapping(path = "/setAssessment")
    public String setAssessments(@PathVariable("id") Long id) {
        CourseEntry entry = courseService.findEntry(id);
        // wenn dies bereits geschehen ist, wird direkt auf die Bewertungsseite weitergeleitet
        if (entry.isCourseAssessmentsSet()) {
            return "redirect:/entries/" + id +"/setCourseAssessment";
        }
        courseService.setCourseAssessments(entry);
        return "redirect:/entries/" + id +"/setCourseAssessment";
    }

    // Aufruf der Notenvergabe Seite
    @GetMapping(path = "/setUserGrades")
    public String setUserGrades(@PathVariable("id") Long id, Model model) {
        CourseEntry entry = courseService.findEntry(id);
        model.addAttribute("entry", entry);
        model.addAttribute("grade", courseService.findByEntryId(entry));
        model.addAttribute("courseGradeForm", new CourseGradeForm());
        return "/courses/participantGrades";
    }

    // Abrufen der eigenen Noten eines bestimmten Teilnehmers
    @GetMapping(path = "/getMyGrades")
    public String MyGrades(@PathVariable("id") Long id, Model model) {
        CourseEntry entry = courseService.findEntry(id);
        User user = userService.findCurrentUser();
        model.addAttribute("entry", entry);
        model.addAttribute("grade", courseService.findByEntryAndUser(entry, user));
        return "/courses/MyGrades";
    }

    // AUfruf der Bewertungs Seite für den aktuellen User
    @GetMapping(path = "/setCourseAssessment")
    public String setCourseAssessment(@PathVariable("id") Long id, Model model) {
        CourseEntry entry = courseService.findEntry(id);
        User user = userService.findCurrentUser();
        CourseAssessment courseAssessment = courseService.findAssessmentByEntryAndUser(entry, user);
        // wenn der Teilnehmer den Kurs bereits bewertet hat
        if (courseAssessment.isAssessmentSet()) {
            return "/messages/AssessmentError";
        }
        courseService.setCourseAssessmentsSet(courseAssessment);
        model.addAttribute("entry", entry);
        model.addAttribute("assessment", courseAssessment);
        model.addAttribute("courseAssessmentForm", new CourseAssessmentForm());
        return "/courses/participantAssessment";
    }

    // gibt eine Liste aller Kursteilnehmer zurück
    @GetMapping(path = "/participants")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("course", courseService.findEntry(id));
        CourseEntry entry = courseService.findEntry(id);
        entry.next = 0;
        return "courses/courseParticipants";
    }
}
