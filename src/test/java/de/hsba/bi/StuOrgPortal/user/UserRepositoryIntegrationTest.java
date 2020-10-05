package de.hsba.bi.StuOrgPortal.user;


import de.hsba.bi.StuOrgPortal.course.CourseAssessmentRepository;
import de.hsba.bi.StuOrgPortal.course.CourseEntryRepository;
import de.hsba.bi.StuOrgPortal.course.CourseGradeRepository;
import de.hsba.bi.StuOrgPortal.course.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository repository;

    @Autowired
    private CourseEntryRepository entryRepository;

    @Autowired
    private CourseAssessmentRepository assessmentRepository;

    @Autowired
    private CourseGradeRepository gradeRepository;

    @BeforeEach
    public void setUp() {
        // delete all entries that have been created by the CourseService's init method

        repository.deleteAll();
        repository.flush();
        entryRepository.deleteAll();
        entryRepository.flush();
        userRepository.deleteAll();
        userRepository.flush();
        assessmentRepository.deleteAll();
        assessmentRepository.flush();
        gradeRepository.deleteAll();
        gradeRepository.flush();
    }

    @Test
    public void shouldFindByName() {

        // given
        User anne = new User("Anne", "password", User.STUDENT_ROLE);
        User bene = new User("Bene", "password", User.ADMIN_ROLE);
        userRepository.save(anne);
        userRepository.save(bene);

        // then
        assertThat(userRepository.findByName("Anne")).isEqualTo(anne);
        assertThat(userRepository.findByName("Bene")).isEqualTo(bene);
        assertThat(userRepository.findByName("Markus")).isNull();
    }

}
