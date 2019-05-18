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

public class MainScreenController {
	
	private Main m;
	private BorderPane pane;
	private World world;
	
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
	
	public void pause() {
		world.stop();
	}
	
	public void play() {
		world.start();	
	}
	
	public void addNewObstacle(double x ,double y, double angle, double length) {
		world.addNewObstacle(x, y, angle, length);
	}

	public BorderPane getPane() {
		return pane;
	}
	
	public World getWorld() {
		return world;
	}
	
	public Main getMain() {
		return m;
	}
}
