package de.hsba.bi.StuOrgPortal.web.course;

import de.hsba.bi.StuOrgPortal.course.Course;
import de.hsba.bi.StuOrgPortal.course.CourseAssessment;
import de.hsba.bi.StuOrgPortal.course.CourseEntry;
import de.hsba.bi.StuOrgPortal.course.CourseGrade;
import org.springframework.stereotype.Component;

@Component
public class CourseFormConverter {

    CourseForm toForm(Course course) {
        CourseForm form = new CourseForm();
        form.setName(course.getName());
        return form;
    }

    Course update(Course course, CourseForm form) {
        course.setName(form.getName());
        return course;
    }

    CourseEntryForm toForm(CourseEntry entry) {
        CourseEntryForm form = new CourseEntryForm();
        form.setCourseName(entry.getCourseName());
        form.setCourseDescription(entry.getCourseDescription());
        form.setMaxParticipants(entry.getMaxParticipants());
        form.setRoomNumber(entry.getRoomNumber());
        return form;
    }

    CourseEntry update(CourseEntry entry, CourseEntryForm form) {
        entry.setCourseName(form.getCourseName());
        entry.setCourseDescription(form.getCourseDescription());
        entry.setMaxParticipants(form.getMaxParticipants());
        entry.setRoomNumber(form.getRoomNumber());
        return entry;
    }

    CourseGradeForm toForm(CourseGrade grade) {
        CourseGradeForm form = new CourseGradeForm();
        form.setGrade(grade.getGrade());
        return form;
    }

    CourseGrade update(CourseGrade grade, CourseGradeForm form) {
        grade.setGrade(form.getGrade());
        return grade;
    }

    CourseAssessmentForm toForm(CourseAssessment assessment) {
        CourseAssessmentForm form = new CourseAssessmentForm();
        form.setAssessment(assessment.getAssessment());
        return form;
    }

    CourseAssessment update(CourseAssessment assessment, CourseAssessmentForm form) {
        assessment.setAssessment(form.getAssessment());
        return assessment;
    }
}
