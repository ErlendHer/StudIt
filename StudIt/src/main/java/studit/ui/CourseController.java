package studit.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import studit.core.chatbot.Chatbot;
import javafx.scene.control.Button;

public class CourseController {
    
    public static Chatbot chatbot = null;
    private String comment;

    @FXML
    private Label label;

    @FXML
    private Button mainpage_btn;

    @FXML
    private Button chatbot_btn;

    @FXML
    private Button ntnu_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private TextArea commentField;

    @FXML
    void logoutAction(ActionEvent event) {

    }

    /*
    * Goes back to main page
    */
    @FXML
    void mainpageAction(ActionEvent event) {


    }

    @FXML
    void ntnuAction(ActionEvent event) {

    }

    @FXML
    void openChatBot(ActionEvent event) {
         if (chatbot == null) {
    		chatbot = new Chatbot();
    	} else {
    		chatbot.show();
    	}
    }

    public void setLabelText(String text){
       label.setText(text);
   }

   public void writeComment(String text){
        comment =  commentField.getText();
        //code to post the comment to the comment-section
        //should maybe be in recent written order
   }
}
