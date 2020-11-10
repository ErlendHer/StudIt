package studit.ui;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import studit.core.mainpage.CourseItem;
import studit.core.mainpage.CourseList;
import studit.core.users.User;
import studit.ui.chatbot.Chatbot;
import studit.ui.remote.ApiCallException;
import studit.ui.remote.DirectStuditModelAccess;
import studit.ui.remote.RemoteStuditModelAccess;

public class AppController {

  private RemoteStuditModelAccess remoteStuditModelAccess = new RemoteStuditModelAccess();

  private User user = new User("Ida Idasen", "IdaErBest", "IdaElskerHunder@flyskflysk.com",
      "0f0b30a66731e73240b9e331116b57de84f715ab2aea0389bb68129fcf099da3", 1);

  /*
   * The user that is currently logged in.
   */
  private User currentUser = null;

  @FXML BorderPane rootPane;

  @FXML TextField searchField;

  @FXML ListView<CourseItem> coursesList;

  @FXML
  private Button chatbot_btn;

  @FXML
  private Button logoutAction;

  private static Chatbot chatbot = null;

  private ObservableList<CourseItem> list = FXCollections.observableArrayList();

  private List<CourseItem> courseList;

  private String label;

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

  public void addUser(User user) {
    this.user = user;
  }

  /**
   * For testing purposes only. Changes the remote.
   * @param remote - The new remote to be set
   */
  public void setRemote(RemoteStuditModelAccess remote) {
    this.remoteStuditModelAccess = remote;
  }

  /**
   * Sets the current user (the user that is logged in from the input).
   * 
   * @param user The user that should be set as the current user.
   */
  public void setCurrentUser(User user) {
    this.currentUser = user;
  }

  /**
   * Gets the user that is currently logged in.
   * 
   * @return The current user
   */
  public User getCurrentUser() {
    return this.currentUser;
  }

  /**
   * Function to initialize AppController.
   *
   * @throws ApiCallException If connection to server could not be established.
   */
  public void initialize() throws ApiCallException {
    if (LoginController.getTestingMode()) {
      setRemote(new DirectStuditModelAccess());
    } else {
      System.out.println("Using old remote :( \n");
    }
    loadData();
    coursesList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    // Actions on clicked list item
    mouseClicked();
    setSearch();
  }



  public void setSearch(){
    // Wrap the ObservableList in a FilteredList (initially display all data).
    FilteredList<CourseItem> filteredData = new FilteredList<>(this.getData(), (p -> true));
    SortedList<CourseItem> sortedData = new SortedList<>(filteredData);
    ObservableList<CourseItem> filteredList = FXCollections.observableArrayList();
  }


  /**
   * Function to search for subjects. The listview will then only show subjects
   * with the letters in the search field.
   */
  @FXML
  public void handleSearchFieldAction() {
    // Set the filter Predicate whenever the filter changes.
    searchField.textProperty().addListener((observable, oldValue, newValue) -> {

      filteredData.setPredicate(courseItem -> {
        // If filter text is empty, display all courses
        if (newValue == null || newValue.isEmpty()) {
          System.out.println("textfield is empty");
          return true;
        }
        // Compare course name and course code of every CourseItem with the filter text
        String lowerCaseFilter = newValue.toLowerCase();

        if ((courseItem.getFagnavn().toLowerCase().contains(lowerCaseFilter))
            || (courseItem.getFagkode().toLowerCase().contains(lowerCaseFilter))) {

          System.out.println("textfield changed from " + oldValue + " to " + newValue);
          return true; // filter matches course name or course code
        }

        System.out.println("no match");
        return false; // Does not match
      });
    });
   
    filteredList.setAll(sortedData);
    this.coursesList.setItems(filteredList);
  }

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
   * logs user out, and opens to login scene, closes current scene.
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
   * A function that does something when an element in the listview is clicked on.
   */
  public void mouseClicked() {
    // Detecting mouse clicked
    coursesList.setOnMouseClicked(new EventHandler<MouseEvent>() {
      // private String label;
      @Override
      public void handle(MouseEvent arg0) {

        try {
          
          // getting loader and a pane for the second scene.
          FXMLLoader courseLoader = new FXMLLoader(getClass().getResource("Course.fxml"));
          Parent coursePane = courseLoader.load();
          // injecting first scene into the controller of the second scene
          CourseController courseController = (CourseController) courseLoader.getController();
          // courseController.setMainScene(mainScene);
          // injecting second scene into the controller of the first scene
          CourseItem courseItem = coursesList.getSelectionModel().getSelectedItem();
          courseController.setCourseItem(courseItem);
          courseController.setCurrentUser(user);
          courseController.updateView();

          Stage primaryStage = (Stage) ((Node) arg0.getSource()).getScene().getWindow();
          Scene courseScene = new Scene(coursePane);

          primaryStage.setScene(courseScene);
          primaryStage.setTitle("Course");
          primaryStage.show();

        } catch (Exception e) {
          System.out.println(e);
        }
      }
    });
  }

  private CourseItem findCourseItem(String name) {
    for (CourseItem courseItem : this.courseList) {
      if (courseItem.getFagnavn().equals(name)) {
        return courseItem;
      }
    }
    return null;
  }

  /**
   * This function should actually fetch data from a database. This will be
   * implemented later.
   *
   * @throws ApiCallException If connection to server could not be established.
   */
  private void loadData() throws ApiCallException {
    CourseList li = remoteStuditModelAccess.getCourseList();

    Collection<CourseItem> items = li.getCourseItems();
    this.courseList = (List<CourseItem>) items;

    for (CourseItem c : items) {
      this.list.add(c);
    }

    this.coursesList.setItems(this.list);

    coursesList.setCellFactory(param -> new ListCell<CourseItem>() {

      @Override
      public void updateItem(CourseItem item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
          setText(null);
          setGraphic(null);
          return;
        }

        setText(item.getFagkode() + " " + item.getFagnavn());
        setGraphic(null);
      }
    });

  }

  /**
   * This function returns the list of subjects.
   *
   * @return list;
   */
  public ObservableList<CourseItem> getData() {
    return list;
  }

}
