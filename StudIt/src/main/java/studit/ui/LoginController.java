package studit.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
            VBox box = FXMLLoader.load(getClass().getResource("App.fxml"));
            Scene scene = new Scene(box);
            scene.getStylesheets().add(getClass().getResource("listStyles.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
		    stage.setTitle("Hello World");
            stage.show();
            //Some way to close te initial window, or load new window instead.
        }
        else{
            System.out.print("Failure, "+username+ ", "+password + " ");
        }
    }





}