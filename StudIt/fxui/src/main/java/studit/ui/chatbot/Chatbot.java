package studit.ui.chatbot;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import studit.core.chatbot.ChatbotManager;
import studit.core.chatbot.Response;
import studit.core.mainpage.CourseList;
import studit.ui.remote.ApiCallException;
import studit.ui.remote.RemoteStuditModelAccess;

public class Chatbot {

  private Stage chatStage;
  private ChatbotManager chatbotManager;

  public Chatbot() {
    displayWindow();
    RemoteStuditModelAccess remoteAccess = new RemoteStuditModelAccess();
    try {
      CourseList courseList = remoteAccess.getCourseList();
      chatbotManager = new ChatbotManager(courseList.getCourseNameList());
    } catch (ApiCallException e) {
      e.printStackTrace();
    }
  }

  /*
   * Opens a new window for our chatbot
   */
  private void displayWindow() {
    Parent root;
    try {
      root = FXMLLoader.load(getClass().getResource("/studit/ui/Chatbot.fxml"));
      chatStage = new Stage();
      Scene scene = new Scene(root);

      // Setting the background to be transparent, so we can create rounded corners in
      // our css file
      chatStage.initStyle(StageStyle.TRANSPARENT);
      scene.setFill(Color.TRANSPARENT);

      scene.getStylesheets().setAll(getClass().getResource("/studit/ui/chatbot.css").toExternalForm());
      chatStage.setScene(scene);
      chatStage.setTitle("Chatbot");
      chatStage.show();
    } catch (IOException e) {
      System.out.println("Error loading ChatbotController.FXML -> Is the file corrupt?");
      e.printStackTrace();
    } catch (NullPointerException e) {
      // Doing this to prevent testing errors
    }

  }

  public void show() {
    chatStage.setIconified(false);
  }

  /*
   * Manages the user entered input and executes commands accordingly.
   * 
   * @param input - the user input we want to process
   * 
   * @return chatbot response
   */
  public Response manageInput(String input) {
    return chatbotManager.manageInput(input);
  }

}
