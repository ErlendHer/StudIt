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
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import studit.core.chatbot.Chatbot;

public class AppController { 

    @FXML private ListView<String> coursesList;
    @FXML private Button mainPageAction;
    @FXML private Button openChatBot;
    @FXML private Button ntnuAction;
    @FXML private Button logoutAction;
    @FXML private BorderPane rootPane;
    @FXML private AnchorPane mainPane;

    public static Chatbot chatbot = null;
    ObservableList<String> list = FXCollections.observableArrayList();

    /**
    * Function to initialize AppController
    * @return none
    */
    @FXML
    public void initialize() {
        loadData();
        coursesList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //Actions on clicked list item
        mouseClicked();
    }
    
   
    /**
    * Gives the user a choice to open NTNU homepage in web-browser
    * @return none
    */
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

    /**
    * Function to search for subjects. The listview will then only show subjects with 
    * the letters in the search field
    * @return none
    */
    @FXML
    void searchView(ActionEvent event) {

    }

    /**
    * Should give the option to go to the subjects web-page
    * @return none
    */
    @FXML
    void handleNtnuAction(ActionEvent event) {

        //go to NTNU homepage (question if you want to open web-browser)?
        //or a new window with information about NTNU?

    }

     
     /** 
     * logs user out, and opens to login scene, closes current scene
     */
    @FXML
    void handleLogoutAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();

            Stage controllerStage = (Stage) mainPageAction.getScene().getWindow();
            // do what you have to do
            controllerStage.hide();
        
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            
             

            } catch (IOException e) {
                System.out.println(e);
            }
	}


    /** 
    * redirects user to the main page
    * @return none
    */
    @FXML
    void handleMainPageAction() {
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
                    System.out.println(name);	
                    		
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Course.fxml"));
                        Parent root = loader.load();
   
                    
                        CourseController courseController = loader.getController();
                        courseController.setLabel(name);
   
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
   
                        } catch (IOException e) {
                            System.out.println(e);
                        }
			}
		});
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
