package de.hsba.bi.StuOrgPortal.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//import de.hsba.bi.StuOrgPortal.user.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select distinct j from Course j join j.entries e where e.description like %:search")
    List<Course> findByEntryDescription(@Param("search") String search);
}
