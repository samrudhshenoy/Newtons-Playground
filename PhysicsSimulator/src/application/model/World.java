package application.model;

import java.util.ArrayList;


import javafx.scene.canvas.Canvas;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class World {

	private Canvas canvas;
	private GraphicsContext gc;
	
	private AnimationTimer timer;
	
	private Ball ball;
	private ArrayList<Obstacle> obstacles;
	private double gravityMag;
	
	
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
                		ball.bounceY();
                	}
                }
                
                ball.act(forces);
                                
            }
        };
	}
	
	public void addNewObstacle(double x ,double y, double angle, double length) {
		obstacles.add(new Obstacle(x, y, angle, length));
	}
	
	public void resetObstacles() {
		obstacles.clear();
		obstacles.add(new Obstacle(0, 0, 0, canvas.getWidth()));
		obstacles.add(new Obstacle(0, 0, 90, canvas.getHeight()));
		obstacles.add(new Obstacle(canvas.getWidth(), 720, 270, canvas.getHeight()));
		obstacles.add(new Obstacle(0, canvas.getHeight(), 0, canvas.getWidth()));
	}
	
	public void start() {
        timer.start();
	}
	
	public void stop() {
		timer.stop();
	}
		
	public Canvas getCanvas() {
		return canvas;
	}
	
	public double getGravity() {
		return gravityMag;
	}
	
	public void setGravity(double g) {
		gravityMag = g;
	}
	
	public Ball getBall() {
		return ball;
	}
}
