package studit.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.WindowMatchers;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import studit.core.users.User;
import studit.ui.remote.DirectStuditModelAccess;
import studit.ui.remote.RemoteStuditModelAccess;

public class AppControllerTest extends ApplicationTest {

  private AppController appController;
  private final RemoteStuditModelAccess remote = new DirectStuditModelAccess();

  @Override
  public void start(final Stage stage) throws Exception {
    LoginController.setTestingMode(true);
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));
    final Parent root = loader.load();
    this.appController = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }

  @Test
  public void testAppController() {
    assertNotNull(this.appController);
  }

  @Test
  public void testSettersGetters() {
    User user = new User("Jon", "jojo", "jo@jo.no", "jonnern", 0);
    String string = "testString";
    appController.setCurrentUser(user);
    appController.setLabel(string);
    AppController.setRemote(this.remote);
    assertEquals(user, appController.getCurrentUser());
    assertEquals(string, appController.getLabel());
  }

  // @SuppressWarnings("unchecked")
  @Test
  public void testSearchField() {
    clickOn("#searchField").write("");
    clickOn("#searchField").write("Disk");
  }

  @Test
  public void testLogoutAction() {
    clickOn("#logout_btn");
    FxAssert.verifyThat(window("Login"), WindowMatchers.isShowing());
  }

  @Test
  public void hasLogoutButton() {
    BorderPane rootNode = (BorderPane) appController.rootPane.getScene().getRoot();
    Button button = from(rootNode).lookup("Logg ut").query();
    assertEquals("Logg ut", button.getText());
  }
}