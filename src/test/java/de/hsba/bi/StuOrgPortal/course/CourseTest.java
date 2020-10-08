package de.hsba.bi.StuOrgPortal.course;

import de.hsba.bi.StuOrgPortal.user.User;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;

public class CourseTest {

    @Test
    void shouldComputeGradeAverage() {
        // given
        User anne = new User("Anne", "password", User.STUDENT_ROLE);
        User ben = new User("Ben", "password", User.STUDENT_ROLE);
        User nik = new User("Nik", "password", User.STUDENT_ROLE);
        User marie = new User("Marie", "password", User.STUDENT_ROLE);
        User zoe = new User("Zoe", "password", User.LECTURER_ROLE);

        Course course = new Course(zoe);
        course.setName("Englisch");
        CourseEntry entry = new CourseEntry("Englisch", "Englisch BWL", zoe, 15, "R602");
        addCourseEntry(course, entry);
        addParticipant(entry, anne);
        addParticipant(entry, ben);
        addParticipant(entry, nik);
        addParticipant(entry, marie);
        List<CourseGrade> gradeList = new ArrayList<>();
        setCourseGrades(entry, anne, 2.0, gradeList);
        setCourseGrades(entry, ben, 2.7, gradeList);
        setCourseGrades(entry, nik, 1.3, gradeList);
        setCourseGrades(entry, marie, 3.7, gradeList);
        setGradeAverage(entry, gradeList);

        printCourse(course);

        assertThat(gradeList).hasSize(4);
        assertThat(entry.getGradeAverage()).isEqualTo(2.42);

    }

    private void addCourseEntry(Course course, CourseEntry entry) {
        entry.setCourse(course);
        entry.setLecturer(course.getOwner());
        course.getEntries().add(entry);
        course.setCourseEntrySet(true);
    }

    private void addParticipant(CourseEntry entry, User user) {
        entry.getParticipants().add(user);
    }

    private void setCourseGrades(CourseEntry entry, User user, Double userGrade, List<CourseGrade> gradeList ) {
        CourseGrade grade = new CourseGrade();
        grade.setCourseEntry(entry);
        grade.setUser(user);
        grade.setGrade(userGrade);
        gradeList.add(grade);
    }

    private double round(double value, int decimalPoints) {
        double d = Math.pow(10, decimalPoints);
        return Math.round(value * d) / d;
    }

    private void setGradeAverage(CourseEntry entry, List<CourseGrade> gradeList) {
        CourseGrade[] gradeArray = gradeList.toArray(CourseGrade[]::new);
        CourseGrade courseGrade;
        Integer qtyUserWithGrade = 0;
        Double userGrades = 0.0;
        for (int i = 0; i<gradeArray.length;i++) {
            courseGrade = gradeArray[i];
            if (courseGrade.getGrade() != null) {
                qtyUserWithGrade++;
                userGrades = userGrades + courseGrade.getGrade();
            }
        }
        entry.setGradeAverage(round((userGrades/qtyUserWithGrade), 2));
    }

    private void printCourse(Course course) {
        for (CourseEntry entry: course.getEntries()) {
            System.out.printf("Der Notendurschnitt für Kurs %s ist %s", course.getName(), entry.getGradeAverage());
        }
    }
}
