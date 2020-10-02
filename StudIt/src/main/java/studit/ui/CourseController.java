package studit.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import studit.core.chatbot.Chatbot;
import javafx.scene.control.Button;

public class CourseController {
    
    public static Chatbot chatbot = null;
    private String comment;

    @FXML
    private BorderPane rootPane;

    @FXML
    private Label label;

    @FXML
    private Button mainPage_btn;

    @FXML
    private Button chatbot_btn;

    @FXML
    private Button ntnu_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private TextArea commentField;

    /*
    * logs user out, and goes to login scene
     */
    @FXML
    void logoutAction() {

    }

    /*
    * Goes back to main page
    */
    @FXML
    void mainPageAction() {
          try {
               this.rootPane = new FXMLLoader.load(getClass().getResource("App.fxml"));
               appController.initialize();

               // BorderPane newPane = FXMLLoader.load(getClass().getResource("App.fxml"));
               // rootPane.getChildren().setAll(newPane);

          } catch (IOException e) {
               System.out.println(e);
               }
          }

    /*
    * Gives the user a choice to open NTNU homepage in web-browser
    */
    @FXML
    void ntnuAction(ActionEvent event) {
          System.out.println("NTNU");
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

   public void writeComment(){
        comment =  commentField.getText();
        System.out.println(comment.toString());
        //code to post the comment to the comment-section
        //should maybe be in recent written order
   }
}
