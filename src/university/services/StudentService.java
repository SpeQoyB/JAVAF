package university.services;

import university.entities.Student;
import university.enums.StudentStatus;

public class StudentService {

    private final Student[] students = new Student[100];
    private int size = 0;

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Студент не може бути null.");
        }

        if (size >= students.length) {
            System.out.println("Список студентів переповнений — додавання неможливе.");
            return;
        }

        if (findById(student.getId()) != null) {
            throw new IllegalArgumentException("Студент з таким ID вже існує.");
        }

        students[size++] = student;
    }

    public void printAllStudents() {
        if (size == 0) {
            System.out.println("Список студентів порожній.");
            return;
        }

        for (int i = 0; i < size; i++) {
            System.out.println(students[i]);
        }
    }

    public Student findById(int id) {
        for (int i = 0; i < size; i++) {
            if (students[i].getId() == id) {
                return students[i];
            }
        }
        return null;
    }

    public void updateStudent(int id, String name, String email, int year) {
        Student student = findById(id);

        if (student == null) {
            System.out.println("Студента з таким ID не знайдено.");
            return;
        }

        student.setFullName(name);
        student.setEmail(email);
        student.setYearOfStudy(year);
    }

    public void deleteStudent(int id) {
        int index = -1;

        for (int i = 0; i < size; i++) {
            if (students[i].getId() == id) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Студента з таким ID не знайдено.");
            return;
        }

        // зсув елементів
        for (int i = index; i < size - 1; i++) {
            students[i] = students[i + 1];
        }

        students[--size] = null;
    }

    public void changeStatus(int id, StudentStatus status) {
        if (status == null) {
            System.out.println("Некоректний статус.");
            return;
        }

        Student student = findById(id);

        if (student == null) {
            System.out.println("Студента з таким ID не знайдено.");
            return;
        }

        student.setStatus(status);
    }

    public void printStudentsFiltered(StudentStatus status, Integer year, boolean sortByName) {
        Student[] filtered = new Student[size];
        int fSize = 0;

        for (int i = 0; i < size; i++) {
            boolean matchStatus = (status == null) || students[i].getStatus() == status;
            boolean matchYear = (year == null) || students[i].getYearOfStudy() == year;

            if (matchStatus && matchYear) {
                filtered[fSize++] = students[i];
            }
        }

        if (fSize == 0) {
            System.out.println("Студентів за вказаними критеріями не знайдено.");
            return;
        }

        if (sortByName) {
            university.util.GPAUtils.sortStudentsByName(filtered, fSize);
        }

        for (int i = 0; i < fSize; i++) {
            System.out.println(filtered[i]);
        }
    }

    public Student[] getRawStudentsArray() {
        return students;
    }

    public int getSize() {
        return size;
    }
}
