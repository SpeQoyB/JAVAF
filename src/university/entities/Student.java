package university.entities;

import university.enums.StudentStatus;

public class Student extends Person {

    private int yearOfStudy;
    private StudentStatus status;

    public Student(int id, String fullName, String email, int yearOfStudy, StudentStatus status) {
        super(id, fullName, email);

        if (yearOfStudy < 1 || yearOfStudy > 6) {
            throw new IllegalArgumentException("Курс навчання повинен бути в межах 1–6");
        }
        if (status == null) {
            throw new IllegalArgumentException("Статус студента не може бути null");
        }

        this.yearOfStudy = yearOfStudy;
        this.status = status;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        if (yearOfStudy < 1 || yearOfStudy > 6) {
            throw new IllegalArgumentException("Курс має бути від 1 до 6");
        }
        this.yearOfStudy = yearOfStudy;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Статус не може бути порожнім");
        }
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", name='" + getFullName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", year=" + yearOfStudy +
                ", status=" + status +
                '}';
    }
}
