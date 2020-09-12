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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses/{id}")
@RequiredArgsConstructor
public class CourseShowController {

    private final CourseService courseService;
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
    /*
    @ModelAttribute("users")
    public List<User> getUsers() {
        return userService.findStudents();
    }

     */

    @ExceptionHandler(NotFoundException.class)
    public String notFound() {
        return "courses/notFound";
    }

    @GetMapping
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("course", courseService.getCourse(id));
        return "courses/show";
    }

    @GetMapping(path = "/draft")
    public String showDraft(@PathVariable("id") Long id, Model model) {
        model.addAttribute("course", courseService.getCourse(id));
        return "courses/showDraft";
    }

    @PostMapping
    public String addEntry(@PathVariable("id") Long id, CourseEntry entry) {
        Course course = courseService.getCourse(id);
        courseService.addCourseEntry(course, entry);
        return "redirect:/courses/" + id + "/draft";
    }

    @PostMapping(path = "/post")
    public String post(@PathVariable("id") Long id) {
        Course course = courseService.getCourse(id);
        courseService.postCourseEntry(course);
        return "redirect:/courses/draft";
    }

    @PostMapping(path = "/delete")
    public String delete(@PathVariable("id") Long id) {
        getCourse(id);
        courseService.delete(id);
        return "redirect:/courses/";
    }

}
