package studit.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import studit.ui.remote.ApiCallException;
import studit.ui.remote.RemoteStuditModelAccess;
import studit.ui.remote.DirectStuditModelAccess;

public class NewUserController {

  private RemoteStuditModelAccess remote;

  public NewUserController() {
    this.remote = new RemoteStuditModelAccess();
  }

  /**
   * Function to initialize AppController.
   */
  public void initialize() {
    if (LoginController.getTestingMode()) {
      setRemote(new DirectStuditModelAccess());
    } else {
      System.out.println("Using old remote :( \n");
    }
  }

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
   * For testing purposes only. Changes the remote.
   * 
   * @param remote - The new remote to be set
   */
  public void setRemote(RemoteStuditModelAccess remote) {
    this.remote = remote;
  }

  /**
   * Makes a new User-object, and sets the four values. Uses the
   * UserManager.addUser to check if the username is taken.
   * 
   * @throws ApiCallException
   * 
   * @if the username is taken: Produces an error message. Does not close the
   *     window.
   * @else: Saves the user in the hashmap /database. Closes the window.
   */
  @FXML
  public void saveNewUserAction() throws ApiCallException {
    String name = nameField.getText();
    String username = usernameField.getText();
    String mail = mailField.getText();
    String password = userPasswordField.getText();
    String[] feedback = remote.addUser(name, username, mail, password);

    // Løkken under er bare for testing, kan tas bort i ferdig implementasjon
    for (int i = 0; i < feedback.length; i++) {
      System.out.println("i: " + i + ", message \"" + feedback[i] + "\"");
    }
    if (feedback[1] != null) {
      infoTextField.setText(feedback[1]);
      return;
    }
    Stage stage = (Stage) saveNewUserButton.getScene().getWindow();
    stage.close();
  }

}
