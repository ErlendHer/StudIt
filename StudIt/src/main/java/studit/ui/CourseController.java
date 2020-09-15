package studit.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class CourseController{ 

    @FXML
    private Label label;

    public void setLabelText(String text){
       label.setText(text);
   }

}