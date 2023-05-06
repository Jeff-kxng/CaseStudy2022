package Server;

public class User {
    private String username;
    private String password;

    public User(String phoneNumber, String password) {
        this.username = phoneNumber;
        this.password = password;
    }

    public String getPhoneNumber() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

