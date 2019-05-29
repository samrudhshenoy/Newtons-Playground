package application.model;

/** The class of the ball that moves around in the world based on the forces acting on it
 * 
 * @author samarthshah
 *
 */
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

		vx = velocityX;
		vy = velocityY;
	}

	/** Changes the velocity and the position based on the forces acting on the ball
	 * 
	 * @param speed The current speed of the simulation
	 * @param actingForces The forces acting on the ball at that moment
	 */
	public void act(double speed, ArrayList<Force> actingForces) {

		double resultantX = 0.0, resultantY = 0.0;

		for (Force f: actingForces) {

			double angle = f.getAngle();
			double mag = f.getMagnitude();

			resultantX += mag * Math.cos(Math.toRadians(angle));
			resultantY += mag * Math.sin(Math.toRadians(angle));
		}

		resultantX = ((int)resultantX*100)/100.0;
		resultantY = ((int)resultantY*100)/100.0;

		vx += resultantX/mass*Math.sqrt(speed);
		vy += resultantY/mass*Math.sqrt(speed);

		x += vx/60*Math.sqrt(speed);
		y -= vy/60*Math.sqrt(speed);		
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

		double A = x - x1;
		double B = y - y1;
		double C = x2 - x1;
		double D = y2 - y1;

		double dot = A * C + B * D;
		double len_sq = C * C + D * D;
		double param = -1;
		if (len_sq != 0) //in case of 0 length line
			param = dot / len_sq;

		double xx, yy;

		if (param < 0) {
			xx = x1;
			yy = y1;
		}
		else if (param > 1) {
			xx = x2;
			yy = y2;
		}
		else {
			xx = x1 + param * C;
			yy = y1 + param * D;
		}

		double dx = x - xx;
		double dy = y - yy;
		return r/2 >= Math.sqrt(dx * dx + dy * dy);
	}	

	/**
	 * Changes the y component of velocity for a bounce in the vertical direction
	 */
	public void bounceY() {
		
		if (vy > 800) {
			vy = 799.9;
		} else if (vy < -800) {
			vy = -799.9;
		}

		vy *= -1;
	}

	/**
	 * Changes the x component of velocity for a bounce in the horizontal direction
	 */
	public void bounceX() {
		
		if (vx > 800) {
			vx = 799.9;
		} else if (vx < -800) {
			vx = -799.9;
		}
		
		vx *= -1;
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
		double a = Math.toDegrees(Math.atan(vy/vx));
		if (vx < 0) {
			if (vy > 0 || a > 0)
				return 180 + a;
		}
		
		
		return a;
	}

	/**
	 * @return A string representation of the ball
	 */
	public String toString() {
		return "Ball: " + x + ", " + y + ": " + r;
	}

}
