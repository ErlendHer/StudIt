package studit.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class LoginController {

    @FXML
    PasswordField passwordField;
    @FXML
    TextField usernameField;
    @FXML
    Button loginButton;
    @FXML VBox vBox;

    public LoginController() {
    }

    public void initialize() {
        studit.core.LoginManager.initialize();
    }

    /// Checks if login creditentials are correct, and does an action corresponding
    public void loginButtonAction() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (studit.core.LoginManager.match(username, password)){
            System.out.print("Success ");
            AnchorPane pane = FXMLLoader.load(getClass().getResource("App.fxml"));
            vBox.getChildren().setAll(pane);
        }
        else{
            System.out.print("Failure, "+username+ ", "+password + " ");
        }
    }





}