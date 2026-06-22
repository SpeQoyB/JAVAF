package university.services;

import university.entities.Teacher;

public class TeacherService {

    private final Teacher[] teachers = new Teacher[50];
    private int size = 0;

    public void addTeacher(Teacher teacher) {
        if (teacher == null) {
            throw new IllegalArgumentException("Викладач не може бути null.");
        }

        if (size >= teachers.length) {
            System.out.println("Список викладачів переповнений — додавання неможливе.");
            return;
        }

        if (findById(teacher.getId()) != null) {
            throw new IllegalArgumentException("Викладач з таким ID вже існує.");
        }

        teachers[size++] = teacher;
    }

    public void printAllTeachers() {
        if (size == 0) {
            System.out.println("Список викладачів порожній.");
            return;
        }

        for (int i = 0; i < size; i++) {
            System.out.println(teachers[i]);
        }
    }

    public Teacher findById(int id) {
        for (int i = 0; i < size; i++) {
            if (teachers[i].getId() == id) {
                return teachers[i];
            }
        }
        return null;
    }

    public void updateTeacher(int id, String name, String email) {
        Teacher teacher = findById(id);

        if (teacher == null) {
            System.out.println("Викладача з таким ID не знайдено.");
            return;
        }

        teacher.setFullName(name);
        teacher.setEmail(email);
    }

    public void deleteTeacher(int id) {
        int index = -1;

        for (int i = 0; i < size; i++) {
            if (teachers[i].getId() == id) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Викладача з таким ID не знайдено.");
            return;
        }

        // зсув елементів
        for (int i = index; i < size - 1; i++) {
            teachers[i] = teachers[i + 1];
        }

        teachers[--size] = null;
    }
}
