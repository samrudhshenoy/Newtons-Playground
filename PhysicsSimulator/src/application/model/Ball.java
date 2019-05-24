package application.model;

import java.io.Serializable;
import java.util.ArrayList;

//import samrudh.shapes.Line;

import javafx.scene.canvas.GraphicsContext;
import samarthshah.shapes.Line;

public class Ball implements Serializable{
	
	private static final long serialVersionUID = 2L;


	private double x, y, r;
	private double mass;
	private double vx, vy;
//	private Circle circle;
	//	private ArrayList<Line> bounds;

	
	/** Creates a new ball with the values
	 *  
	 * @param xVal The initial x value
	 * @param yVal The initial y value
	 * @param radius The radius of the ball
	 * @param mass The mass of the ball
	 * @param velocityX The initial x velocity of the ball
	 * @param velocityY The initial y velocity of the ball
	 */
	public Ball(double xVal, double yVal, double radius, double mass, double velocityX, double velocityY) {
		x = xVal;
		y = yVal;
		r = radius;
		this.mass = mass;

//		circle = new Circle(x, y, r); 

		vx = velocityX;
		vy = velocityY;

		//		bounds = new ArrayList<Line>();
		//		
		//		bounds.add(new Line(x, y, x+radius*2, y));
		//		bounds.add(new Line(x, y+radius*2, x+radius*2, y+radius*2));
		//		bounds.add(new Line(x, y, x, y+radius*2));
		//		bounds.add(new Line(x+radius*2, y, x+radius*2, y+radius*2));
	}

	/** Changes the velocity and the position based on the forces acting on the ball
	 * 
	 * @param actingForces The forces acting on the ball at that moment
	 */
	public void act(ArrayList<Force> actingForces) {

		double resultantX = 0.0, resultantY = 0.0;

		for (Force f: actingForces) {

			double angle = f.getAngle();
			double mag = f.getMagnitude();

			resultantX += mag * Math.cos(Math.toRadians(angle));
			resultantY += mag * Math.sin(Math.toRadians(angle));
		}

		resultantX = ((int)resultantX*100)/100.0;
		resultantY = ((int)resultantY*100)/100.0;

			vx += resultantX/mass;
			vy += resultantY/mass;

//		System.out.println(vx);
//		System.out.println(vy);

		x += vx/60;
		y -= vy/60;		
	}

	/**  Draws the ball on the canvas using the graphics context
	 * 
	 * @param gc The graphics context to draw the ball on
	 */
	public void draw(GraphicsContext gc) {
		gc.fillOval(x, y, r, r);
	}
	
	/** Checks if the ball is colliding with the obstacle
	 * 
	 * @param o The obstacles to check for collision
	 * @return True of the ball is currently colliding with the obstacle
	 */
	public boolean collides(Obstacle o) {
		Line l = o.getLine();
		
		double x1 = l.getX();
		double x2 = l.getX2();
		double y1 = l.getY();
		double y2 = l.gety2();
		
		
		int a = Math.abs((int)(y2-y1));
		int b = (int)(x2-x1);
		int c = (int)((x1*y2) - (x2*y1));

		double dist = (Math.abs(a * x + b * y + c)) /  
				Math.sqrt(a * a + b * b); 
				
		return r/2 >= dist;	
	}
	
	

	/**
	 * Changes the y component of velocity for a bounce in the vertical direction
	 */
	public void bounceY() {
		if (vx < 0) {
			y += 1;
		} else {
			y -= 1;
		}
		
		vy *= -0.9;
//		vy = 0;
//		y = 100;
	}
	
	/**
	 * Changes the x component of velocity for a bounce in the horizontal direction
	 */
	public void bounceX() {
		vx *= -.9;
	}
	
	/**
	 *  
	 * @return The x value of the ball
	 */
	public double getX() {
		return x;
	}

	/**
	 * 
	 * @return The y value of the ball
	 */
	public double getY() {
		return y;
	}

	/**
	 * 
	 * @return The radius of the ball
	 */
	public double getRadius() {
		return r;
	}

	/**
	 * 
	 * @return The mass of the ball
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * 
	 * @return The velocity in the x direction
	 */
	public double getVX() {
		return vx;
	}

	/**
	 * 
	 * @return The velocity in the y direction
	 */
	public double getVY() {
		return vy;
	}

	/**
	 * 
	 * @param val The value to set the x value to
	 */
	public void setX(double val) {
		x = val;
	}

	/**
	 * 
	 * @param val The value to set the y value to
	 */
	public void setY(double val) {
		y = val;
	}

	/**
	 * 
	 * @param val The value to set the radius value to
	 */
	public void setRadius(double val) {
		r = val;
	}

	/**
	 * 
	 * @param val The value to set the mass value to
	 */
	public void setMass(double val) {
		mass = val;
	}

	/**
	 * 
	 * @param val The value to set the velocity in the x direction value to
	 */
	public void setVX(double val) {
		vx = val;
	}

	/**
	 * 
	 * @param val The value to set the velocity in the y direction value to
	 */
	public void setVY(double val) {
		vy = val;
	}

	/**
	 * 
	 * @return The normal force of the ball
	 */
	public double getNormalForce() {
		return 9.81 * mass;
	}

	/**
	 * 
	 * @return The total magnitude of the velocity of the ball
	 */
	public double getVelocity() {
		return Math.sqrt(vx*vx + vy*vy);
	}

	/**
	 * 
	 * @return The total angle of the velocity of the ball
	 */
	public double getAngle() {
		return Math.toDegrees(Math.atan(vy/vx));
	}
	
	public String toString() {
		return "Ball: " + x + ", " + y + ": " + r;
	}

}
