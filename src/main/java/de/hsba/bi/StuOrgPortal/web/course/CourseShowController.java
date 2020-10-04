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
        /*
        if (!course.isOwnedByCurrentUser()) {
            throw new ForbiddenException();
        }

         */
        return course;
    }

    @ExceptionHandler(NotFoundException.class)
    public String notFound() {
        return "courses/notFound";
    }

    @GetMapping
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("course", courseService.getCourse(id));
        model.addAttribute("user", userService.findCurrentUser());
        return "courses/show";
    }

    @GetMapping(path = "/draft")
    public String showDraft(@PathVariable("id") Long id, Model model) {
        model.addAttribute("course", courseService.getCourse(id));
        model.addAttribute("courseEntryForm", new CourseEntryForm());
        return "courses/showDraft";
    }

    @PostMapping
    public String addEntry(@PathVariable("id") Long id, @ModelAttribute("courseEntryForm") @Valid CourseEntryForm entryForm, BindingResult entryBindingResult) {
        if (entryBindingResult.hasErrors()) {
            return "courses/showDraft";
        }
        Course course = courseService.getCourse(id);
        if (!course.isCourseEntrySet()) {
            courseService.addCourseEntry(course, formConverter.update(new CourseEntry(), entryForm));
        } else {
            CourseEntry courseEntry = courseService.findCourseEntryByCourseId(id);
            courseService.changeCourseEntry(formConverter.update(courseEntry, entryForm));
        }
        return "redirect:/courses/" + id + "/draft";
    }

    @PostMapping(path = "/post")
    public String post(@PathVariable("id") Long id) {
        Course course = courseService.getCourse(id);
        courseService.postCourseEntry(course);
        return "redirect:/courses/draft";
    }

    @PostMapping(path = "/start")
    public String start(@PathVariable("id") Long id) {
        Course course = courseService.getCourse(id);
        courseService.startCourseEntry(course);
        return "redirect:/courses/myCourses";
    }

    @PostMapping(path = "/end")
    public String end(@PathVariable("id") Long id) {
        Course course = courseService.getCourse(id);
        courseService.endCourseEntry(course);
        return "redirect:/courses/myEndedCourses";
    }

    @PostMapping(path = "/delete")
    public String delete(@PathVariable("id") Long id) {
        getCourse(id);
        courseService.delete(id);
        return "redirect:/courses/";
    }

}
