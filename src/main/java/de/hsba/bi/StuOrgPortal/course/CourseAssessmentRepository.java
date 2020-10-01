package de.hsba.bi.StuOrgPortal.course;

import de.hsba.bi.StuOrgPortal.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseAssessmentRepository extends JpaRepository<CourseAssessment, Long> {
    List<CourseAssessment> findByCourseEntry(CourseEntry courseEntry);
    CourseAssessment findByCourseEntryAndAndUser(CourseEntry courseEntry, User user);
}
