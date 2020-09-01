package de.hsba.bi.StuOrgPortal.web.course;
/*
import de.hsba.bi.StuOrgPortal.course.Course;
import de.hsba.bi.StuOrgPortal.course.CourseEntry;
import de.hsba.bi.StuOrgPortal.course.CourseService;
import de.hsba.bi.StuOrgPortal.user.UserService;
import javassist.NotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/entries/{id}")
@NoArgsConstructor
public class CourseEntryController {

    private final CourseService courseService;
    private final UserService userService;
    private final CourseFormConverter formConverter;

    @ModelAttribute("users")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @ModelAttribute("entry")
    public CourseEntry getEntry(@PathVariable("id") Long id) {
        CourseEntry entry = courseService.findEntry(id);
        if (entry == null) {
            throw new NotFoundException();
        }

        if (!entry.getCourse().isOwnedByCurrentUser()) {
            throw new Http403ForbiddenEntryPoint();
        }

        return entry;
    }

    @GetMapping
    public String show(Model model, @PathVariable("id") Long id) {
        model.addAttribute("courseEntryForm", formConverter.toForm(getEntry(id)));
        return "courses/entry";
    }

    @PostMapping
    public String update(
            @PathVariable("id") Long id,
            @ModelAttribute("courseEntryForm") @Validated CourseEntry form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "courses/entry";
        }

        CourseEntry entry = getEntry(id);
        courseService.save(formConverter.update(entry, form));
        return "redirect:/courses/" + entry.getCourse().getId();
    }

}
*/