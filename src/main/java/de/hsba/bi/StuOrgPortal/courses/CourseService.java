package de.hsba.bi.StuOrgPortal.courses;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void Seed(String name, String description, int duration, int maxStudents) {
        courseRepository.save(new Course(name, description, duration, maxStudents));
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public Course findCourse(Integer id) {
        return courseRepository.findById(id).orElse(null);
    }

}