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



public class AppController{ 
    
    ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        loadData();
        coursesList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        System.out.println("Init");

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
    void openChatBot(ActionEvent event) {

    }

    //Ved å skrive inn noe i søkefeltet skal du få opp en listview
    @FXML
    void searchView(ActionEvent event) {

    }

    
    //Må lage en funksjon som observerer at elementer blir dobbeltrykket i listview slik at => noe skjer.
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
                            e.printStackTrace();
                        }
			}
		});
	}
    
    //Denne funksjonen burde egentlig være en som henter noe fra en database der data om hvert fag blir lagret.
    private void loadData() {
    String a = "TDT4109";
    String b =  "TMA4145";
    String c = "TTM4175";
    String d = "IT1901";
    list.addAll(a,b,c,d);
    coursesList.setItems(list);
    }

}
