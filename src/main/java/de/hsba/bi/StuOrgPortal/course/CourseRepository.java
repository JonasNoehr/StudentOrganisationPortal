package de.hsba.bi.StuOrgPortal.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface CourseRepository extends JpaRepository<Course, Long> {

    //@Query("select distinct j from Course j where j.status like %:filter%")
    List<Course> findByStatus(String filter);
}
