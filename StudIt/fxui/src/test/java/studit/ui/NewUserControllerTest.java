package studit.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import studit.ui.remote.ApiCallException;
import studit.ui.remote.DirectStuditModelAccess;

public class NewUserControllerTest extends ApplicationTest {

  private NewUserController controller;
  private DirectStuditModelAccess remote = new DirectStuditModelAccess();

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("NewUser.fxml"));
    final Parent root = loader.load();
    this.controller = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }

  @Test
  public void testLoginController() {
    assertNotNull(this.controller);
  }

  @Test
  public void testSaveNewUserAction() throws ApiCallException {
    controller.setRemote(remote);
    remote.addUser("Ola Halvorsen", "olahalla", "ola.halvorsen@gmail.com", "olalalala");
    writeNewUserFields();
    clickOn("#saveNewUserButton");
    assertEquals("\'olahalla\' is not a unique username", controller.infoTextField.getText());
  }

  public void writeNewUserFields() {
    clickOn("#nameField").write("Ola Halvorsen");
    clickOn("#usernameField").write("olahalla");
    clickOn("#mailField").write("ola.halvorsen@gmail.com");
    clickOn("#userPasswordField").write("olalalala");
  }

}