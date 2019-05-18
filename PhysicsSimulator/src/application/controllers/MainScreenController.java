package application.controllers;

import java.io.IOException;

import application.Main;
import application.model.World;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


/** The class that controls the main screen with the simulation and the 2 sidebars
 * 
 * @author samarthshah
 *
 */
public class MainScreenController {
	
	private Main m;
	private BorderPane pane;
	private World world;
	
	/** Creates a new main screen
	 * 
	 * @param m The main of the program
	 */
	public MainScreenController(Main m) {
		
		this.m = m;
		pane = new BorderPane();
		
		world = new World();
		Canvas canvas = world.getCanvas();

		// Create the Pane
		Pane root = new Pane();

		// Add the Canvas to the Pane
		root.getChildren().add(canvas);
		
		pane.setCenter(root);
		
		try {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("fxml/ObjectAdderPanel.fxml"));
		VBox vbox = (VBox) loader.load();
		
		ObjectAdderPanelController controller = loader.getController();
		controller.setController(this);
		
		pane.setLeft(vbox);

		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("fxml/WorldEditorPanel.fxml"));
		VBox vbox = (VBox) loader.load();
		
		WorldEditorPanelController controller = loader.getController();
		controller.setMainAndFields(this);
		
		pane.setRight(vbox);

		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		world.start();
	}
	
	/** Pauses the simulation
	 * 
	 */
	public void pause() {
		world.stop();
	}
	
	/** Starts the simulation
	 * 
	 */
	public void play() {
		world.start();	
	}
	
	/** Creates a new obstacle in the world
	 * 
	 * @param x The x value of the first point
	 * @param y The y value of the first point
	 * @param angle The angle of the obstacle
	 * @param length The length of the obstacle
	 */
	public void addNewObstacle(double x ,double y, double angle, double length) {
		world.addNewObstacle(x, y, angle, length);
	}

	/**
	 * 
	 * @return The pane that has the main screen on it to be displayed on the window
	 */
	public BorderPane getPane() {
		return pane;
	}
	
	/**
	 * 
	 * @return The simulation to be used by the 2 sidebars
	 */
	public World getWorld() {
		return world;
	}
	
	/**
	 * 
	 * @return The main to be used for going back to the main menu
	 */
	public Main getMain() {
		return m;
	}
}
