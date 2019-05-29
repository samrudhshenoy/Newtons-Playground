package application.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import application.Main;
import application.model.Obstacle;
import application.model.World;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


/** The class that controls the main screen with the simulation and the 2 sidebars
 * 
 * @author samarthshah
 *
 */
public class MainScreenController implements Serializable {

	private static final long serialVersionUID = 100L;


	private Main m;
	private BorderPane pane;
	private World world;
	private WorldEditorPanelController wepc;

	/** Creates a new main screen
	 * 
	 * @param m The main of the program
	 */
	public MainScreenController(Main m) {

		this.m = m;
		pane = new BorderPane();

		world = new World(this);
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

			wepc = loader.getController();
			wepc.setMainAndFields(this);

			pane.setRight(vbox);


		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	/** Refreshes the world bar with the ball values as they change
	 * 
	 */
	public void refreshWorldBar() {
		wepc.refresh();
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

	/** Sets the current world to the one entered as a parameter
	 * 
	 * @param w The new world to set the simulation to
	 */
	public void setWorld(World w) {
		world.setBall(w.getBall());

		ArrayList<Obstacle> newObstacles = w.getObstacles();

		for (Obstacle o: newObstacles) {
			o.generateLine();
		}

		world.setObstacles(newObstacles);
		world.setGravity(w.getGravity());
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
