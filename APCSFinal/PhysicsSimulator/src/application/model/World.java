package application.model;

import java.util.ArrayList;


import javafx.scene.canvas.Canvas;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class World {

	private Canvas canvas;
	private GraphicsContext gc;
	
	private AnimationTimer timer;
	
	private Ball ball;
	private ArrayList<Obstacle> obstacles;
	private double gravityMag;
	
	
	public World() {
		ball = new Ball(100, 100, 20, 100, 0.0, 100.0);
		gravityMag = -9.81;
		
		canvas = new Canvas(1280, 720);
		
		obstacles = new ArrayList<Obstacle>();
		obstacles.add(new Obstacle(0, 0, 0, canvas.getWidth()));
		obstacles.add(new Obstacle(0, 0, 90, canvas.getHeight()));
		obstacles.add(new Obstacle(canvas.getWidth(), 0, 90, canvas.getHeight()));
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
                
                ArrayList<Force> forces = new ArrayList<Force>();
                forces.add(new Force(90, gravityMag * 1000));
                
                for (Obstacle o: obstacles) {
                	o.drawLine(gc);
                	if (ball.collides(o)) {
                		ball.bounceY();
//                		forces.add(new Force(o.getNormalAngle()+(o.getNormalAngle()-ball.getAngle()), ball.getVelocity()));
                	}
                }
                
                ball.act(forces);
                ball.draw(gc);
                                
            }
        };
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
}
