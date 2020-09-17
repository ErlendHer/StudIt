package studit.core;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Chatbot {
	
	private Stage chatStage;
	private ChatbotManager chatbotManager;
	
	public Chatbot() {
		displayWindow();
		chatbotManager = new ChatbotManager();
	}
	
	/*
	 * Opens a new window for our chatbot
	 */
	private void displayWindow() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/studit/ui/Chatbot.fxml"));
			chatStage = new Stage();
			Scene scene = new Scene(root);
			
			// Setting the background to be transparent, so we can create rounded corners in our css file
			chatStage.initStyle(StageStyle.TRANSPARENT);
			scene.setFill(Color.TRANSPARENT);
			
			scene.getStylesheets().setAll(getClass().getResource("/studit/ui/chatbot.css").toExternalForm());
			chatStage.setScene(scene);
			chatStage.show();
		} catch (IOException e) {
			System.out.println("Error loading ChatbotController.FXML -> Is the file corrupt?");
			e.printStackTrace();
		}
	}

	public void show() {
		chatStage.setIconified(false);
	}
	
	/*
	 * Manages the user entered input and executes commands accordingly.
	 * @param input - the user input to process
	 * @return chatbot response
	 */
	public String manageInput(String input) {
		return chatbotManager.manageInput(input);
	}

}
