package de.hsba.bi.StuOrgPortal.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CourseEntryRepository extends JpaRepository<CourseEntry, Long> {
}
