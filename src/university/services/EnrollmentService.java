package university.services;

import university.entities.Enrollment;
import university.entities.Student;
import university.entities.Course;
import university.enums.Grade;

public class EnrollmentService {

    private final Enrollment[] enrollments = new Enrollment[200];
    private int size = 0;

    public void enrollStudent(Student student, Course course, String semester) {
        if (student == null || course == null || semester == null || semester.isBlank()) {
            System.out.println("Неможливо виконати зарахування: некоректні дані.");
            return;
        }

        if (size >= enrollments.length) {
            System.out.println("Список зарахувань переповнений — додавання неможливе.");
            return;
        }

        // перевірка на дубль
        for (int i = 0; i < size; i++) {
            Enrollment e = enrollments[i];
            if (e.getStudent().getId() == student.getId()
                    && e.getCourse().getId() == course.getId()
                    && e.getSemester().equals(semester)) {
                System.out.println("Студент уже зарахований на цей курс у зазначеному семестрі.");
                return;
            }
        }

        enrollments[size++] = new Enrollment(student, course, semester);
    }

    public void setGrade(int studentId, int courseId, Grade grade) {
        if (grade == null) {
            System.out.println("Оцінка не може бути порожньою.");
            return;
        }

        for (int i = 0; i < size; i++) {
            Enrollment e = enrollments[i];
            if (e.getStudent().getId() == studentId && e.getCourse().getId() == courseId) {
                e.setGrade(grade);
                return;
            }
        }

        System.out.println("Зарахування для виставлення оцінки не знайдено.");
    }

    public void setPaid(int studentId, int courseId, boolean paid) {
        for (int i = 0; i < size; i++) {
            Enrollment e = enrollments[i];
            if (e.getStudent().getId() == studentId && e.getCourse().getId() == courseId) {
                e.setPaid(paid);
                return;
            }
        }

        System.out.println("Зарахування для оновлення статусу оплати не знайдено.");
    }

    public void printTranscript(int studentId) {
        System.out.println("\n--- ТРАНСКРИПТ СТУДЕНТА ---");

        boolean found = false;

        for (int i = 0; i < size; i++) {
            Enrollment e = enrollments[i];
            if (e.getStudent().getId() == studentId) {
                System.out.println(e);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Для цього студента немає записів про зарахування.");
        }
    }

    public Enrollment[] getEnrollments() {
        return enrollments;
    }

    public int getSize() {
        return size;
    }
}
