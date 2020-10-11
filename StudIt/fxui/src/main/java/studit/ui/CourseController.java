package studit.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import studit.core.chatbot.Chatbot;

public class CourseController implements Initializable {

  @FXML
  private BorderPane rootPane;
  @FXML
  private FlowPane flowPane;
  @FXML
  private Button chatbot_btn;
  @FXML
  private Button ntnu_btn;
  @FXML
  private Button logout_btn;
  @FXML
  private Button addComment_btn;
  @FXML
  private Button mainPage_btn;
  @FXML
  private Label comments;
  @FXML
  private Label label;
  @FXML
  private Label rating;
  @FXML
  private TextArea comment1;
  @FXML
  private TextArea comment2;
  @FXML
  private TextArea comment3;
  @FXML
  private TextArea commentField;
  @FXML
  private TextField search;
  @FXML
  private TextArea courseText;

  private Scene mainScene;

  public void setMainScene(Scene scene) {
    this.mainScene = scene;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    courseText.setEditable(false);
  }

  /**
   * Function to set the label - the name of the subject on the top of the page.
   * @param label the label to set
   */

  @FXML
  public void setLabel(String label) {
    this.label.setText(label);
  }

  /**
   * logs user out, and goes to login scene closes the current window.
   */
  @FXML
  void handleLogoutAction(ActionEvent event) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
      Parent root = loader.load();

      Stage stage2 = new Stage();
      stage2.setScene(new Scene(root));
      stage2.setTitle("StudIt");
      stage2.show();

      Stage stage = (Stage) rootPane.getScene().getWindow();
      stage.hide();

    } catch (IOException e) {
      System.out.println(e);
    }
  }

  /**
   * Opens main-page scene and closes the current scene.
   */
  @FXML
  void handleMainPageAction(ActionEvent actionEvent) {
    try {
      Stage primaryStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
      primaryStage.setScene(mainScene);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * Gives the user a choice to open NTNU homepage in web-browser.
   */
  @FXML
  void handleNtnuAction(ActionEvent event) {
    System.out.println("NTNU");
  }

  /**
   * Opens Chatbot.
   */
  @FXML
  void openChatBot(ActionEvent event) {
    if (AppController.getChatbot() == null) {
      AppController.newChatbot();
    } else {
      AppController.getChatbot().show();
    }
  }

  /**
   * Posts the comment written in the field to the empty comment section.
   */
  @FXML
  void handleAddCommentAction(ActionEvent event) {
    String comment = commentField.getText();

    if (comment1.getText().equals("")
        || (!comment1.getText().equals("")) && (!comment2.getText().equals("")) && (!comment3.getText().equals(""))) {
      comment1.setText(comment);
    } else if ((!comment1.getText().equals("")) && (comment2.getText().equals(""))) {
      comment2.setText(comment);
    } else if ((!comment1.getText().equals("")) && (!comment2.getText().equals(""))) {
      comment3.setText(comment);
    } else if ((!comment1.getText().equals("")) && (!comment2.getText().equals(""))) {
      comment3.setText(comment);
    }
  }

  @FXML
  void setRating(double rating) {
    String r = String.valueOf(rating);
    this.rating.setText(r);
  }

}
