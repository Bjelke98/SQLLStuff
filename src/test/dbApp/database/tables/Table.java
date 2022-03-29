package test.dbApp.database.tables;

import test.dbApp.database.AppDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class Table {
    protected String tableName;
    protected String primaryKey = null;
    private final String connectionUrl;
    public Table(String tableName, String connectionUrl){
        this.tableName = tableName;
        this.connectionUrl = connectionUrl;
        AppDatabase.tables.put(tableName, this);
    }
    protected void deleteTable(){
        String sql = "DROP TABLE IF EXISTS "+tableName+";";
        try {
            Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate(sql);
            AppDatabase.tables.remove(tableName);
            connection.close();
        } catch (SQLException throwables) {
            System.out.println("Error deleting table "+tableName);
            throwables.printStackTrace();
        }
    }
    protected abstract void createTable();

    protected void createTable(String sql){
        try {
            Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate(sql);
            //System.out.println("Table "+tableName+" created or allready exists");
        } catch (SQLException throwables) {
            System.out.println("Error creating table");
            throwables.printStackTrace();
        }
    }

    public String selectAll(){
        return "SELECT * FROM "+tableName+(primaryKey!=null?" ORDER BY "+primaryKey:"");
    }
    public void addRow(List<String> content){
        StringBuilder elements = new StringBuilder();
        String last = content.get(content.size()-1);
        for (String c : content){
            if(!c.equals(last)){
                elements.append("'");
            }
            elements.append(c);
            if (!c.equals(last)){
                elements.append("'");
                elements.append(", ");
            }
        }
        String sql = "INSERT INTO "+tableName+" VALUES("+elements+");";
        System.out.println(sql);
        try {
            Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException throwables) {
            System.out.println("Error adding row to: "+tableName);
            throwables.printStackTrace();
        }
    }
}
