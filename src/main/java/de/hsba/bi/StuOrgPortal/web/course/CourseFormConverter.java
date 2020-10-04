package de.hsba.bi.StuOrgPortal.web.course;

import de.hsba.bi.StuOrgPortal.course.Course;
import de.hsba.bi.StuOrgPortal.course.CourseEntry;
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
}
