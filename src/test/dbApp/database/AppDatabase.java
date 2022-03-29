package test.dbApp.database;

import test.dbApp.database.tables.MedlemTable;
import test.dbApp.database.tables.Table;
import test.dbApp.plattfot.Medlem;

import java.sql.*;
import java.util.HashMap;

public class AppDatabase {
    private static final String connectionUrl = "jdbc:sqlite:Plattfot.db";

    public static HashMap<String, Table> tables = new HashMap<>();

    public AppDatabase() {
        new MedlemTable(connectionUrl);
    }
    public ResultSet getContent(String tableName){
        try {
            Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet rs = statement.executeQuery(tables.get(tableName).selectAll());
            //connection.close();
            return rs;
        } catch (SQLException throwables) {
            System.out.println("Error getting data");
            return null;
        }
    }
    public void addMember(Medlem m){
        Table member = tables.get("Medlem");
        member.addRow(m.toStringList());
    }
}
