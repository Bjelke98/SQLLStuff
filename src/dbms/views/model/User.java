package dbms.views.model;

public class User {

    final String username;
    String password;
    boolean admin;

    public User(String username, String password, boolean admin) {
        this.username = username.toLowerCase();
        this.password = password;
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                '}';
    }
}
