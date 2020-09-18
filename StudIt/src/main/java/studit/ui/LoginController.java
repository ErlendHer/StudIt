package studit.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import studit.core.LoginManager;

public class LoginController {

    @FXML PasswordField passwordField;
    @FXML TextField usernameField;
    @FXML Button loginButton;
    @FXML VBox vBox;
    @FXML Text loginInfoMessage;

    public LoginController() {
    }

    /*
     * Initializes the LoginManager with usernames and passwords
    */
    public void initialize() {
        studit.core.UserManager.initialize();
    }

    /*
    * Checks if login credentials are correct, logs in if it is. Else produces an error message in the application
    */
    public void loginButtonAction() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (studit.core.LoginManager.match(username, password)){
            BorderPane pane = FXMLLoader.load(getClass().getResource("App.fxml"));
            Scene scene = new Scene(pane);
            //scene.getStylesheets().add(getClass().getResource("listStyles.css").toExternalForm());
            //The line above works in gitpod, but not in IDEA
            Stage stage = new Stage();
            stage.setScene(scene);
		    stage.setTitle("Hello World");
            stage.show();
            LoginManager.writeToFile(password, "keys.txt");
            //Some way to close te initial window, or load new window instead.
        }
        else{
            System.out.println("Failure, "+username+ ", "+password + " ");
            loginInfoMessage.setText("Error: The username and/or password is incorrect");
        }
    }

    public void newUserButtonAction() {
        try {
            BorderPane pane = FXMLLoader.load(getClass().getResource("newUser.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            System.out.println("Coud not open newUser.fxml");
        }
    }
}
