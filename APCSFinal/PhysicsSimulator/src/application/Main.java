package application;

import java.awt.Toolkit;

import application.controllers.menuScreenController;
import application.model.World;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

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

			Scene scene = new Scene(pane, Toolkit.getDefaultToolkit().getScreenSize().getWidth() , Toolkit.getDefaultToolkit().getScreenSize().getHeight());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.setMinHeight(720);
			primaryStage.setMinWidth(1280);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void showMainScreen () {		
		//		  Canvas canvas = new Canvas(400, 200);
		//	        // Set the width of the Canvas
		//	        canvas.setWidth(400);
		//	        // Set the height of the Canvas
		//	        canvas.setHeight(200);
		//	         
		//	        // Get the graphics context of the canvas
		//	        GraphicsContext gc = canvas.getGraphicsContext2D();
		//	         
		//	        // Draw a Text
		//	        gc.strokeText("Hello Canvas", 150, 100);

		World world = new World();
		Canvas canvas = world.getCanvas();

		// Create the Pane
		Pane root = new Pane();

		// Add the Canvas to the Pane
		root.getChildren().add(canvas);
		// Create the Scene
		Scene scene = new Scene(root);

		primaryStage.setScene(scene);

		world.start();

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

		String helpString = "Hi";

		TextArea text = new TextArea(helpString);
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
