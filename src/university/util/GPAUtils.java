package university.util;

import university.entities.Enrollment;
import university.entities.Student;
import university.enums.Grade;

public class GPAUtils {

    /**
     * Обчислює середній бал студента за всіма оціненими курсами.
     */
    public static double calculateStudentGPA(int studentId, Enrollment[] enrollments, int size) {
        if (enrollments == null || size <= 0) {
            return 0.0;
        }

        double totalPoints = 0.0;
        int gradedCourses = 0;

        for (int i = 0; i < size; i++) {
            Enrollment e = enrollments[i];
            if (e == null) continue;

            if (e.getStudent().getId() == studentId) {
                Grade grade = e.getGrade();
                if (grade != null && grade != Grade.NA) {
                    totalPoints += grade.getWeight();
                    gradedCourses++;
                }
            }
        }

        return gradedCourses == 0 ? 0.0 : totalPoints / gradedCourses;
    }

    /**
     * Обчислює середній бал по курсу серед усіх студентів.
     */
    public static double calculateCourseGPA(int courseId, Enrollment[] enrollments, int size) {
        if (enrollments == null || size <= 0) {
            return 0.0;
        }

        double totalPoints = 0.0;
        int gradedStudents = 0;

        for (int i = 0; i < size; i++) {
            Enrollment e = enrollments[i];
            if (e == null) continue;

            if (e.getCourse().getId() == courseId) {
                Grade grade = e.getGrade();
                if (grade != null && grade != Grade.NA) {
                    totalPoints += grade.getWeight();
                    gradedStudents++;
                }
            }
        }

        return gradedStudents == 0 ? 0.0 : totalPoints / gradedStudents;
    }

    /**
     * Сортує студентів за іменем у межах переданого розміру.
     */
    public static void sortStudentsByName(Student[] students, int size) {
        if (students == null || size <= 1) {
            return;
        }

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                Student a = students[j];
                Student b = students[j + 1];

                if (a == null || b == null) continue;

                if (a.getFullName().compareToIgnoreCase(b.getFullName()) > 0) {
                    students[j] = b;
                    students[j + 1] = a;
                }
            }
        }
    }
}
