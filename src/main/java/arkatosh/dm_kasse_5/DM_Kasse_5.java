package arkatosh.dm_kasse_5;

import arkatosh.dm_kasse_5.core.Database;
import arkatosh.dm_kasse_5.core.Debug;
import arkatosh.dm_kasse_5.ui.LoginWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class DM_Kasse_5 extends Application {
    @Override
    public void start(Stage stage) {
        Debug.setEnabled(true);
        Debug.log("Starte DM_Kasse_5...");
        Database.init();  // <- DB-Verbindung aufbauen
        new LoginWindow().show(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
