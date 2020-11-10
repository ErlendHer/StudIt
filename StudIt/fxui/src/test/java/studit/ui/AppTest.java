package studit.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.WindowMatchers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import studit.core.mainpage.CourseItem;
import studit.core.users.User;
import studit.ui.remote.ApiCallException;
import studit.ui.remote.DirectStuditModelAccess;

public class AppTest extends ApplicationTest {

  private DirectStuditModelAccess directStuditModelAccess = new DirectStuditModelAccess();
  private AppController appController = new AppController();
  private ObservableList<CourseItem> list = FXCollections.observableArrayList();
  private ListView<CourseItem> coursesList = appController.coursesList;

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader appLoader = new FXMLLoader(getClass().getResource("App.fxml"));
    LoginController.setTestingMode(true);

    final Parent root = appLoader.load();
    this.appController = appLoader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }

  @Test
  public void testInitialize() throws ApiCallException {
    appController.initialize();
  }

  @Test
  public void testCurrentUser(){
    User currentUser = appController.getCurrentUser();
    assertNotEquals(currentUser, null);
  }

  @BeforeEach
  public void setup() throws ApiCallException {
    list.addAll(directStuditModelAccess.getCourseList().getCourseItems());
    coursesList.setItems(list);
  }

  @Test
  public void hasChatBotButton() {
    BorderPane rootNode = (BorderPane) appController.rootPane.getScene().getRoot();
    Button button = from(rootNode).lookup(".button").query();
    assertEquals("Chatbot", button.getText());
  }

  @Test
  public void testAppController() {
    assertNotNull(this.appController);
  }

  @Test
  public void testSetLabel() {
    String label = "TDT4109";
    appController.setLabel(label);
    Assertions.assertThat(appController.getLabel().equals(label));
  }

  @Test
  public void testListView() {
    assertNotNull(this.coursesList);
  }

  @Test
  public void hasMainPageButton() {
    BorderPane rootNode = (BorderPane) appController.rootPane.getScene().getRoot();
    Button button = from(rootNode).lookup(".button").query();
    assertEquals("Fagoversikt", button.getText());
  }

  @Test
  public void hasLogoutButton() {
    BorderPane rootNode = (BorderPane) appController.rootPane.getScene().getRoot();
    Button button = from(rootNode).lookup("Logg ut").query();
    assertEquals("Logg ut", button.getText());
  }

  @Test
  public void testLogoutAction() {
    clickOn("#logout_btn");
    FxAssert.verifyThat(window("Login"), WindowMatchers.isShowing());
  }

  @Test
  public void testOpenChatBot() {
    clickOn("#chatbot_btn");
    FxAssert.verifyThat(window("Chatbot"), WindowMatchers.isShowing());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testClickOnCourse() {
    ListView<String> listView = (ListView<String>) lookup("#coursesList");
    clickOn(listView.getItems().get(2));
    Assertions.assertThat(listView.getSelectionModel().getSelectedItem().equals("IT1901")); // hvilket fag skal være her
    FxAssert.verifyThat(window("Course"), WindowMatchers.isShowing());
  }

  // @SuppressWarnings("#unchecked")
  // @Test
  // public void hasExactlyNumItems() {
  //   ListView<CourseItem> listView = (ListView<CourseItem>lookup) ("#coursesList");
  //   int numberOfItems = listView.getItems().size();
  //   int actualNumber = 2;
  //   assertEquals(numberOfItems, actualNumber);
  // }

  @Test
  public void testSearch() {
    String name = "Informatikk prosjekt";
    String code = "IT1901";
    appController.searchField.setText("Informatikk");
    CourseItem item2 = appController.coursesList.getItems().get(1);
    assertEquals(item2.getFagnavn(), name);
    assertEquals(item2.getFagkode(), code);
  }
}
