package university.entities;

import university.enums.TeacherPosition;

public class Teacher extends Person {

    private TeacherPosition position;

    public Teacher(int id, String fullName, String email, TeacherPosition position) {
        super(id, fullName, email);

        if (position == null) {
            throw new IllegalArgumentException("Посада викладача не може бути порожньою");
        }

        this.position = position;
    }

    public TeacherPosition getPosition() {
        return position;
    }

    public void setPosition(TeacherPosition position) {
        if (position == null) {
            throw new IllegalArgumentException("Некоректна посада");
        }
        this.position = position;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + getId() +
                ", name='" + getFullName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", position=" + position +
                '}';
    }
}
