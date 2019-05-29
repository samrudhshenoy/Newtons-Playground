package application;

import java.io.IOException;

import application.controllers.LoadScreenController;
import application.controllers.MainScreenController;
import application.controllers.MenuScreenController;
import application.controllers.SaveScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/** The main class of the program tasked with managing and switching between windows and potentially showing new ones
 * 
 * @author samarthshah
 *
 */
public class Main extends Application {

	private Stage primaryStage;
	private MainScreenController msc;

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
//			pane.setStyle("-fx-background-color: #4c4c4c;");

			MenuScreenController controller = loader.getController();
			controller.setMain(this);

			Scene scene = new Scene(pane, 1280 , 720);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setMinHeight(720);
			primaryStage.setMinWidth(1280);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/** Shows a new window where the user can save the current world
	 * 
	 */
	public void showSaveScreen() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("fxml/SaveScreen.fxml"));
			VBox pane = (VBox) loader.load();
			SaveScreenController controller = loader.getController();
			controller.setWorld(msc.getWorld());

			Scene scene = new Scene(pane);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


			Stage dialogStage = new Stage();
			dialogStage.setResizable(false);
			dialogStage.setTitle("Data"); //Sets title to Edit Member
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setScene(scene);

			dialogStage.showAndWait();			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** Shows a new window where the user can load saved worlds
	 * 
	 */
	public void showLoadScreen() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("fxml/LoadScreen.fxml"));
			VBox pane = (VBox) loader.load();
			LoadScreenController controller = loader.getController();
			controller.setWorld(msc);

			Scene scene = new Scene(pane);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


			Stage dialogStage = new Stage();
			dialogStage.setResizable(false);
			dialogStage.setTitle("Data"); //Sets title to Edit Member
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setScene(scene);

			dialogStage.showAndWait();			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** Shows the main screen of the program with the simualation and the 2 sidebars
	 * 
	 */
	public void showMainScreen () {

		msc = new MainScreenController(this);
		Scene scene = new Scene(msc.getPane());

		primaryStage.setScene(scene);
	}

	/** Shows a new window with all of the data of the last play pause
	 * 
	 * @param data The data to show
	 */
	public void showDataWindow(double[] data) {
		Stage dialogStage = new Stage();
		dialogStage.setResizable(false);
		dialogStage.setTitle("Data"); //Sets title to Edit Member
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);

		TextArea a = new TextArea();
		a.setText("Displacement: " + data[0] + "\n" + "Avg Velocity: " + data[1] + "\n" + "Change X: " + data[2] + "\n" + "Change Y: " + data[3] + "\n" + "Time Elapsed: " + data[4]);
		a.setEditable(false);

		Scene scene = new Scene(a, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		dialogStage.setScene(scene);

		dialogStage.showAndWait();
	}

	/** Shows the menu screen of the program
	 * 
	 */
	public void showMenuScreen () {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("fxml/menuScreen.fxml"));
			BorderPane pane = (BorderPane) loader.load();
			MenuScreenController controller = loader.getController();
			controller.setMain(this);

			Scene scene = new Scene(pane);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			
			primaryStage.setScene(scene);

		} catch (Exception e) {
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

		String helpString = "Once program is launched:\n" + 
				"- Press \"Exit\" to exit the program\n" + 
				"- Press \"Help\" to view a detailed set of instructions\n" + 
				"- Press \"Start\" to enter the simulator\n" + 
				"* Note: Our program assumes a perfect physics world, so there is no decrease of the ball's total velocity because total mechanical energy is conserved\n" + 
				"	- Left side bar:\n" + 
				"		- Save/Load an experiment with a user-determined name\n" + 
				"		- Play/Pause an experiment at a desired time\n" + 
				"		- View specific data collected within the time that the game was played and paused\n" + 
				"		- Create a new obstacle with a given coordinate start point, an angle, and a length\n" + 
				"		- Remove all obstacles currently displayed on the screen\n" + 
				"	- Right side bar:\n" + 
				"		- Set the gravitational acceleration of the world\n" + 
				"		- Set the playback speed of the simulation (from 0x to 2x)\n" + 
				"		- Set the (X, Y) coordinates of the ball\n" + 
				"		- Set the radius of the ball\n" + 
				"		- Set the mass of the ball\n" + 
				"		- Set the current x-directional velocity of the ball\n" + 
				"		- Set the current y-directional velocity of the ball";
		

		TextArea text = new TextArea(helpString);
		text.setEditable(false);
		text.setMinSize(900, 600);
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
	 * @param args idk
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
