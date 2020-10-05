package de.hsba.bi.StuOrgPortal.course;

import de.hsba.bi.StuOrgPortal.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseGradeRepository extends JpaRepository<CourseGrade, Long> {

    List<CourseGrade> findCourseGradeByCourseEntry(CourseEntry courseEntry);
    List<CourseGrade> findByCourseEntry(CourseEntry courseEntry);
    CourseGrade findByCourseEntryAndAndUser(CourseEntry courseEntry, User user);

}
