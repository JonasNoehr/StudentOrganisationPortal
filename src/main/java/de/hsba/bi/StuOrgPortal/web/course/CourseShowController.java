package de.hsba.bi.StuOrgPortal.web.course;

import de.hsba.bi.StuOrgPortal.ForbiddenException;
import de.hsba.bi.StuOrgPortal.NotFoundException;
import de.hsba.bi.StuOrgPortal.course.Course;
import de.hsba.bi.StuOrgPortal.course.CourseEntry;
import de.hsba.bi.StuOrgPortal.course.CourseService;
import de.hsba.bi.StuOrgPortal.user.User;
import de.hsba.bi.StuOrgPortal.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/courses/{id}")
@RequiredArgsConstructor
public class CourseShowController {

    private final CourseService courseService;
    private final CourseFormConverter formConverter;
    private final UserService userService;

    @ModelAttribute("course")
    public Course getCourse(@PathVariable("id") Long id) {
        Course course = courseService.getCourse(id);
        if (course == null) {
            throw new NotFoundException();
        }
        return course;
    }

    @ExceptionHandler(NotFoundException.class)
    public String notFound() {
        return "messages/notFound";
    }

    // Kurs Seite wird aufgerufen
    @GetMapping
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("course", courseService.getCourse(id));
        model.addAttribute("user", userService.findCurrentUser());
        return "courses/show";
    }

    // Kurs Entwurf wird aufgerufen
    @GetMapping(path = "/draft")
    public String showDraft(@PathVariable("id") Long id, Model model) {
        model.addAttribute("course", courseService.getCourse(id));
        model.addAttribute("courseEntryForm", new CourseEntryForm());
        return "courses/showDraft";
    }

    // Kurs Eintrag wird angelegt, falls schon passiert kann dieser aktualisiert werden
    @PostMapping
    public String addEntry(@PathVariable("id") Long id, @ModelAttribute("courseEntryForm") @Valid CourseEntryForm entryForm, BindingResult entryBindingResult) {
        // wenn Form fehlerhaft ist
        if (entryBindingResult.hasErrors()) {
            return "courses/showDraft";
        }
        Course course = courseService.getCourse(id);
        // wenn kein EIntrag vorhanden ist wird einer angelegt
        if (!course.isCourseEntrySet()) {
            courseService.addCourseEntry(course, formConverter.update(new CourseEntry(), entryForm));
        } else {
            // wenn EIntrag vorhanden ist kann dieser aktualisiert werden
            CourseEntry courseEntry = courseService.findCourseEntryByCourseId(id);
            courseService.changeCourseEntry(formConverter.update(courseEntry, entryForm));
        }
        return "redirect:/courses/" + id + "/draft";
    }

    // Kurs wird auf Status freigegeben gesetzt
    @PostMapping(path = "/post")
    public String post(@PathVariable("id") Long id) {
        Course course = courseService.getCourse(id);
        courseService.postCourseEntry(course);
        return "redirect:/courses/draft";
    }

    // Kurs wird auf Status Gestartet gesetzt
    @PostMapping(path = "/start")
    public String start(@PathVariable("id") Long id) {
        Course course = courseService.getCourse(id);
        courseService.startCourseEntry(course);
        return "redirect:/courses/myCourses";
    }

    // Kurs wird auf Status Beendet gesetzt
    @PostMapping(path = "/end")
    public String end(@PathVariable("id") Long id) {
        Course course = courseService.getCourse(id);
        courseService.endCourseEntry(course);
        return "redirect:/courses/myEndedCourses";
    }

    // Kurs löschen
    @PostMapping(path = "/delete")
    public String delete(@PathVariable("id") Long id) {
        getCourse(id);
        courseService.delete(id);
        return "redirect:/courses/";
    }

}
