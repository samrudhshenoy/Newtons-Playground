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

public class World {

	private Canvas canvas;
	private Ball ball;
	private ArrayList<Obstacle> obstacles;
	private double gravityMag;
	
	
	public World() {
		ball = new Ball(100, 100, 5, 100);
		
		canvas = new Canvas(1280, 720);
		GraphicsContext gc = canvas.getGraphicsContext2D();
//		drawShapes(gc);
		
		ball.draw(gc);
		
	}
	private void drawShapes(GraphicsContext gc) {
		gc.setFill(Color.GREEN);
		gc.fillRect(10, 10, 10, 10);
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
}
