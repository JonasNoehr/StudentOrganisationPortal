package de.hsba.bi.StuOrgPortal.web.course;

import de.hsba.bi.StuOrgPortal.course.Course;
import de.hsba.bi.StuOrgPortal.course.CourseEntry;
import de.hsba.bi.StuOrgPortal.course.CourseService;
import de.hsba.bi.StuOrgPortal.user.User;
import de.hsba.bi.StuOrgPortal.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final UserService userService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("course", courseService.getAll());
        return "courses/index";
    }

    @GetMapping(path = "/draft")
    public String showDrafts(Model model) {
        model.addAttribute("course", courseService.getAll());
        return "courses/myCourseDrafts";
    }

    @GetMapping(path = "/myCourses")
    public String showCourses(Model model) {
        User currentUser = userService.findCurrentUser();
        model.addAttribute("course", courseService.getAll());
        model.addAttribute("entry", courseService.getAllEntries());
        model.addAttribute("user", currentUser);
        return "courses/myCourses";
    }

    @GetMapping(path = "/filter")
    public String showCoursesWithFilter(Model model, @RequestParam(value = "filter", required = false, defaultValue = "") String filter) {
        User currentUser = userService.findCurrentUser();
        String statusFilter = new String();
        if (filter.equals("POSTED_STATUS")) {
            statusFilter = Course.POSTED_STATUS;
        } else if (filter.equals("ENDED_STATUS")) {
            statusFilter = Course.ENDED_STATUS;
        } else if (filter.equals("STARTED_STATUS")) {
            statusFilter = Course.STARTED_STATUS;
        }
        model.addAttribute("course", courseService.findCourses(statusFilter));
        model.addAttribute("entry", courseService.getAllEntries());
        model.addAttribute("filter", filter);
        model.addAttribute("user", currentUser);
        return "courses/myCourses";
    }

    @GetMapping(path = "/myEndedCourses")
    public String showEndedCourses(Model model) {
        model.addAttribute("course", courseService.getAll());
        return "courses/myEndedCourses";
    }

    @PostMapping
    public String create(String name) {
        User currentUser = userService.findCurrentUser();
        Course course = courseService.createCourse(name, currentUser);
        return "redirect:/courses/" + course.getId() + "/draft";
    }
}
