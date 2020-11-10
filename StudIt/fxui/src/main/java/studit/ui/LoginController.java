package studit.ui;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import studit.core.users.User;
import studit.ui.remote.RemoteStuditModelAccess;

public class LoginController {

  @FXML
  PasswordField passwordField;
  @FXML
  TextField usernameField;
  @FXML
  Button login_btn;
  @FXML
  VBox vBox;
  @FXML
  Text registerUser;
  @FXML
  Text forgotPassword;
  @FXML
  BorderPane rootPane;
  @FXML
  Text loginInfoText;

  private RemoteStuditModelAccess remote;
  private User currentUser;
  private static Boolean testingMode = false;

  public static void setTestingMode(Boolean bol) {
    if (bol) {
      testingMode = true;
    } else {
      testingMode = false;
    }
  }

  public static Boolean getTestingMode() {
    return testingMode;
  }

  /**
   * The constrcutur sets up a remote, to call methods from
   * RemoteStuditModelAccess Frontend needs this class to connect to API/backend.
   */
  public LoginController() {
    this.remote = new RemoteStuditModelAccess();
    this.currentUser = null;
  }

  /**
   * Initializes the listener for ENTER buttin in passwordField.
   */
  public void initialize() {
    listenForEnter();
  }

  /**
   * For testing purposes only. Changes the remote.
   * 
   * @param remote - The new remote to be set
   */
  public void setRemote(RemoteStuditModelAccess remote) {
    this.remote = remote;
  }

  /**
   * Enables other classes in frontend to get the user that is currently logged
   * in.
   * 
   * @return the user used to successfully logged in, else null.
   */
  public User getCurrentUser() {
    return this.currentUser;
  }

  /**
   * Creates a listener for the ENTER button when passwordField is highlited. When
   * ENTER is pressed, the loginButtonAction is triggered. This method is only
   * needed to run once to create the listener.
   */
  public void listenForEnter() {
    passwordField.setOnKeyReleased(new EventHandler<KeyEvent>() {
      public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
          try {
            loginButtonAction();
          } catch (Exception e) {
            System.out.println("Error occured while logging in");
            e.printStackTrace();
          }
        }
      }
    });
  }

  /**
   * Method to register new user. Loads new window: "Newuser.fxml"
   * 
   */
  public void registerUser() throws IOException {
    BorderPane pane = FXMLLoader.load(getClass().getResource("NewUser.fxml"));
    Stage stage = new Stage();
    Scene scene = new Scene(pane);
    stage.setScene(scene);
    stage.setTitle("New User");
    stage.show();
  }

  /**
   * Checks if login credentials are correct, logs in if it is. Else produces an
   * error message in the application
   */
  public void loginButtonAction() throws Exception {
    String username = usernameField.getText();
    String password = passwordField.getText();

    // Check if username and password is correct
    if (remote.authenticateLogin(username, password)) {
      loginInfoText.setText("");
      this.currentUser = remote.getUserByUsername(username);
      FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));
      Parent root = loader.load();

      // Transfers the information about which user logged in to appController
      AppController appController = (AppController) loader.getController();
      appController.setCurrentUser(this.currentUser);

      Scene scene = new Scene(root);
      Stage stage = new Stage();
      stage.setScene(scene);
      stage.setTitle("StudIt");
      stage.show();

      // Hide the login window
      Stage stage2 = (Stage) passwordField.getScene().getWindow();
      stage2.hide();
    } else {
      System.out.print("Failure, " + username + ", " + password + " ");
      loginInfoText.setText("Feil brukernavn eller passord");
    }
  }

}
