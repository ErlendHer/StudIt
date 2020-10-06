package studit.ui;
	
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
        // getting loader and a pane for the first scene. 
        // loader will then give a possibility to get related controller
		FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("App.fxml"));
        Parent mainPane = mainLoader.load();
        Scene mainScene = new Scene(mainPane);
        
        // getting loader and a pane for the second scene. 
        FXMLLoader courseLoader = new FXMLLoader(getClass().getResource("Course.fxml"));
        Parent coursePane = courseLoader.load();
        Scene courseScene = new Scene(coursePane);
        
        // injecting first scene into the controller of the second scene
        CourseController courseController = (CourseController) courseLoader.getController();
        courseController.setMainScene(mainScene);

        // injecting second scene into the controller of the first scene
        AppController appController = (AppController) mainLoader.getController();
        courseController.setLabel(appController.getLabel());
        appController.setSecondScene(courseScene);
        

		primaryStage.setScene(mainScene);
		primaryStage.setTitle("StudIt");
        primaryStage.show();
        System.out.println(appController.getLabel());
    }
}
