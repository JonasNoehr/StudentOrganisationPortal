package de.hsba.bi.StuOrgPortal.course;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CourseRepository {

    private Map<Long, Course> store = new TreeMap<>();
    private AtomicLong sequence = new AtomicLong();

    Course save(Course course) {
        Long id = sequence.incrementAndGet();
        course.setId(id);
        store.put(id, course);
        return course;
    }

    Optional<Course> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    Collection<Course> findAll() {
        return store.values();
    }
}
