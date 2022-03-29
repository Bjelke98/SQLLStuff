package test.adrianSample;

import java.io.File;
import java.sql.*;

public class Database {
    private String connectionString;
    private File dbFile;
    public Database(String dbFileName){
        connectionString = "jdbc:sqlite:"+dbFileName+".db";
        dbFile = new File(dbFileName+".db");
        if(!dbFile.exists()) createDatabase();
    }

    public void addUser(String username, String password){
        update("INSERT INTO members(username, password) values("+username+", "+password+");");
    }

    public ResultSet execute(String sql){
        try {
            Connection conn = DriverManager.getConnection(connectionString);
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);
            return statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void update(String sql){
        try {
            Connection conn = DriverManager.getConnection(connectionString);
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void createDatabase(){
        String sql = """
                SELECT 1+1;
                """;
        update(sql);
    }
}
