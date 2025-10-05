package arkatosh.dm_kasse_5.ui;

import arkatosh.dm_kasse_5.ui.tabs.EinstellungenTab;
import arkatosh.dm_kasse_5.ui.tabs.PfandTab;
import arkatosh.dm_kasse_5.ui.tabs.ProdukteTab;
import arkatosh.dm_kasse_5.ui.tabs.StatistikTab;
import arkatosh.dm_kasse_5.ui.tabs.VerkaufTab;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindow {

    private final Stage stage;

    // ✅ Konstruktor mit Stage
    public MainWindow(Stage stage) {
        this.stage = stage;
    }

    // ✅ Methode ohne Parameter (LoginWindow ruft so auf)
    public void show() {
        TabPane tabs = new TabPane();

        // Beispielhafte Tabs – du kannst später echte Inhalte reinbauen
        tabs.getTabs().addAll(
            new VerkaufTab(),
            new PfandTab(),
            new ProdukteTab(),
            new StatistikTab(),
            new EinstellungenTab()
        );

        BorderPane root = new BorderPane(tabs);
        Scene scene = new Scene(root, 1280, 800);

        // CSS laden (achte: style.css muss unter src/main/resources liegen!)
        var css = getClass().getResource("/style.css");
        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        }

        stage.setTitle("Mühle Live POS");
        stage.setScene(scene);
        stage.show();
    }
}
