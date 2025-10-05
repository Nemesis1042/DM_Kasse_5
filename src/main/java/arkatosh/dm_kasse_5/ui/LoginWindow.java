package arkatosh.dm_kasse_5.ui;

import arkatosh.dm_kasse_5.core.Database;
import arkatosh.dm_kasse_5.core.Debug;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginWindow {
    private Stage stage;

    public void show(Stage primaryStage) {
        Debug.log("Starte LoginWindow...");
        this.stage = primaryStage;

        Label titleLabel = new Label("Mühle Live POS");
        titleLabel.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");

        TextField userField = new TextField();
        userField.setPromptText("Benutzername");
        userField.setStyle("-fx-font-size: 18px; -fx-pref-height: 45px;");

        PasswordField passField = new PasswordField();
        passField.setPromptText("Passwort");
        passField.setStyle("-fx-font-size: 18px; -fx-pref-height: 45px;");

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.setStyle("-fx-font-size: 16px;");

        Button loginButton = new Button("Login");
        loginButton.setStyle("""
            -fx-background-color: #00a86b;
            -fx-text-fill: white;
            -fx-font-size: 20px;
            -fx-font-weight: bold;
            -fx-pref-width: 200px;
            -fx-pref-height: 50px;
            -fx-cursor: hand;
        """);

        Runnable tryLogin = () -> {
            String user = userField.getText().trim();
            String pass = passField.getText().trim();
            Debug.log("Login-Versuch: " + user);

            if (Database.validateLogin(user, pass)) {
                Debug.log("✅ Login erfolgreich für Benutzer '" + user + "'");
                openMainWindow();
            } else {
                Debug.warn("❌ Fehlgeschlagener Login-Versuch!");
                errorLabel.setText("❌ Ungültiger Benutzer oder Passwort");
            }

        };

        loginButton.setOnAction(e -> tryLogin.run());
        passField.setOnAction(e -> tryLogin.run());

        VBox layout = new VBox(15, titleLabel, userField, passField, loginButton, errorLabel);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));
        layout.setStyle("-fx-background-color: #1e1e2f;");

        Scene scene = new Scene(layout, 500, 400);
        stage.setScene(scene);
        stage.setTitle("Login - Mühle Live POS");
        stage.show();
    }

    private void openMainWindow() {
        Debug.log("Öffne MainWindow...");
        stage.close();
        try {
            Stage mainStage = new Stage();
            MainWindow mainWindow = new MainWindow(mainStage);
            mainWindow.show();
        } catch (Exception e) {
            Debug.error("Fehler beim Öffnen des MainWindow!", e);
        }
    }
}
