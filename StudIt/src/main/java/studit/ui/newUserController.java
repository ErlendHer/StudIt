package studit.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import studit.core.User;
import studit.core.UserManager;

public class newUserController {

    @FXML TextField nameField;
    @FXML TextField usernameField;
    @FXML TextField mailField;
    @FXML TextField userPasswordField;
    @FXML Button saveNewUserButton;

    @FXML public void saveNewUserAction() {
        User user = new User();
        user.setName(nameField.getText());
        user.setUsername(usernameField.getText());
        user.setMail(mailField.getText());
        user.setPassword(userPasswordField.getText());
        UserManager.addUser(user);
        Stage stage = (Stage) saveNewUserButton.getScene().getWindow();
        stage.close();
    }
}
