package dbms.database;

import java.sql.*;
import java.util.HashMap;

public class Database {

    private static final HashMap<String, Database> DATABASES = new HashMap<>();

    private final String CONNECTION_URL;

    public final String NAME;

    private Connection conn;

    private Database(String dbname){
        CONNECTION_URL = "jdbc:sqlite:"+dbname+".db";
        this.NAME = dbname;
        DATABASES.put(dbname, this);
    }

    public static Database getDatabase(String dbname){
        if(DATABASES.containsKey(dbname)){
            return DATABASES.get(dbname);
        }
        return new Database(dbname);
    }

    public ResultSet execute(String sql){
        try {
            conn = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(10);
            return statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void update(String sql) throws SQLException {
        conn = DriverManager.getConnection(CONNECTION_URL);
        Statement statement = conn.createStatement();
        statement.setQueryTimeout(10);
        statement.executeUpdate(sql);
    }



//    public ArrayList<HashMap<String, String>> select(String sql, String... col) throws SQLException {
//        System.out.println(sql);
//        ArrayList<HashMap<String, String>> table = new ArrayList<>();
//        Connection conn = DriverManager.getConnection(CONNECTION_URL);
//        Statement statement = conn.createStatement();
//        statement.setQueryTimeout(30);
//        ResultSet rs = statement.executeQuery(sql);
//        while (rs.next()){
//            HashMap<String, String> kv = new HashMap<>();
//
//            for (String c : col){
//                String v = rs.getString(c);
//                kv.put(c, v);
//            }
//
//            table.add(kv);
//        }
//        rs.close();
//        return table;
//    }

}
