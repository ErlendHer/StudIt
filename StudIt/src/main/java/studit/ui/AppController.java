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
    private ListView<String> coursesList;

     @FXML
    private TextField search_field;

    @FXML
    private Button chatbot_btn;

    @FXML
    private Button ntnu_btn;

    @FXML
    private Button contact_btn;

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

    }

    @FXML
    void logoutAction(ActionEvent event) {

    }

    @FXML
    void contactAction(ActionEvent event) {

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
                        courseController.setLabelText(name);
   
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
