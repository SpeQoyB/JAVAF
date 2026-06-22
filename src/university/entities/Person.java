package university.entities;

public abstract class Person {

    private int id;
    private String fullName;
    private String email;

    public Person(int id, String fullName, String email) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID повинен бути додатним");
        }
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Ім'я не може бути порожнім");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Невірний формат email");
        }

        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Ім'я не може бути порожнім");
        }
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Невірний email");
        }
        this.email = email;
    }
}
