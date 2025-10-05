package arkatosh.dm_kasse_5.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Connection conn;

    public static Connection get() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection("jdbc:sqlite:muehle_live_pos.db");
            conn.createStatement().execute("PRAGMA foreign_keys = ON;");
        }
        return conn;
    }
}
