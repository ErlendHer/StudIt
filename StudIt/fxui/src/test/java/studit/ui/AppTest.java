// package studit.ui;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.testfx.assertions.api.Assertions;
// import org.testfx.api.FxAssert;
// import org.testfx.framework.junit5.ApplicationTest;
// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.stage.Stage;
// import javafx.stage.Window;
// import javafx.scene.control.Button;
// import javafx.scene.control.ListView;
// import javafx.scene.layout.BorderPane;

// public class AppTest extends ApplicationTest {

//   private AppController appController;
//   private CourseController courseController;
//   private ObservableList<String> list = FXCollections.observableArrayList();
//   private ListView<String> listView;
//   String a = "TDT4109";
//   String b = "TMA4145";
//   String c = "TTM4175";
//   String d = "IT1901";

//   @Override
//   public void start(final Stage stage) throws Exception {
//     final FXMLLoader appLoader = new FXMLLoader(getClass().getResource("App.fxml"));
//     final FXMLLoader courseLoader = new FXMLLoader(getClass().getResource("App.fxml"));

//     final Parent root = appLoader.load();
//     this.appController = appLoader.getController();
//     stage.setScene(new Scene(root));
//     stage.show();
//   }

//   @BeforeEach
//   public void setup() throws Exception {
//     list.addAll(a, b, c, d);
//     listView.setItems(list);
//   }

//   @Test
//   public void hasChatBotButton() {
//     BorderPane rootNode = (BorderPane) appController.rootPane.getScene().getRoot();
//     Button button = from(rootNode).lookup(".button").query();
//     assertEquals("Chatbot", button.getText());
//   }

//   @Test
//   public void testAppController() {
//     assertNotNull(this.appController);
//   }

//   @Test
//   public void testCourseController() {
//     assertNotNull(this.courseController);
//   }

//   @Test
//   public void testListView() {
//     assertNotNull(this.listView);
//   }



//   @Test
//   public void hasMainPageButton() {
//     BorderPane rootNode = (BorderPane) appController.rootPane.getScene().getRoot();
//     Button button = from(rootNode).lookup(".button").query();
//     assertEquals("Hjem", button.getText());
//   }

//   @Test
//   public void hasLogoutButton() {
//     BorderPane rootNode = (BorderPane) appController.rootPane.getScene().getRoot();
//     Button button = from(rootNode).lookup("Logg ut").query();
//     assertEquals("Logg ut", button.getText());
//   }

//   @Test
//   public void hasNtnuButton() {
//     BorderPane rootNode = (BorderPane) appController.rootPane.getScene().getRoot();
//     Button button = from(rootNode).lookup("NTNU").query();
//     assertEquals("NTNU", button.getText());
//   }

//   @Test
//   public void testLogoutAction() {
//     Button button = (Button) lookup("#logout_btn");
//     clickOn(button);
//     ObservableList<Window> windows = Window.getWindows();
//     Window login = appController.rootPane.getScene().getWindow();
//     Assertions.assertThat(windows.contains(login));
//   }

//   @Test
//   public void testOpenChatBot() {
//     Button button = (Button) lookup("#chatbot_btn");
//     clickOn(button);
//     ObservableList<Window> windows = Window.getWindows();
//     Window chatbot = appController.rootPane.getScene().getWindow();
//     Assertions.assertThat(windows.contains(chatbot));
//   }

//   @SuppressWarnings("unchecked")
//   @Test
//   public void testClickOnCourse() {
//     ListView<String> listView = (ListView<String>) lookup("#coursesList");
//     clickOn(listView.getItems().get(2));
//     listView.getSelectionModel().getSelectedItem();

//   }

//   @SuppressWarnings("#unchecked")
//   @Test
//   public void hasExactlyNumItems() {
//     ListView<String> listView = (ListView<String>) lookup("#coursesList");
//     int numberOfItems = listView.getItems().size();
//     assertEquals(numberOfItems, 4);
//   }

//   // @Test
//   // public void comment() {
//   // String comment = "Jeg synes dette er et kjedelig fag";
//   // clickOn("#comment_btn");
//   // Assertions.verifyThat(appController.comment1.getText().equals("Jeg synes
//   // dette er et kjedelig fag"));
//   // }

//   @Test
//   public void testClickOnMainPage() {
//     Button button = (Button) lookup("#mainPage_btn");
//     clickOn(button);
//   }
// }
