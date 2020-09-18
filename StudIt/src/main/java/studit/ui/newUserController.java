package studit.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import studit.core.User;
import studit.core.UserManager;

public class newUserController {

    @FXML TextField nameField;
    @FXML TextField usernameField;
    @FXML TextField mailField;
    @FXML TextField userPasswordField;
    @FXML Button saveNewUserButton;
    @FXML Text infoTextField;

    @FXML public void saveNewUserAction() {
        User user = new User();
        user.setName(nameField.getText());
        user.setUsername(usernameField.getText());
        user.setMail(mailField.getText());
        user.setPassword(userPasswordField.getText());
        boolean successfullyAdded = UserManager.addUser(user);
        Stage stage = (Stage) saveNewUserButton.getScene().getWindow();
        if (successfullyAdded) {
            stage.close();
            return;
        }
        infoTextField.setText("Error: This username is already taken");
    }
}
