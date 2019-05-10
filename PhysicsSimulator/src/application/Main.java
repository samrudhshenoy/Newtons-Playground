package application;
	
import java.awt.Color;

import application.controllers.menuScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.*;


/** The main class of the program tasked with managing and switching between windows and potentially showing new ones
 * 
 * @author samarthshah
 *
 */
public class Main extends Application {
	
	private Stage primaryStage;
	
	/** Is what the program does while running, like initializing the scene with the menu on it and showing the window itself.
	 * 
	 */
	@Override
	public void start(Stage primaryStage) {
		try {			
			
			this.primaryStage = primaryStage;
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("fxml/menuScreen.fxml"));
			BorderPane pane = (BorderPane) loader.load();
			menuScreenController controller = loader.getController();
			controller.setMain(this);
						
			Scene scene = new Scene(pane, 1280, 720);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Opens a new window with instructions and help on it
	 * 
	 */
	public void showHelpWindow() {
		Stage dialogStage = new Stage();
		dialogStage.setResizable(false);
		dialogStage.setTitle("Help"); //Sets title to Edit Member
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);
		
		TextArea text = new TextArea("Hi");
		text.setEditable(false);
		text.setMinSize(400, 400);
		text.setWrapText(true);
		BorderPane pane = new BorderPane();
		pane.setTop(text);
		Scene scene = new Scene(pane);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		dialogStage.setScene(scene);
		dialogStage.showAndWait();		
	}
	
	/** The main method that runs the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
