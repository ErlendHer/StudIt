package studit.ui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import studit.core.chatbot.Chatbot;
import studit.core.mainpage.CourseItem;
import studit.core.mainpage.CourseList;
import studit.json.CoursePersistence;

public class AppController implements ChangeListener<String> {

  @FXML
  private ListView<String> coursesList;
  @FXML
  private Button mainPageAction;
  @FXML
  private Button openChatBot;
  @FXML
  private Button ntnuAction;
  @FXML
  private Button logoutAction;
  @FXML
  public BorderPane rootPane;
  @FXML
  private AnchorPane mainPane;
  @FXML
  private TextField searchField;
  @FXML
  private Button mainPage_btn;
  @FXML
  private Button chatbot_btn;
  @FXML
  private Button ntnu_btn;
  @FXML
  private Button logout_btn;

  private static Chatbot chatbot = null;
  public Scene mainScene;
  private ObservableList<String> list = FXCollections.observableArrayList();
  private String label;
  private CoursePersistence coursePersistence = new CoursePersistence();

  public void setSecondScene(Scene scene) {
    this.mainScene = scene;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getLabel() {
    return this.label;
  }

  public static Chatbot getChatbot() {
    return chatbot;
  }

  public static void newChatbot() {
    chatbot = new Chatbot();
  }

  /**
   * Function to initialize AppController.
   */
  public void initialize() {
    loadData();
    coursesList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    // Actions on clicked list item
    mouseClicked();
    // searchField.textProperty().addListener(new
    // ChangeListener<String>.changed(ObservableValue<? extends String>, String,
    // String) {
    // @Override
    // public void changed(ObservableValue observable, Object oldValue, Object
    // newValue) {
    // filterCoursesList((String) oldValue, (String) newValue);
    // }
    // });
  }

  // public void filterCoursesList(String oldValue, String newValue) {

  // ObservableList<String> filteredList = FXCollections.observableArrayList();
  // if (searchField == null || (newValue.length() < oldValue.length()) ||
  // newValue == null) {
  // coursesList.setItems(list);
  // } else {
  // newValue = newValue.toUpperCase();
  // for (String course : coursesList.getItems()) {
  // if (course.toUpperCase().contains(newValue)) {
  // filteredList.add(course);
  // }
  // }
  // coursesList.setItems(filteredList);
  // }
  // }

  /**
   * Opens chatbot.
   */
  @FXML
  void openChatBot(ActionEvent event) {
    if (chatbot == null) {
      chatbot = new Chatbot();
    } else {
      chatbot.show();
    }
  }

  /**
   * closes chatbot.
   */
  public static void closeChatbot() {
    chatbot = null;
  }

  /**
   * Function to search for subjects. The listview will then only show subjects
   * with the letters in the search field.
   */
  @FXML
  public void handleSearchViewAction(String oldVal, String newVal) {
    if (oldVal != null && (newVal.length() < oldVal.length())) {
      coursesList.setItems(list);
    }

    String value = newVal.toUpperCase();

    ObservableList<String> subentries = FXCollections.observableArrayList();
    for (Object entry : coursesList.getItems()) {
      boolean match = true;
      String entryText = (String) entry;
      if (!entryText.toUpperCase().contains(value)) {
        match = false;
        break;
      }
      if (match) {
        subentries.add(entryText);
      }
    }
    coursesList.setItems(subentries);
  }

  /**
   * Should give the option to go to the subjects web-page.
   */
  @FXML
  void handleNtnuAction(ActionEvent event) {

    // go to NTNU homepage (question if you want to open web-browser)?
    // or a new window with information about NTNU?

  }

  /**
   * logs user out, and opens to login scene, closes current scene.
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
   * redirects user to the main page.
   */
  @FXML
  void handleMainPageAction() {
    // nothing should really happen when you are in the home page other than maybe
    // refresh(?)
  }

  /**
   * A function that does something when a element in the listview is clicked on.
   */
  public void mouseClicked() {
    // Detecting mouse clicked
    coursesList.setOnMouseClicked(new EventHandler<MouseEvent>() {
      // private String label;
      @Override
      public void handle(MouseEvent arg0) {
        System.out.println((coursesList.getSelectionModel().getSelectedItem()));
        setLabel(coursesList.getSelectionModel().getSelectedItem());

        try {

          Stage primaryStage = (Stage) ((Node) arg0.getSource()).getScene().getWindow();

          FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("App.fxml"));
          Parent mainPane = mainLoader.load();
          Scene mainScene = new Scene(mainPane);

          // getting loader and a pane for the second scene.
          FXMLLoader courseLoader = new FXMLLoader(getClass().getResource("Course.fxml"));
          Parent coursePane = courseLoader.load();
          Scene courseScene = new Scene(coursePane);

          // injecting first scene into the controller of the second scene
          CourseController courseController = (CourseController) courseLoader.getController();
          courseController.setMainScene(mainScene);

          // injecting second scene into the controller of the first scene
          AppController appController = (AppController) mainLoader.getController();
          courseController.setLabel(coursesList.getSelectionModel().getSelectedItem());
          appController.setSecondScene(courseScene);

          primaryStage.setScene(courseScene);
          primaryStage.setTitle("StudIt");
          primaryStage.show();

          //

        } catch (Exception e) {
          System.out.println(e);
        }
      }
    });
  }

  /**
   * This function should actually fetch data from a database. This will be
   * implemented later.
   */
  private void loadData() {

    try (FileReader fr = new FileReader("src/main/resources/studit/db/db.json", StandardCharsets.UTF_8)) {
      
      CourseList li = coursePersistence.readCourseList(fr);

      System.out.println(li.getCourseItems().size());

      Collection<CourseItem> items = li.getCourseItems();

      System.out.println(items.size());

      for (CourseItem c : items) {
        this.list.add(c.getFagnavn());
      }

      this.coursesList.setItems(this.list);
    
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

  }

  
  /**
   * This function returns the list of subjects.
   * @return list;
   */
  public ObservableList<String> getData() {
    return (ObservableList<String>) list;
  }

  @Override
  public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

  }
}
