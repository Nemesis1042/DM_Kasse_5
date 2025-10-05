package arkatosh.dm_kasse_5.core;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Debug {
    private static boolean enabled = true;
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    /** Aktiviert oder deaktiviert Debug-Ausgaben */
    public static void setEnabled(boolean state) {
        enabled = state;
        log("Debug-Modus: " + (enabled ? "AKTIV" : "INAKTIV"));
    }

    /** Gibt eine normale Logmeldung aus */
    public static void log(String msg) {
        if (!enabled) return;
        System.out.println(color("[LOG " + now() + "] ", "37") + msg);
    }

    /** Gibt eine Warnung aus */
    public static void warn(String msg) {
        if (!enabled) return;
        System.out.println(color("[WARN " + now() + "] ", "33") + msg);
    }

    /** Gibt eine Fehlermeldung aus */
    public static void error(String msg, Exception e) {
        if (!enabled) return;
        System.err.println(color("[ERROR " + now() + "] ", "31") + msg);
        if (e != null) e.printStackTrace(System.err);
    }

    /** Aktuelle Uhrzeit formatiert */
    private static String now() {
        return LocalTime.now().format(TIME_FORMAT);
    }

    /** ANSI-Farben für Konsole */
    private static String color(String prefix, String colorCode) {
        return "\u001B[" + colorCode + "m" + prefix + "\u001B[0m";
    }
}
