package de.hsba.bi.StuOrgPortal.web;

import de.hsba.bi.StuOrgPortal.course.Course;
import de.hsba.bi.StuOrgPortal.course.CourseEntry;
import de.hsba.bi.StuOrgPortal.course.CourseService;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String index(Model model, @RequestParam(value = "suche", required = false, defaultValue = "") String search) {
        model.addAttribute("courses", courseService.findCourses(search));
        model.addAttribute("suche", search);
        return "courses/index";
    }

    @PostMapping
    public String create(String name) {
        Course course = courseService.createCourse(name);
        return "redirect:/courses/" /*+ course.getId()*/;
    }

    @GetMapping(path = "/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("course", courseService.getCourse(id));
        return "courses/show";
    }

    @PostMapping(path = "/{id}")
    public String addEntry(@PathVariable("id") Long id, CourseEntry entry) {
        Course course = courseService.getCourse(id);
        courseService.addCourseEntry(course, entry);
        return "redirect:/courses/" + id;
    }

    @PostMapping(path = "/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        courseService.delete(id);
        return "redirect:/courses/";
    }
}
