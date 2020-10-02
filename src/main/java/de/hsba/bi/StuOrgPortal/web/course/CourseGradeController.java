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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/courseGrades/{entryId}/{userId}")
@RequiredArgsConstructor
public class CourseGradeController {

    private final CourseService courseService;
    private final UserService userService;

    @PostMapping
    public String setUserGrade(@PathVariable("entryId") Long entryId, @PathVariable("userId") Long userId, Double grade) {
        CourseEntry entry = courseService.findEntry(entryId);
        User user = userService.findById(userId);
        CourseGrade courseGrade = courseService.findByEntryAndUser(entry, user);
        courseService.setUserGrade(courseGrade, grade);
        courseService.setGradeAverage(entry);
        return "redirect:/entries/" + entry.getId() + "/setUserGrades";
    }
}
