package studit.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import studit.core.User;
import studit.core.UserManager;

public class NewUserController {

    @FXML
    TextField nameField;
    @FXML
    TextField usernameField;
    @FXML
    TextField mailField;
    @FXML
    TextField userPasswordField;
    @FXML
    Button saveNewUserButton;
    @FXML
    Text infoTextField;

    /**
     * Makes a new User-object, and sets the four values. Uses the
     * UserManager.addUser to check if the username is taken.
     * 
     * @if the username is taken: Produces an error message. Does not close the
     *     window.
     * @else: Saves the user in the hashmap /database. Closes the window.
     */
    @FXML
    public void saveNewUserAction() {
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
