package application.model;

import javafx.scene.canvas.GraphicsContext;
import samarthshah.shapes.Line;

/** The class of the planes that are obstacles in the simulation represented with lines
 * 
 * @author samarthshah
 *
 */
public class Obstacle {

	private double x, y;
	private Line line;
	private double length;
	private double angle;
	private double frictionMag;
	
	/** Creates a new line with the values entered in the parameters
	 * 
	 * @param x The first x value of the line
	 * @param y The first y value of the line
	 * @param theta The angle of the obstacle
	 * @param length The length of the obstacle
	 */
	public Obstacle(double x, double y, double theta, double length) {
		 this.x = x;
		 this.y = y;
		 this.length = length;
		 angle = theta;
		 
		 line = new Line(x, y, x + length * Math.cos(Math.toRadians(angle)), y + length * Math.sin(Math.toRadians(angle)));
	}
	
	/**  Draws the obstacle on the canvas using the graphics context
	 * 
	 * @param gc The graphics context to draw the obstacle on
	 */
	public void drawLine(GraphicsContext gc) {
		gc.strokeLine(line.getX(), line.getY(), line.getX2(), line.gety2());
	}
	
	/**
	 * 
	 * @return The line object created with the values of the obstacle
	 */
	public Line getLine() {
		return line;
	}
	
	/**
	 * 
	 * @return The normal angle of the plane used for bounce calculations
	 */
	public double getNormalAngle() {
		return angle + 90;
	}
	
	/**
	 *  @return A string representation of the obstacle with the 2 points of the line
	 */
	public String toString() {
		return line.getX() + " " + line.getY() + " " + line.getX2() + " " + line.gety2();
	}
}
