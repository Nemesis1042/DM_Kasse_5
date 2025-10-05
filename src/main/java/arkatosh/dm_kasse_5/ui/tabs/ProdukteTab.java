package arkatosh.dm_kasse_5.ui.tabs;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

public class ProdukteTab extends Tab {
    public ProdukteTab() {
        setText("Produkte");
        VBox box = new VBox(10);
        box.getChildren().add(new Label("Produktverwaltung"));
        setContent(box);
    }
}
