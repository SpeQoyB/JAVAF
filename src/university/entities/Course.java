package university.entities;

public class Course {

    private int id;
    private String title;
    private int credits;
    private Teacher teacher;

    public Course(int id, String title, int credits, Teacher teacher) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID курсу має бути додатним");
        }
        if (credits <= 0) {
            throw new IllegalArgumentException("Кредити не можуть бути нульовими або від’ємними");
        }

        this.id = id;
        this.title = title;
        this.credits = credits;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        // Невелика перевірка, яку часто додають вручну
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Назва курсу не може бути порожньою");
        }
        this.title = title;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        if (credits <= 0) {
            throw new IllegalArgumentException("Кредити повинні бути більше нуля");
        }
        this.credits = credits;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        // Люди часто додають такі перевірки
        if (teacher == null) {
            throw new IllegalArgumentException("Курс повинен мати викладача");
        }
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Course{id=" + id +
                ", title='" + title + '\'' +
                ", credits=" + credits +
                ", teacher=" + (teacher != null ? teacher.getFullName() : "—") +
                '}';
    }
}
