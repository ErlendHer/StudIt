// package studit.ui;

// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.Test;
// import org.mockito.internal.junit.ExceptionFactory;
// import org.testfx.api.FxToolkit;
// import org.testfx.framework.junit5.ApplicationTest;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.stage.Stage;
// import studit.core.chatbot.Chatbot;
// import javafx.scene.control.Button;
// import javafx.scene.control.ListView;

// public abstract class AppTest extends ApplicationTest {


//     @Before 
//     public void setUpClass() throws Exception {
//         ApplicationTest.launch(Main.class);

//     }


//     @Override
//     public void start(Stage stage) throws ExceptionFactory {
//         stage.show();
//     }


//     @Override
//     public void afterEachTest() throws TimeOutException{
//         FxToolkit.hideStage();
//         release(new KeyCode[]{});
//         release(new MouseButton[]{});
//     }




//     @Override
//     protected Parent getRootNode() {
//         try {
//             return FXMLLoader.load(this.getClass().getResource("App.fxml"));
//         } catch (IOException e) {
//             System.err.println(e);
//         }
//         return null;
//     }

    
//     @Test
//     public void testOpenChatBot() {
       
    
//     }

//     @Test 
//     public void testClickOnCourse() {
//         ListView listView = find("coursesList");
//         clickOn(listView.ListCell).type(text);


//         TextField textField = find("#coursesList");
//         clickOn(textField).type(text);
//         clickOn("#upcaseButton");
//         assertEquals(text.toUpperCase(), textField.getText());
//     }

//     }





//     // @Override
//     // public void start(final Stage stage) throws Exception {
//     //     final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("App.fxml"));
//     //     parent = fxmlLoader.load();
//     //     controller = fxmlLoader.getController();
//     //     stage.setScene(new Scene(parent));
//     //     stage.show();
        
//     //     chatbot = new Chatbot();
//     // }


