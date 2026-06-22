package university.services;

import university.entities.Course;

public class CourseService {

    private final Course[] courses = new Course[50];
    private int size = 0;

    public void addCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Курс не може бути null");
        }
        if (size >= courses.length) {
            System.out.println("Список курсів переповнений — додавання неможливе.");
            return;
        }
        if (findById(course.getId()) != null) {
            throw new IllegalArgumentException("Курс з таким ID вже існує.");
        }

        courses[size++] = course;
    }

    public void printAllCourses() {
        if (size == 0) {
            System.out.println("Список курсів порожній.");
            return;
        }

        for (int i = 0; i < size; i++) {
            System.out.println(courses[i]);
        }
    }

    public Course findById(int id) {
        for (int i = 0; i < size; i++) {
            if (courses[i].getId() == id) {
                return courses[i];
            }
        }
        return null;
    }

    public void updateCourse(int id, String title, int credits) {
        Course course = findById(id);
        if (course == null) {
            System.out.println("Курс з таким ID не знайдено.");
            return;
        }

        course.setTitle(title);
        course.setCredits(credits);
    }

    public void deleteCourse(int id) {
        int index = -1;

        for (int i = 0; i < size; i++) {
            if (courses[i].getId() == id) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Курс з таким ID не знайдено.");
            return;
        }

        // зсув елементів
        for (int i = index; i < size - 1; i++) {
            courses[i] = courses[i + 1];
        }

        courses[--size] = null;
    }
}
