package university;

import university.entities.Student;
import university.entities.Teacher;
import university.entities.Course;
import university.entities.Enrollment;
import university.enums.StudentStatus;
import university.enums.TeacherPosition;
import university.enums.Grade;
import university.services.StudentService;
import university.services.TeacherService;
import university.services.CourseService;
import university.services.EnrollmentService;
import university.util.GPAUtils;

import java.util.Scanner;

public class Main {

    private static final StudentService studentService = new StudentService();
    private static final TeacherService teacherService = new TeacherService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n--- ГОЛОВНЕ МЕНЮ ---");
            System.out.println("1. Студенти");
            System.out.println("2. Викладачі");
            System.out.println("3. Курси");
            System.out.println("4. Зарахування та оцінки");
            System.out.println("5. Звіти / Пошук");
            System.out.println("0. Вихід");
            System.out.print("Оберіть пункт меню: ");

            choice = readInt();

            switch (choice) {
                case 1 -> studentMenu();
                case 2 -> teacherMenu();
                case 3 -> courseMenu();
                case 4 -> enrollmentMenu();
                case 5 -> reportsMenu();
                case 0 -> System.out.println("Завершення роботи.");
                default -> System.out.println("Невідомий пункт меню.");
            }

        } while (choice != 0);
    }

    // -------------------- STUDENTS --------------------

    private static void studentMenu() {
        System.out.println("\n--- КЕРУВАННЯ СТУДЕНТАМИ ---");
        System.out.println("1. Додати студента");
        System.out.println("2. Перегляд списку");
        System.out.println("3. Оновити дані");
        System.out.println("4. Видалити студента");
        System.out.println("5. Змінити статус");
        System.out.print("Оберіть пункт: ");

        int choice = readInt();

        switch (choice) {
            case 1 -> addStudent();
            case 2 -> filterStudents();
            case 3 -> updateStudent();
            case 4 -> deleteStudent();
            case 5 -> changeStudentStatus();
            default -> System.out.println("Невідома команда.");
        }
    }

    private static void addStudent() {
        System.out.print("ID: ");
        int id = readInt();
        System.out.print("ПІБ: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Курс навчання (1-6): ");
        int year = readInt();

        studentService.addStudent(new Student(id, name, email, year, StudentStatus.ACTIVE));
        System.out.println("Студента додано.");
    }

    private static void filterStudents() {
        System.out.println("1. Повний список");
        System.out.println("2. Фільтрація/Сортування");
        System.out.print("Оберіть: ");

        int choice = readInt();

        if (choice == 1) {
            studentService.printAllStudents();
            return;
        }

        System.out.println("Оберіть статус (0 - пропустити): 1.ACTIVE 2.ON_LEAVE 3.EXPELLED 4.GRADUATED");
        int s = readInt();
        StudentStatus status = switch (s) {
            case 1 -> StudentStatus.ACTIVE;
            case 2 -> StudentStatus.ON_LEAVE;
            case 3 -> StudentStatus.EXPELLED;
            case 4 -> StudentStatus.GRADUATED;
            default -> null;
        };

        System.out.print("Курс (0 - пропустити): ");
        int y = readInt();
        Integer year = y == 0 ? null : y;

        System.out.print("Сортувати за ПІБ? (так/ні): ");
        boolean sort = scanner.nextLine().equalsIgnoreCase("так");

        studentService.printStudentsFiltered(status, year, sort);
    }

    private static void updateStudent() {
        System.out.print("ID студента: ");
        int id = readInt();
        System.out.print("Нове ПІБ: ");
        String name = scanner.nextLine();
        System.out.print("Новий Email: ");
        String email = scanner.nextLine();
        System.out.print("Новий курс: ");
        int year = readInt();

        studentService.updateStudent(id, name, email, year);
    }

    private static void deleteStudent() {
        System.out.print("ID для видалення: ");
        int id = readInt();
        studentService.deleteStudent(id);
    }

    private static void changeStudentStatus() {
        System.out.print("ID студента: ");
        int id = readInt();
        System.out.println("1.ACTIVE 2.ON_LEAVE 3.EXPELLED 4.GRADUATED");
        int s = readInt();

        StudentStatus status = switch (s) {
            case 1 -> StudentStatus.ACTIVE;
            case 2 -> StudentStatus.ON_LEAVE;
            case 3 -> StudentStatus.EXPELLED;
            case 4 -> StudentStatus.GRADUATED;
            default -> null;
        };

        if (status != null) {
            studentService.changeStatus(id, status);
        } else {
            System.out.println("Невідомий статус.");
        }
    }

    // -------------------- TEACHERS --------------------

    private static void teacherMenu() {
        System.out.println("\n--- КЕРУВАННЯ ВИКЛАДАЧАМИ ---");
        System.out.println("1. Додати викладача");
        System.out.println("2. Показати всіх");
        System.out.println("3. Оновити дані");
        System.out.println("4. Видалити викладача");
        System.out.print("Оберіть пункт: ");

        int choice = readInt();

        switch (choice) {
            case 1 -> addTeacher();
            case 2 -> teacherService.printAllTeachers();
            case 3 -> updateTeacher();
            case 4 -> deleteTeacher();
            default -> System.out.println("Невідома команда.");
        }
    }

    private static void addTeacher() {
        System.out.print("ID: ");
        int id = readInt();
        System.out.print("ПІБ: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.println("1.ASSISTANT 2.LECTURER 3.PROFESSOR");
        int p = readInt();

        TeacherPosition pos = switch (p) {
            case 1 -> TeacherPosition.ASSISTANT;
            case 2 -> TeacherPosition.LECTURER;
            case 3 -> TeacherPosition.PROFESSOR;
            default -> null;
        };

        if (pos == null) {
            System.out.println("Невідома посада.");
            return;
        }

        teacherService.addTeacher(new Teacher(id, name, email, pos));
    }

    private static void updateTeacher() {
        System.out.print("ID викладача: ");
        int id = readInt();
        System.out.print("Нове ПІБ: ");
        String name = scanner.nextLine();
        System.out.print("Новий Email: ");
        String email = scanner.nextLine();

        teacherService.updateTeacher(id, name, email);
    }

    private static void deleteTeacher() {
        System.out.print("ID для видалення: ");
        int id = readInt();
        teacherService.deleteTeacher(id);
    }

    // -------------------- COURSES --------------------

    private static void courseMenu() {
        System.out.println("\n--- КЕРУВАННЯ КУРСАМИ ---");
        System.out.println("1. Додати курс");
        System.out.println("2. Показати всі");
        System.out.println("3. Оновити дані");
        System.out.println("4. Видалити курс");
        System.out.print("Оберіть пункт: ");

        int choice = readInt();

        switch (choice) {
            case 1 -> addCourse();
            case 2 -> courseService.printAllCourses();
            case 3 -> updateCourse();
            case 4 -> deleteCourse();
            default -> System.out.println("Невідома команда.");
        }
    }

    private static void addCourse() {
        System.out.print("ID курсу: ");
        int id = readInt();
        System.out.print("Назва: ");
        String title = scanner.nextLine();
        System.out.print("Кредити: ");
        int credits = readInt();
        System.out.print("ID викладача: ");
        int tId = readInt();

        Teacher t = teacherService.findById(tId);
        if (t == null) {
            System.out.println("Викладача не знайдено. Курс створено без викладача.");
        }

        courseService.addCourse(new Course(id, title, credits, t));
    }

    private static void updateCourse() {
        System.out.print("ID курсу: ");
        int id = readInt();
        System.out.print("Нова назва: ");
        String title = scanner.nextLine();
        System.out.print("Нові кредити: ");
        int credits = readInt();

        courseService.updateCourse(id, title, credits);
    }

    private static void deleteCourse() {
        System.out.print("ID курсу: ");
        int id = readInt();
        courseService.deleteCourse(id);
    }

    // -------------------- ENROLLMENTS --------------------

    private static void enrollmentMenu() {
        System.out.println("\n--- ЗАРАХУВАННЯ ТА ОЦІНКИ ---");
        System.out.println("1. Зарахувати");
        System.out.println("2. Виставити оцінку");
        System.out.println("3. Позначити оплату");
        System.out.println("4. Транскрипт та GPA");
        System.out.print("Оберіть пункт: ");

        int choice = readInt();

        switch (choice) {
            case 1 -> enrollStudent();
            case 2 -> setGrade();
            case 3 -> setPayment();
            case 4 -> printTranscript();
            default -> System.out.println("Невідома команда.");
        }
    }

    private static void enrollStudent() {
        System.out.print("ID студента: ");
        int sId = readInt();
        System.out.print("ID курсу: ");
        int cId = readInt();
        System.out.print("Семестр: ");
        String sem = scanner.nextLine();

        enrollmentService.enrollStudent(
                studentService.findById(sId),
                courseService.findById(cId),
                sem
        );
    }

    private static void setGrade() {
        System.out.print("ID студента: ");
        int sId = readInt();
        System.out.print("ID курсу: ");
        int cId = readInt();
        System.out.print("Оцінка (A-F): ");
        Grade grade = Grade.valueOf(scanner.nextLine().toUpperCase());

        enrollmentService.setGrade(sId, cId, grade);
    }

    private static void setPayment() {
        System.out.print("ID студента: ");
        int sId = readInt();
        System.out.print("ID курсу: ");
        int cId = readInt();
        System.out.print("Оплачено? (так/ні): ");
        boolean paid = scanner.nextLine().equalsIgnoreCase("так");

        enrollmentService.setPaid(sId, cId, paid);
    }

    private static void printTranscript() {
        System.out.print("ID студента: ");
        int sId = readInt();

        enrollmentService.printTranscript(sId);
        double gpa = GPAUtils.calculateStudentGPA(
                sId,
                enrollmentService.getEnrollments(),
                enrollmentService.getSize()
        );

        System.out.printf("GPA: %.2f\n", gpa);
    }

    // -------------------- REPORTS --------------------

    private static void reportsMenu() {
        System.out.println("\n--- ЗВІТИ ТА ПОШУК ---");
        System.out.println("1. Пошук студента");
        System.out.println("2. Студенти-боржники");
        System.out.println("3. GPA по курсу");
        System.out.print("Оберіть пункт: ");

        int choice = readInt();

        switch (choice) {
            case 1 -> searchStudent();
            case 2 -> printDebtors();
            case 3 -> printCourseGPA();
            default -> System.out.println("Невідома команда.");
        }
    }

    private static void searchStudent() {
        System.out.print("Пошук: ");
        String q = scanner.nextLine().toLowerCase();

        Student[] arr = studentService.getRawStudentsArray();
        int size = studentService.getSize();

        boolean found = false;

        for (int i = 0; i < size; i++) {
            Student s = arr[i];
            if (s.getFullName().toLowerCase().contains(q) ||
                    s.getEmail().toLowerCase().contains(q)) {
                System.out.println(s);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Нічого не знайдено.");
        }
    }

    private static void printDebtors() {
        System.out.println("Студенти з неоплаченими курсами:");

        Enrollment[] arr = enrollmentService.getEnrollments();
        int size = enrollmentService.getSize();

        boolean found = false;

        for (int i = 0; i < size; i++) {
            Enrollment e = arr[i];
            if (!e.isPaid()) {
                System.out.println(e.getStudent() + " -> " + e.getCourse().getTitle());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Боржників немає.");
        }
    }

    private static void printCourseGPA() {
        System.out.print("ID курсу: ");
        int id = readInt();

        double gpa = GPAUtils.calculateCourseGPA(
                id,
                enrollmentService.getEnrollments(),
                enrollmentService.getSize()
        );

        System.out.printf("GPA курсу: %.2f\n", gpa);
    }

    // -------------------- HELPERS --------------------

    private static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.print("Введіть число: ");
            }
        }
    }
}
