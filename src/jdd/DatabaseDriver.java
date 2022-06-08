package jdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseDriver {
    private Connection conn;

    private DatabaseDriver(){
        String CONNECTION_URL = "jdbc:sqlite:"+"test"+".db";
        try {
            conn = DriverManager.getConnection(CONNECTION_URL);
            System.out.println("DB connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
