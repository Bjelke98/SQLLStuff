package dbms;

import dbms.views.model.UserTable;
import dbms.views.model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class TestMain {
    private static final UserTable USER_TABLE = UserTable.getInstance();
    public static void main(String[] args) {

//        for (int i = 0; i < 30; i++) {
//            addTestUser();
//        }

//        deleteAllUsers();
        try {
            USER_TABLE.add(new User("t", "t", true));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        printUsers();

    }

    public static void printUsers(){
        ArrayList<User> users = new ArrayList<>();
        try {
            users = USER_TABLE.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Object[] header = new String[]{
                "Username",
                "Password",
                "Role"
        };
        System.out.format("%-15s%-15s%-8s%n", header);
        System.out.println("------------------------------------");
        for (User u : users){
            Object[] row = new String[]{
                    u.getUsername().substring(0, 1).toUpperCase()+u.getUsername().substring(1),
                    u.getPassword(),
                    u.isAdmin()?"Admin":"User"
            };
            System.out.format("%-15s%-15s%-8s%n", row);
        }
        System.out.println("------------------------------------");
        System.out.format("%-15s%-15s%-8s%n", header);
    }

    public static void deleteUser(String username){
        try {
            USER_TABLE.delete(username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteAllUsers(){
        try {
            USER_TABLE.deleteAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addTestUser(){
        String[] testNames = {
                "Morty",
                "Boeran",
                "Bjelke",
                "Furrmix",
                "Snick",
                "Herms",
                "JuanIvar",
                "Ewik",
                "GauteG",
                "Ruud",
                "Vannmelon",
                "Kakkper",
                "Ruud",
                "Smolav",
                "Slimon",
                "Lud",
                "Durek",
                "ShamanDurex",
                "SaftigeGardiner"
        };
        User testUser = new User(testNames[(int)(Math.random()* testNames.length)]+(int)(Math.random()*100), "TestPass"+(int)(Math.random()*100), ((int)(Math.random()*2))==1);
        try {
            USER_TABLE.add(testUser);
        } catch (SQLException throwables) {
            System.out.println("Couldnt add user to database "+ USER_TABLE.getName());
            throwables.printStackTrace();
        }
    }
}
