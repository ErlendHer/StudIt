// package studit.ui;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import java.util.ArrayList;

// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.internal.junit.ExceptionFactory;
// import org.testfx.api.FxAssert;
// import org.testfx.api.FxRobot;
// import org.testfx.api.FxToolkit;
// import org.testfx.framework.junit5.ApplicationTest;
// import org.testfx.matcher.base.WindowMatchers;

// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.stage.Stage;
// import studit.core.chatbot.Chatbot;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.ListCell;
// import javafx.scene.control.ListView;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.BorderPane;
// import javafx.scene.layout.StackPane;

// public class AppTest extends ApplicationTest {

//     private AppController appController;
//     private App app;
//     private Course course;
//     private ObservableList<String> list = FXCollections.observableArrayList();
//     private ListView<String> listView = new ListView<>();

//     @Override
//         public void start(final Stage stage) throws Exception {
//             final FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));
//             final Parent root = loader.load();
//             this.appController = loader.getController();
//             this.list =  this.appController.getData();
//             stage.setScene(new Scene(root));
//             stage.show();
//     }


//     @BeforeEach
//     public void setup() throws Exception {
//         listView.setItems(list);        
//     }

    
//     @Test
//     public void hasChatBotButton() {
//         BorderPane rootNode = (BorderPane) appController.rootPane.getScene().getRoot();
//         Button button = from(rootNode).lookup(".button").query();
//         assertEquals("Chatbot", button.getText());
//     }

//     @Test
//     public void hasMainPageButton() {
//         BorderPane rootNode = (BorderPane) appController.rootPane.getScene().getRoot();
//         Button button = from(rootNode).lookup(".button").query();
//         assertEquals("Hjem", button.getText());
//     }

//     @Test
//     public void hasLogoutButton() {
//         BorderPane rootNode = (BorderPane) appController.rootPane.getScene().getRoot();
//         Button button = from(rootNode).lookup(".button").query();
//         assertEquals("Logg ut", button.getText());
//     }

//     @Test
//     public void testLogoutAction() {
//         FxAssert.verifyThat(window("Login"), WindowMatchers.isShowing());
//     }
    
//     @Test
//     public void testOpenChatBot() {
//         FxAssert.verifyThat(window("Chatbot"), WindowMatchers.isShowing());
//     }

    
//     @Test 
//     public void testClickOnCourse() {
//         ListView<String> coursesList = find("#coursesList");
//         clickOn(ListCell).type(text);
        
//     }

//     // @Test
//     // public void hasListCell() {
//     //     verifyThat(listView).hasListCell("TDT4109");
//     // }

//     @Test
//     public void hasExactlyNumItems() {
//         int numberOfItems = listView.getItems().size();
//         assertEquals(numberOfItems, 4);
//     }

//     @Test 
//     public void testClickOnMainPage() {
//         Button button = find("#mainPage_btn");
//         clickOn(button);
//         verify
        
//     }

 

//     }









