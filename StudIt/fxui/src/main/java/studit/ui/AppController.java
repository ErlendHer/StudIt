package studit.ui;

import java.io.IOException;
import java.util.Collection;
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
import javafx.scene.input.KeyEvent;
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

  private static RemoteStuditModelAccess remoteStuditModelAccess = new RemoteStuditModelAccess();

  /*
   * The user that is currently logged in.
   */
  private User currentUser = null;

  @FXML 
  BorderPane rootPane;

  @FXML
  private TextField searchField;

  @FXML
  private ListView<CourseItem> coursesList;

  @FXML
  private Button chatbot_btn;

  @FXML
  private Button logoutAction;

  private static Chatbot chatbot = null;

  private ObservableList<CourseItem> list = FXCollections.observableArrayList();

  private FilteredList<CourseItem> filteredData = new FilteredList<>(this.getData(), (p -> true));

  private SortedList<CourseItem> sortedData = new SortedList<>(filteredData);

  private ObservableList<CourseItem> filteredList = FXCollections.observableArrayList();

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
    chatbot = new Chatbot(remoteStuditModelAccess);
  }

  public static void newChatbot(boolean directAccess) throws ApiCallException {
    chatbot = directAccess ? new Chatbot(true) : new Chatbot(remoteStuditModelAccess);
  }

  /**
   * For testing purposes only. Changes the remote.
   * 
   * @param remote - The new remote to be set
   */
  public static void setRemote(RemoteStuditModelAccess remote) {
    remoteStuditModelAccess = remote;
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

    //This check is only used for testing purposes in order to set the RemoteStuditModel to DirectStuditModelAccess
    if (LoginController.getTestingMode() || DiscussionController.getTestingMode()) {
      setRemote(new DirectStuditModelAccess());
    }
    loadData();
    coursesList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    // Actions on clicked list item
    clickOnCourse();
    initializeSearch();
  }


  /**
   * Function to initialize the search function.
   */
  public void initializeSearch() {
    // Set the filter Predicate whenever the filter changes.
    searchField.textProperty().addListener((observable, oldValue, newValue) -> {

      this.filteredData.setPredicate(courseItem -> {
        // If filter text is empty, display all courses
        if (newValue == null || newValue.isEmpty()) {
          return true;
        }
        // Compare course name and course code of every CourseItem with the filter text
        String lowerCaseFilter = newValue.toLowerCase();

        if ((courseItem.getFagnavn().toLowerCase().contains(lowerCaseFilter))
            || (courseItem.getFagkode().toLowerCase().contains(lowerCaseFilter))) {
          return true; // filter matches course name or course code
        }
        return false; // Does not match
      });
    });

    filteredList.setAll(sortedData);
    this.coursesList.setItems(filteredList);

  }

  /**
   * Function to search for subjects. The listview will then only show subjects
   * with the letters in the search field.
   */
  @FXML
  public void handleSearchFieldAction(KeyEvent e) {
    filteredList.setAll(sortedData);
    this.coursesList.setItems(filteredList);
  }

  /**
   * Opens chatbot.
   */
  @FXML
  void openChatBot(ActionEvent event) {
    if (chatbot == null) {
      chatbot = new Chatbot(remoteStuditModelAccess);
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
      e.printStackTrace();
    }
  }

  /**
   * A function that does something when an element in the listview is clicked on.
   */
  public void clickOnCourse() {
    // Detecting mouse clicked
    coursesList.setOnMouseClicked(new EventHandler<MouseEvent>() {
      // private String label;
      @Override
      public void handle(MouseEvent arg0) {

        try {
         
          // getting loader and a pane for the course scene.
          FXMLLoader courseLoader = new FXMLLoader(getClass().getResource("Course.fxml"));
          Parent coursePane = courseLoader.load();
          CourseController courseController = (CourseController) courseLoader.getController();
          CourseItem courseItem = coursesList.getSelectionModel().getSelectedItem();
          courseController.setCourseItem(courseItem);
          courseController.setCurrentUser(currentUser);
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


  /**
   * Function that loads the courses from the server.
   *
   * @throws ApiCallException If connection to server could not be established.
   */
  private void loadData() throws ApiCallException {

    CourseList li = remoteStuditModelAccess.getCourseList();

    Collection<CourseItem> items = li.getCourseItems();

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
