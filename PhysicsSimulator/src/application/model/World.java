package application.model;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class World extends Application {

	private Canvas canvas;
	private Ball ball;
	private ArrayList<Obstacle> obstacles;
	private double gravityMag;
	
	
	public World() {
		ball = new Ball(100, 100, 5, 100);
		
	}


	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Drawing Canvas");
		Group root = new Group();
		Canvas canvas = new Canvas(300, 250);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		drawShapes(gc);
		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	
	private void drawShapes(GraphicsContext gc) {
		gc.setFill(Color.GREEN);
	}
}
