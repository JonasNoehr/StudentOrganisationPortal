package de.hsba.bi.StuOrgPortal.web.course;

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


@Controller
@RequestMapping("/entries/{id}")
@RequiredArgsConstructor
public class CourseEntryController {

    private final CourseService courseService;
    private final UserService userService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("course", courseService.getAll());
        return "courses/index";
    }

    @PostMapping(path = "/participate")
    public String participate(@PathVariable("id") Long id) {
        CourseEntry entry = courseService.findEntry(id);
        User user = userService.findCurrentUser();
        if (entry.isParticipant(user)) {
            return "/courses/alreadySubscribed";
        }
        courseService.addParticipant(entry, user);
        return "/courses/successfulParticipation";
    }

    @PostMapping(path = "/unsubscribe")
    public String unsubscribe(@PathVariable("id") Long id) {
        CourseEntry entry = courseService.findEntry(id);
        User user = userService.findCurrentUser();
        if (entry.isParticipant(user)) {
            courseService.removeParticipant(entry, user);
            return "/courses/successfulUnsubscription";
        }
        return "/courses/noSubscription";
    }

    @GetMapping(path = "/participants")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("course", courseService.findEntry(id));
        return "courses/courseParticipants";
    }
}
