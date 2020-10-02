package de.hsba.bi.StuOrgPortal.web.course;

import de.hsba.bi.StuOrgPortal.course.CourseAssessment;
import de.hsba.bi.StuOrgPortal.course.CourseEntry;
import de.hsba.bi.StuOrgPortal.course.CourseGrade;
import de.hsba.bi.StuOrgPortal.course.CourseService;
import de.hsba.bi.StuOrgPortal.user.User;
import de.hsba.bi.StuOrgPortal.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/courseAssessment/{entryId}/{userId}")
@RequiredArgsConstructor
public class CourseAssessmentController {

    private final CourseService courseService;
    private final UserService userService;

    @PostMapping
    public String setCourseAssessment(@PathVariable("entryId") Long entryId, @PathVariable("userId") Long userId, Double assessment) {
        CourseEntry entry = courseService.findEntry(entryId);
        User user = userService.findById(userId);
        CourseAssessment courseAssessment = courseService.findAssessmentByEntryAndUser(entry, user);
        courseService.setCourseAssessment(courseAssessment, assessment);
        courseService.setAssessmentAverage(entry);
        return "/courses/AssessmentSuccessful";
    }
}
