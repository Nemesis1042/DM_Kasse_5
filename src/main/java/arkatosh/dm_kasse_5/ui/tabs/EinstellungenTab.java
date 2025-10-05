package arkatosh.dm_kasse_5.ui.tabs;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

public class EinstellungenTab extends Tab {
    public EinstellungenTab() {
        setText("Einstellungen");
        VBox box = new VBox(10);
        box.getChildren().add(new Label("Systemeinstellungen"));
        setContent(box);
    }
}
