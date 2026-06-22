package university.enums;

public enum StudentStatus {
    ACTIVE,
    ON_LEAVE,
    EXPELLED,
    GRADUATED;

    // Часто додають такі методи для зручності
    public boolean isActive() {
        return this == ACTIVE;
    }

    public boolean isFinished() {
        return this == GRADUATED;
    }

    public boolean isRemoved() {
        return this == EXPELLED;
    }
}
