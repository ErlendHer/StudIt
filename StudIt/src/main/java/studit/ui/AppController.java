package studit.ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import studit.core.chatbot.Chatbot;

public class AppController { 
    
    public static Chatbot chatbot = null;
    ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        loadData();
        coursesList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //Actions on clicked list item
        mouseClicked();
    }

    @FXML
    private BorderPane rootPane;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private ListView<String> coursesList;

     @FXML
    private TextField search_field;

    @FXML
    private Button chatbot_btn;

    @FXML
    private Button ntnu_btn;

    @FXML
    private Button mainPage_btn;

    @FXML
    private Button logout_btn;

    @FXML
    void openChatBot(ActionEvent event) {
        if (chatbot == null) {
    		chatbot = new Chatbot();
    	} else {
    		chatbot.show();
    	}
    }

    public static void closeChatbot() {
        chatbot = null;
    }

    @FXML
    void searchView(ActionEvent event) {

    }

    @FXML
    void ntnuAction(ActionEvent event) {

        //go to NTNU homepage (question if you want to open web-browser)?
        //or a new window with information about NTNU?

    }

     /** Logs user out, and redirects to the login window
    */
    @FXML
    void logoutAction(ActionEvent event) {
        //code
    }


    /** redirects user to the main page
    */
    @FXML
    void mainPageAction() {
        //nothing should really happen when you are in the home page other than maybe refresh(?)
    }


    
    /** A function that does something when a element in the listview is clicked on.
    * @return None
    */
    public void mouseClicked(){
		//Detecting mouse clicked
		coursesList.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				String name = coursesList.getSelectionModel().getSelectedItem();	
                    		
                    try {
                        this.rootPane = new FXMLLoader.load(getClass().getResource("Course.fxml"));

                        // AnchorPane newPane = FXMLLoader.load(getClass().getResource("Course.fxml"));
                        // mainPane.getChildren().addAll(newPane);
                       // rootPane.getChildren().addAll(newPane);

                        } catch (IOException e) {
                            System.out.println(e);
                        }
			}
        });
        return rootPane;
	}
    
    /** This function should actually fetch data from a database. This will be implemented later.
    * @return None
    */
    private void loadData() {
    String a = "TDT4109";
    String b =  "TMA4145";
    String c = "TTM4175";
    String d = "IT1901";
    list.addAll(a,b,c,d);
    coursesList.setItems(list);
    }

}
