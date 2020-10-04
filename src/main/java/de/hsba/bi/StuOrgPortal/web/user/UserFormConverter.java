package de.hsba.bi.StuOrgPortal.web.user;

import de.hsba.bi.StuOrgPortal.course.Course;
import de.hsba.bi.StuOrgPortal.course.CourseAssessment;
import de.hsba.bi.StuOrgPortal.course.CourseEntry;
import de.hsba.bi.StuOrgPortal.course.CourseGrade;
import de.hsba.bi.StuOrgPortal.user.User;
import de.hsba.bi.StuOrgPortal.web.course.CourseAssessmentForm;
import de.hsba.bi.StuOrgPortal.web.course.CourseEntryForm;
import de.hsba.bi.StuOrgPortal.web.course.CourseForm;
import de.hsba.bi.StuOrgPortal.web.course.CourseGradeForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFormConverter {

    private final PasswordEncoder passwordEncoder;

    UserForm toForm(User user) {
        UserForm form = new UserForm();
        form.setName(user.getName());
        form.setPassword(passwordEncoder.encode(user.getPassword()));
        return form;
    }

    User update(User user, UserForm form) {
        user.setName(form.getName());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        return user;
    }
}
