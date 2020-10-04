package de.hsba.bi.StuOrgPortal.web.course;

import de.hsba.bi.StuOrgPortal.course.CourseAssessment;
import de.hsba.bi.StuOrgPortal.course.CourseEntry;
import de.hsba.bi.StuOrgPortal.course.CourseGrade;
import de.hsba.bi.StuOrgPortal.course.CourseService;
import de.hsba.bi.StuOrgPortal.user.User;
import de.hsba.bi.StuOrgPortal.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/courseAssessment/{entryId}/{userId}")
@RequiredArgsConstructor
public class CourseAssessmentController {

    private final CourseService courseService;
    private final CourseFormConverter formConverter;
    private final UserService userService;

    @PostMapping
    public String setCourseAssessment(@PathVariable("entryId") Long entryId, @PathVariable("userId") Long userId, Double assessment, Model model, @ModelAttribute("courseAssessmentForm") @Valid CourseAssessmentForm assessmentForm, BindingResult assessmentBindingResult) {
        CourseEntry entry = courseService.findEntry(entryId);
        User user = userService.findById(userId);
        CourseAssessment courseAssessment = courseService.findAssessmentByEntryAndUser(entry, user);
        if (assessmentBindingResult.hasErrors()) {
            model.addAttribute("entry", entry);
            model.addAttribute("assessment", courseAssessment);
            return "courses/participantAssessment";
        }
        courseService.setCourseAssessment(formConverter.update(courseAssessment, assessmentForm), assessment);
        courseService.setAssessmentAverage(entry);
        return "/courses/AssessmentSuccessful";
    }
}
