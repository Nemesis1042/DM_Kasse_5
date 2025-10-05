package arkatosh.dm_kasse_5.ui.tabs;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

public class VerkaufTab extends Tab {

    public VerkaufTab() {
        super("💰 Verkauf");
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.getChildren().add(new Label("Verkauf-Ansicht (Produkte, Warenkorb, Zahlung etc.)"));
        setContent(box);
    }
}
