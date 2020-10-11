package studit.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Course extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("Course.fxml"));
    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource("mainPage.css").toExternalForm());

    primaryStage.setScene(scene);
    primaryStage.setTitle("StudIt");
    primaryStage.show();
  }

}
