package arkatosh.dm_kasse_5.ui.tabs;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

public class PfandTab extends Tab {
    public PfandTab() {
        setText("Pfand");
        VBox box = new VBox(10);
        box.getChildren().add(new Label("Pfand-Rückgabe"));
        setContent(box);
    }
}
