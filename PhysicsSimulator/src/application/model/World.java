package application.model;

import java.io.Serializable;
import java.util.ArrayList;

import application.controllers.MainScreenController;
import javafx.scene.canvas.Canvas;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


/** The class used to control the simulation part of the program like the ball and the obstacles and make sure they interact
 * 
 * @author samarthshah
 *
 */
public class World implements Serializable {

	private static final long serialVersionUID = 3L;

	private transient MainScreenController msc;
	private transient Canvas canvas;
	private transient GraphicsContext gc;

	private transient AnimationTimer timer;

	private Ball ball;
	private ArrayList<Obstacle> obstacles;
	private double speed;
	private double gravityMag;
	private double[] data;


	/** Creates a new world with a new ball, canvas, and obstacles for the edges of the window, and a new animation timer
	 * 
	 * @param main The Main Screen Controller used to update the other parts of the main screen
	 */
	public World(MainScreenController main) {

		msc = main;

		ball = new Ball(100, 100, 20, 100, 0.0, 0.0);
		gravityMag = 9.81;
		speed = 1.0;

		data = new double[] {0, 0, 0, 0, 0};


		canvas = new Canvas(720, 720);

		obstacles = new ArrayList<Obstacle>();
		obstacles.add(new Obstacle(0, 0, 0, canvas.getWidth()));
		obstacles.add(new Obstacle(0, 0, 270, canvas.getHeight()));
		obstacles.add(new Obstacle(canvas.getWidth(), canvas.getHeight(), 90, canvas.getHeight()));
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
												
						if (o.getNormalAngle() == 90)
							ball.bounceY();
						else if (o.getNormalAngle() == 0 || Math.abs(o.getNormalAngle()) == 180) {
							ball.bounceX();
						} else {
							
							double bAngle = ball.getAngle();
							double oAngle = 180 - o.getNormalAngle();
							

							if (bAngle < oAngle + 90 && bAngle > oAngle - 90) {
								double fAngle = (oAngle) + (oAngle - bAngle);

								if (o.getAngle() < 90 && o.getAngle() > 0)
									ball.setVX(-Math.sqrt(Math.abs((Math.pow(ball.getVelocity(), 2))*Math.cos(fAngle*Math.PI/180))));

								else if (-o.getAngle() > 90 && -o.getAngle() < 180)
									ball.setVX(-Math.sqrt(Math.abs((Math.pow(ball.getVelocity(), 2))*Math.cos(fAngle*Math.PI/180))));
									
								else
									ball.setVX(Math.sqrt(Math.abs((Math.pow(ball.getVelocity(), 2))*Math.cos(fAngle*Math.PI/180))));
								
								ball.setVY(-Math.sqrt(Math.abs((Math.pow(ball.getVelocity(), 2))*Math.sin(fAngle*Math.PI/180))));

							}
							
							else {
							
							double fAngle = (oAngle) + (oAngle - bAngle);

							if (o.getAngle() < 90 && o.getAngle() > 0)
								ball.setVX(Math.sqrt(Math.abs((Math.pow(ball.getVelocity(), 2))*Math.cos(fAngle*Math.PI/180))));

							else if (-o.getAngle() > 90 && -o.getAngle() < 180)
								ball.setVX(Math.sqrt(Math.abs((Math.pow(ball.getVelocity(), 2))*Math.cos(fAngle*Math.PI/180))));
								
							else
								ball.setVX(-Math.sqrt(Math.abs((Math.pow(ball.getVelocity(), 2))*Math.cos(fAngle*Math.PI/180))));
							
							ball.setVY(Math.sqrt(Math.abs((Math.pow(ball.getVelocity(), 2))*Math.sin(fAngle*Math.PI/180))));
							}
						}
					}
				}

				if (ball.getVX() > 800)
					ball.setVX(800);
				if (ball.getVY() > 800)
					ball.setVY(800);
				if (ball.getVX() < -800)
					ball.setVX(-800);
				if (ball.getVY() < -800)
					ball.setVY(-800);
				
				
				ball.act(speed, forces);

				msc.refreshWorldBar();
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

		for (Obstacle o: obstacles) {
			o.drawLine(gc);
		}
	}

	/** Resets the obstacles in the simulation to just the ones on the edges
	 * 
	 */
	public void resetObstacles() {
		obstacles = new ArrayList<Obstacle>();
		obstacles.add(new Obstacle(0, 0, 0, canvas.getWidth()));
		obstacles.add(new Obstacle(0, 0, 270, canvas.getHeight()));
		obstacles.add(new Obstacle(canvas.getWidth(), canvas.getHeight(), 90, canvas.getHeight()));
		obstacles.add(new Obstacle(0, canvas.getHeight(), 0, canvas.getWidth()));
		
		for (Obstacle o: obstacles) {
			o.drawLine(gc);
		}
	}

	/** Starts the simulation
	 * 
	 */
	public void start() {
		timer.start();
		this.startDataCollection();
	}

	/** Pauses the simulation
	 * 
	 */
	public void stop() {
		timer.stop();
		this.stopDataCollection();
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
	 * @return A double with the current speed of the simulation
	 */
	public double getSpeed() {
		return speed;
	}
	
	/**
	 * 
	 * @param s The values to the speed of the simulation to
	 */
	public void setSpeed(double s) {
		speed = s;
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

	static long start = 0;
	static double xI = 0;
	static double yI = 0;
	static double xF = 0;
	static double yF = 0;

	/**
	 * Begins data collection and starts time, initializes position
	 */
	public void startDataCollection() {
		start = System.currentTimeMillis();
		xI = ball.getX();
		yI = ball.getY();
	}

	/**
	 * Stops data collection and enters the values into the data field computed values in a double array
	 */
	public void stopDataCollection() {
		double timeElapsed = (System.currentTimeMillis() - start) * .001;
		xF = ball.getX();
		yF = ball.getY();

		double changeDisp = Math.sqrt(Math.pow(xI-xF, 2) + Math.pow(yI-yF, 2));
		double avgVelocity = changeDisp/timeElapsed;
		double changeX = xF - xI;
		double changeY = yF - yI;

		data = new double[] {changeDisp, avgVelocity, changeX, changeY, timeElapsed};
	}

	/**
	 * 
	 * @return The data values that are saved in the world from the last play pause timeframe
	 */
	public double[] getData() {
		return data;
	}

	/**
	 *  @return A string representation of the world
	 */
	public String toString() {
		String s = "";

		s += ball.toString();

		for (Obstacle o : obstacles) {
			s += "\n" + o.toString();
		}

		s += "\n" + this.getGravity();

		return s;
	}

	/**
	 * 
	 * @param b The new ball to set the current ball to
	 */
	public void setBall(Ball b) {
		ball = b;
	}

	/**
	 * 
	 * @return The list of obstacles in the world
	 */
	public ArrayList<Obstacle> getObstacles() {
		return obstacles;
	}

	/**
	 * 
	 * @param obstacles The list of obstacles to set the obstacles in the simulation to
	 */
	public void setObstacles(ArrayList<Obstacle> obstacles) {
		this.obstacles = obstacles;
	}
}
