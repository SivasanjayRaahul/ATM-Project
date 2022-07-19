package atm.model;

public class User {
    private final String name;
    private final String emailId;
    private final int age;

    public User(String name, String emailId, int age) {
        this.name = name;
        this.emailId = emailId;
        this.age = age;
    }

    public String getName() {
        return name;
    }
}
