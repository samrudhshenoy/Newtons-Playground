package application.model;

import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;
import samarthshah.shapes.Line;

/** The class of the planes that are obstacles in the simulation represented with lines
 * 
 * @author samarthshah
 *
 */
public class Obstacle implements Serializable {

	private static final long serialVersionUID = 1L;

	private double x, y;
	private transient Line line;
	private double length;
	private double angle;

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
		angle = -theta;

		double x1 = x;
		double x2 = x + length * Math.cos(Math.toRadians(angle));
		double y1 = y;
		double y2 = y + length * Math.sin(Math.toRadians(angle));

		if (y1 < y2 && x1 == x2) {
			double temp = y1;
			y1 = y2;
			y2 = temp;
		}

		line = new Line(x1, y1, x2, y2);
	}

	/** Generates a new line in the obstacle after it has been serialized
	 * 
	 */
	public void generateLine() {
		double x1 = x;
		double x2 = x + length * Math.cos(Math.toRadians(angle));
		double y1 = y;
		double y2 = y + length * Math.sin(Math.toRadians(angle));

		if (y1 < y2 && x1 == x2) {
			double temp = y1;
			y1 = y2;
			y2 = temp;
		}

		line = new Line(x1, y1, x2, y2);
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
	 * @return The length of the line
	 */
	public double getLength() {
		return length;
	}

	/**
	 * 
	 * @return The x value of the first point
	 */
	public double getX() {
		return x;
	}

	/**
	 * 
	 * @return The y value of the first point
	 */
	public double getY() {
		return y;
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

	/**
	 * @return the angle direction of the obstacle
	 */
	public double getAngle() {
		return angle;
	}
}
