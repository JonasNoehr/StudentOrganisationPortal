
package de.hsba.bi.StuOrgPortal.courses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    Course findByName(String name);

    List<Course> findAll();

    Optional<Course> findById(Integer id);

    @Query("SELECT c FROM Course c ORDER BY c.name")
    List<Course> findAllCourses();

    List<Course> findByUsersId(Integer id);

}
