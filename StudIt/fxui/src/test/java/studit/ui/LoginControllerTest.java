package studit.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.WindowMatchers;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import studit.ui.remote.DirectStuditModelAccess;
import studit.ui.remote.RemoteStuditModelAccess;

public class LoginControllerTest extends ApplicationTest {

  private LoginController loginController;
  private final RemoteStuditModelAccess remote = new DirectStuditModelAccess();

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
    final Parent root = loader.load();
    this.loginController = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }

  @Test
  public void testLoginController() {
    loginController.setRemote(this.remote);
    assertNotNull(this.loginController);
  }

  @Test
  public void testUserPasswordFields() {
    writeUserPassword();
    assertTrue(loginController.usernameField.getText().equals("Berte92"));
  }

  @Test
  public void testLoginButtonActionFail() {
    loginController.setRemote(this.remote);
    writeUserPassword();
    clickOn("#passwordField").write("fweg");
    clickOn("#login_btn");
    assertEquals("Feil brukernavn eller passord", loginController.loginInfoText.getText());
  }

  @Test
  public void testLoginButtonActionSuccess() {
    loginController.setRemote(this.remote);
    LoginController.setTestingMode(true);
    writeUserPassword();
    clickOn("#login_btn");
    FxAssert.verifyThat(window("StudIt"), WindowMatchers.isShowing());
  }

  @Test
  public void testRegisterUserAction() {
    loginController.setRemote(this.remote);
    clickOn("#registerUser_btn");
    FxAssert.verifyThat(window("New User"), WindowMatchers.isShowing());
  }

  @Test
  public void testGetCurrentUser() {
    assertNull(loginController.getCurrentUser());
  }

  @Test
  public void testTestMode() {
    LoginController.setTestingMode(false);
    assertFalse(LoginController.getTestingMode());
  }

  public void writeUserPassword() {
    String u = "Berte92";
    String p = "kusma1992";
    clickOn("#usernameField").write(u);
    clickOn("#passwordField").write(p);
  }

}
