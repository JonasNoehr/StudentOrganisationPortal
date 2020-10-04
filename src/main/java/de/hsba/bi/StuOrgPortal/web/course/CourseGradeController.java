package de.hsba.bi.StuOrgPortal.web.course;

import de.hsba.bi.StuOrgPortal.course.Course;
import de.hsba.bi.StuOrgPortal.course.CourseEntry;
import de.hsba.bi.StuOrgPortal.course.CourseGrade;
import de.hsba.bi.StuOrgPortal.course.CourseService;
import de.hsba.bi.StuOrgPortal.user.User;
import de.hsba.bi.StuOrgPortal.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/courseGrades/{entryId}/{userId}")
@RequiredArgsConstructor
public class CourseGradeController {

    private final CourseService courseService;
    private final CourseFormConverter formConverter;
    private final UserService userService;

    @PostMapping
    public String setUserGrade(@PathVariable("entryId") Long entryId, @PathVariable("userId") Long userId, Double grade, Model model, @ModelAttribute("courseGradeForm") @Valid CourseGradeForm gradeForm, BindingResult gradeBindingResult) {
        CourseEntry entry = courseService.findEntry(entryId);
        if (gradeBindingResult.hasErrors()) {
            model.addAttribute("entry", entry);
            model.addAttribute("grade", courseService.findByEntryId(entry));
            return "courses/participantGrades";
        }
        User user = userService.findById(userId);
        CourseGrade courseGrade = courseService.findByEntryAndUser(entry, user);
        courseService.setUserGrade(formConverter.update(courseGrade, gradeForm), grade);
        courseService.setGradeAverage(entry);
        return "redirect:/entries/" + entry.getId() + "/setUserGrades";
    }
}
