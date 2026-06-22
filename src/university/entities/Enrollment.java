package university.entities;

import university.enums.Grade;
import university.interfaces.Payable;

public class Enrollment implements Payable {

    private Student student;
    private Course course;
    private String semester;
    private Grade grade;
    private boolean paid;

    public Enrollment(Student student, Course course, String semester) {
        if (student == null) {
            throw new IllegalArgumentException("Студент не може бути null");
        }
        if (course == null) {
            throw new IllegalArgumentException("Курс не може бути null");
        }
        if (semester == null || semester.isBlank()) {
            throw new IllegalArgumentException("Семестр вказано некоректно");
        }

        this.student = student;
        this.course = course;
        this.semester = semester;
        this.grade = Grade.NA;   // стартове значення
        this.paid = false;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getSemester() {
        return semester;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        if (grade == null) {
            throw new IllegalArgumentException("Оцінка не може бути null");
        }
        this.grade = grade;
    }

    @Override
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public boolean isPaid() {
        return paid;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "student=" + student.getFullName() +
                ", course=" + course.getTitle() +
                ", semester='" + semester + '\'' +
                ", grade=" + grade +
                ", paid=" + (paid ? "yes" : "no") +
                '}';
    }
}
