package arkatosh.dm_kasse_5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DM_Kasse_5 extends Application {

    @Override
    public void start(Stage stage) {
        // Ein einfaches Label als GUI
        Label label = new Label("Hallo Kasse 5");
        Scene scene = new Scene(label, 400, 200);

        stage.setTitle("DM Kasse 5");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args); // Startet JavaFX-Anwendung
    }
}
