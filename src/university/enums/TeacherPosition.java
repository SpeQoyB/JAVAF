package university.enums;

public enum TeacherPosition {
    ASSISTANT,
    LECTURER,
    PROFESSOR;

    public boolean isSenior() {
        return this == PROFESSOR;
    }

    public boolean isTeachingRole() {
        return this == ASSISTANT || this == LECTURER || this == PROFESSOR;
    }

    @Override
    public String toString() {
        return name();
    }
}
