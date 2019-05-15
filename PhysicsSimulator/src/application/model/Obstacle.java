package application.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Line;

public class Obstacle {

	private double x, y;
	private Line line;
	private double length;
	private double angle;
	private double frictionMag;
	
	public Obstacle(double x, double y, double theta, double length) {
		 this.x = x;
		 this.y = y;
		 this.length = length;
		 angle = theta;
		 
		 line = new Line(x, y, x + length * Math.cos(Math.toRadians(angle)), y + length * Math.sin(Math.toRadians(angle)));
	}
	
	public void drawLine(GraphicsContext gc) {
		gc.strokeLine(x, y, line.getEndX(), line.getEndY());
	}
	
	public Line getLine() {
		return line;
	}
}
