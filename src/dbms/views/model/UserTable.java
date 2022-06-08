package dbms.views.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserTable extends DatabaseTable{
    private static final UserTable instance = new UserTable();
    private UserTable(){
        super("userdb");
    }

    public static UserTable getInstance(){
        return instance;
    }

    @Override
    protected void createTable(){
        String sql = """
            CREATE TABLE IF NOT EXISTS user (
                username TEXT PRIMARY KEY,
                password TEXT NOT NULL,
                admin BOOLEAN NOT NULL
            );
        """;
        try {
            db.update(sql);
        } catch (SQLException throwables) {
            System.out.println("could not add table to database "+db.NAME);
            throwables.printStackTrace();
        }
    }

    /**
     * Henter en bruker fra database
     * @param username tar imot brukernavn(String) som skal hentes
     * @return returnerer bruker(User) om den er hentet
     * @throws SQLException SQL feil kastes om den ikke finnes eller lignende.
     */
    public User get(String username) throws SQLException {
        String sql = "SELECT * FROM user WHERE username='"+username+"';";
        ResultSet rs = db.execute(sql);
        if(!rs.next())return null;
        return new User(rs.getString("username"), rs.getString("password"), rs.getBoolean("admin"));
    }
    public ArrayList<User> getAll() throws SQLException {
        String sql = "SELECT * FROM user";
        ResultSet rs = db.execute(sql);
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()){
            users.add(new User(rs.getString("username"), rs.getString("password"), rs.getBoolean("admin")));
        }
        return users;
    }
    public void add(User user) throws SQLException {
        String sql = "INSERT INTO user('username','password','admin') VALUES('" +
                user.getUsername()+ "','"+
                user.getPassword()+ "','"+
                (user.isAdmin() ? 1 : 0)+ "');";
        db.update(sql);
    }
    public void put(User user) throws SQLException{
        String sql = "UPDATE user" +
                "SET password='"+user.getPassword()+"', " +
                    "admin='"+(user.isAdmin()?1:0)+"'"+
                "WHERE username='"+user.getUsername()+"';";
        db.update(sql);
    }
    public void delete(User user) throws SQLException {
        delete(user.getUsername());
    }
    public void delete(String username) throws SQLException {
        String sql = "DELETE FROM user WHERE username='"+username+"';";
        db.update(sql);
    }

    public void deleteAll() throws SQLException {
        String sql = "DELETE FROM user";
        db.update(sql);
    }

    public String getName(){
        return db.NAME;
    }

    public void printUsers(){
        ArrayList<User> users = new ArrayList<>();
        try {
            users = getAll();
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

}
