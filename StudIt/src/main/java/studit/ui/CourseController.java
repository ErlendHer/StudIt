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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import studit.core.chatbot.Chatbot;

public class CourseController implements Initializable { 

    App app = new App();
    Course course = new Course();
    public static Chatbot chatbot = null;


    @FXML private BorderPane rootPane;
    @FXML private FlowPane flowPane;

    @FXML private Button mainPageAction;
    @FXML private Button chatbotAction;
    @FXML private Button ntnuAction;
    @FXML private Button logoutAction;
    @FXML private Button addComment;
    @FXML private Button mainPage_btn;
    @FXML private Button openChatBot;

    @FXML private Label label1;
    @FXML private Label label;

    @FXML private TextArea comment1;
    @FXML private TextArea comment2;
    @FXML private TextArea comment3;
    @FXML private TextArea commentField;



   	@Override
   public void initialize(URL location, ResourceBundle resources) {
   }

   /** 
    * Function to set the label - the name of the subject on the top of the page
    * @param label1 the label to set
    * @return none
    */
   public void setLabel(String label) {
       this.label1.setText(label);
   }
     
    /**
    * logs user out, and goes to login scene
    * closes the current window
    * @return none
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
    * Opens main-page scene and closes the current scene
    * @return none
    */
    @FXML
    void handleMainPageAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            } catch (IOException e) {
                System.out.println(e);
            }
		}
		

    /**
    * Gives the user a choice to open NTNU homepage in web-browser
    * @return none
    */
    @FXML
    void handleNtnuAction(ActionEvent event) {
          System.out.println("NTNU");
    }

    /**
    * Opens Chatbot
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

    
    /** 
    * Posts the comment written in the field to the empty comment section
    * @return none
    */
     @FXML
     void handleAddCommentAction(ActionEvent event) {
          String comment = commentField.getText();

          if(comment1.getText().equals("") || (! comment1.getText().equals("")) && (! comment2.getText().equals("")) && (! comment3.getText().equals(""))){
                comment1.setText(comment);
          }
          else if((! comment1.getText().equals("")) && (comment2.getText().equals(""))){
                comment2.setText(comment);
          }
          else if((! comment1.getText().equals("")) && (! comment2.getText().equals(""))){
              comment3.setText(comment);
          }
   }

   

}
