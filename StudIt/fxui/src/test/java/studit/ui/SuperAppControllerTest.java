package studit.ui;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import studit.ui.remote.DirectStuditModelAccess;
import studit.ui.remote.RemoteStuditModelAccess;

public class SuperAppControllerTest extends ApplicationTest {

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
  
}