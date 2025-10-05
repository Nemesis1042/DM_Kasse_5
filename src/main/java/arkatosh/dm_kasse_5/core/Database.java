package arkatosh.dm_kasse_5.core;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static Connection connection;

    // SQLite-Datei
    private static final String DB_PATH = "data/pos.db";

    /** Initialisiert Datenbank und Tabellen */
    public static void init() {
        try {
            Debug.log("Initialisiere Datenbankverbindung...");

            // Ordner erstellen, falls nötig
            File dbFile = new File(DB_PATH);
            dbFile.getParentFile().mkdirs();

            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
            Debug.log("Verbindung hergestellt: " + DB_PATH);

            // Tabellen erstellen, falls nicht vorhanden
            createTables();

        } catch (SQLException e) {
            Debug.error("SQL-Fehler bei der Initialisierung der Datenbank!", e);
        } catch (Exception e) {
            Debug.error("Unerwarteter Fehler bei der Datenbank-Initialisierung!", e);
        }
    }

    /** Erstellt benötigte Tabellen (nur beim ersten Start) */
    private static void createTables() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT UNIQUE NOT NULL,
                password TEXT NOT NULL,
                role TEXT DEFAULT 'admin'
            );
        """;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            Debug.log("Tabellenstruktur geprüft/erstellt.");

            // Standardbenutzer anlegen, falls keiner existiert
            ensureDefaultUser();
        }
    }

    /** Fügt einen Admin-Account hinzu, falls Datenbank leer ist */
    private static void ensureDefaultUser() throws SQLException {
        String checkSql = "SELECT COUNT(*) FROM users;";
        try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(checkSql)) {

            if (rs.next() && rs.getInt(1) == 0) {
                Debug.log("Keine Benutzer gefunden — Erstelle Standard-Admin...");
                addUser("admin", "password");
            }
        }
    }

    /** Fügt neuen Benutzer hinzu */
    public static void addUser(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            Debug.log("Benutzer '" + username + "' hinzugefügt.");
        } catch (SQLException e) {
            Debug.error("Fehler beim Hinzufügen eines Benutzers!", e);
        }
    }

    /** Prüft Login-Daten gegen die Datenbank */
    public static boolean validateLogin(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            boolean valid = rs.next();
            Debug.log("Login-Check für '" + username + "': " + (valid ? "✅ gültig" : "❌ ungültig"));
            return valid;
        } catch (SQLException e) {
            Debug.error("Fehler beim Prüfen der Login-Daten!", e);
            return false;
        }
    }

    /** Schließt Verbindung */
    public static void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                Debug.log("Datenbankverbindung geschlossen.");
            }
        } catch (SQLException e) {
            Debug.error("Fehler beim Schließen der Datenbankverbindung!", e);
        }
    }
}
