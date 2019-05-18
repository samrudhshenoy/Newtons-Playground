package application.model;

import java.util.ArrayList;


import javafx.scene.canvas.Canvas;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/** The class used to control the simulation part of the program like the ball and the obstacles and make sure they interact
 * 
 * @author samarthshah
 *
 */
public class World {

	private Canvas canvas;
	private GraphicsContext gc;
	
	private AnimationTimer timer;
	
	private Ball ball;
	private ArrayList<Obstacle> obstacles;
	private double gravityMag;
	
	/** Creates a new world with a new ball, canvas, and obstacles for the edges of the window, and a new animation timer
	 * 
	 */
	public World() {
		ball = new Ball(100, 100, 20, 100, 0.0, 0.0);
		gravityMag = 9.81;
		
		canvas = new Canvas(720, 720);
		
		obstacles = new ArrayList<Obstacle>();
		obstacles.add(new Obstacle(0, 0, 0, canvas.getWidth()));
		obstacles.add(new Obstacle(0, 0, 90, canvas.getHeight()));
		obstacles.add(new Obstacle(canvas.getWidth(), 720, 270, canvas.getHeight()));
		obstacles.add(new Obstacle(0, canvas.getHeight(), 0, canvas.getWidth()));

		gc = canvas.getGraphicsContext2D();
		
		ball.draw(gc);
		
		timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.setFill(Color.BEIGE);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.setFill(Color.BLACK);
                
                ball.draw(gc);
                
                ArrayList<Force> forces = new ArrayList<Force>();
                forces.add(new Force(270, gravityMag * ball.getMass()));
                
                for (Obstacle o: obstacles) {
                	o.drawLine(gc);
                

                	if (ball.collides(o)) {
                	}
                }
                
                ball.act(forces);
                                
            }
        };
	}
	
	/** Adds a new obstacle with the parameters for values
	 * 
	 * @param x The first x value of the line
	 * @param y The first y value of the line
	 * @param angle The angle of the obstacle
	 * @param length The length of the obstacle
	 */
	public void addNewObstacle(double x ,double y, double angle, double length) {
		obstacles.add(new Obstacle(x, y, angle, length));
	}
	
	/** Resets the obstacles in the simulation to just the ones on the edges
	 * 
	 */
	public void resetObstacles() {
		obstacles.clear();
		obstacles.add(new Obstacle(0, 0, 0, canvas.getWidth()));
		obstacles.add(new Obstacle(0, 0, 90, canvas.getHeight()));
		obstacles.add(new Obstacle(canvas.getWidth(), 720, 270, canvas.getHeight()));
		obstacles.add(new Obstacle(0, canvas.getHeight(), 0, canvas.getWidth()));
	}
	
	/** Starts the simulation
	 * 
	 */
	public void start() {
        timer.start();
	}
	
	/** Pauses the simulation
	 * 
	 */
	public void stop() {
		timer.stop();
	}
		
	/**
	 * 
	 * @return The canvas where all of the simulation is being drawn on
	 */
	public Canvas getCanvas() {
		return canvas;
	}
	
	/**
	 *  
	 * @return A double with the current gravity magnitude of the simulation
	 */
	public double getGravity() {
		return gravityMag;
	}
	
	/**
	 * 
	 * @param g The value to set the gravity magnitude of the simulation to 
	 */
	public void setGravity(double g) {
		gravityMag = g;
	}
	
	/**
	 * 
	 * @return The ball that is currently being used in the simulation
	 */
	public Ball getBall() {
		return ball;
	}
}
