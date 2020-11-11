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
import javafx.stage.Stage;
import studit.core.mainpage.CourseItem;
import studit.core.users.User;

public class CourseController implements Initializable {

  private User currentUser;
  private CourseItem courseItem;

  @FXML
  private BorderPane rootPane;

  @FXML
  private Button information_btn;

  @FXML
  private Button discussion_btn;

  @FXML
  private Label fagkode;

  @FXML
  private Label fagnavn;

  @FXML
  private Label rating;

  @FXML
  private TextArea courseInformation;

  @FXML
  private TextArea litterature;

  @FXML
  private TextArea tips_tricks;

  @FXML
  private Button mainPageAction;

  @FXML
  private Button chatbot_btn;

  @FXML
  private Button logoutAction;

  @FXML
  private TextField eksamensdato;

  @FXML
  private TextField vurderingsform;

  @FXML
  private TextField hjelpemidler;

  @FXML
  private TextArea commentField;

  @FXML
  private Button addCommentAction;

  // private Scene mainScene;

  

  public void setCourseItem(CourseItem courseItem) {
    this.courseItem = courseItem;
  }

  public void setHjelpemidler(String hjelpemidler) {
    this.hjelpemidler.setText(hjelpemidler);
  }

  public void setVurderingsForm(String vurderingsform) {
    this.vurderingsform.setText(vurderingsform);
  }

  public void setEksamensdato(String eksamensdato) {
    this.eksamensdato.setText(eksamensdato);
  }

  public void setTips(String tips) {
    this.tips_tricks.setText(tips);
  }

  public void setLitterature(String litteratur) {
    this.litterature.setText(litteratur);
  }

  /*
  public void setRating(Float rating) {
    this.rating.setText(rating.toString());
  } */

  // public void setMainScene(Scene scene) {
  // this.mainScene = scene;
  // }

  public void setFagkode(String fagkode) {
    this.fagkode.setText(fagkode);
  }

  /**
   * Function to set the label - the name of the subject on the top of the page.
   */

  @FXML
  public void setFagnavn(String fagnavn) {
    this.fagnavn.setText(fagnavn);
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
      stage2.setTitle("Login");
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
      FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));
      Parent root = loader.load();

      Stage stage2 = new Stage();
      stage2.setScene(new Scene(root));
      stage2.setTitle("App");
      stage2.show();

      Stage stage = (Stage) rootPane.getScene().getWindow();
      stage.hide();

    } catch (IOException e) {
      System.out.println(e);
    }
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
   * Sets the rating of the subject.
   * 
   * @param rating represents the value of the rating to be set
   */
  @FXML
  public void setRating(double rating) {
    String r = String.valueOf(rating);
    this.rating.setText(r);
  }

  /**
   * returns the rating of the subject.
   */
  @FXML
  public double getRating() {
    return Double.valueOf(this.rating.getText());
  }

  @FXML
  public void setCourseInformation(String information) {
    this.courseInformation.setText(information.toString());
  }

  @FXML
  void openDiscussion(ActionEvent event) {

    if (this.courseItem != null) {

      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Discussion.fxml"));
        Parent root = loader.load();
        DiscussionController discussionController = (DiscussionController) loader.getController();

        // TODO sjekke om den nede funker? Virker som det ikke gjør det.
        if (this.courseItem != null) {
          discussionController.addCourse(this.courseItem);
          discussionController.setCurrentUser(this.currentUser);
      
          System.out.println("addet courseItem");
          discussionController.updateView();
        }

        Stage stage2 = new Stage();
        stage2.setScene(new Scene(root));
        stage2.setTitle("Discussion");
        stage2.show();

        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.hide();

      } catch (IOException e) {
        System.out.println(e);
      }

    } else {

      System.out.println("Kunne ikke gå til diskusjon med riktig informasjon");

    }
  }

  // TODO funker ikke :(
  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  public void updateView() {
    if (this.courseItem != null) {
      this.fagnavn.setText(courseItem.getFagnavn());
      this.fagkode.setText(courseItem.getFagkode());
      this.hjelpemidler.setText(courseItem.getHjelpemidler());
      this.litterature.setText(courseItem.getPensumlitteratur());
      this.rating.setText(String.valueOf(courseItem.getAverageVurdering()));
      this.vurderingsform.setText(courseItem.getVurderingsform());
      this.tips_tricks.setText(courseItem.getTips());
      this.courseInformation.setText(courseItem.getInformasjon());
      this.eksamensdato.setText(courseItem.getEksamensdato());

    }
  }

  public void setCurrentUser(User user) {
    this.currentUser = user;
  }

}
