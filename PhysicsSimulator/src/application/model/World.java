package application.model;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class World {

	private Canvas canvas;
	GraphicsContext gc;
	
	private Ball ball;
	private ArrayList<Obstacle> obstacles;
	private double gravityMag;
	
	
	public World() {
		ball = new Ball(100, 100, 20, 100);
		
		canvas = new Canvas(1280, 720);
		
		obstacles = new ArrayList<Obstacle>();
		obstacles.add(new Obstacle(0, 0, 0, canvas.getWidth()));
		obstacles.add(new Obstacle(0, 0, 90, canvas.getHeight()));
		obstacles.add(new Obstacle(canvas.getWidth(), 0, 90, canvas.getHeight()));
		obstacles.add(new Obstacle(0, canvas.getHeight(), 0, canvas.getWidth()));

		
		gc = canvas.getGraphicsContext2D();
		
		ball.draw(gc);	
	}
	
	public void run() {
		AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.setFill(Color.BEIGE);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.setFill(Color.BLACK);
                
                ArrayList<Force> forces = new ArrayList<Force>();
                forces.add(new Force(90, -50));
                ball.act(forces);
                
                for (Obstacle o: obstacles) {
                	o.drawLine(gc);
                }
                
                ball.draw(gc);
                
            }
        };
        
        timer.start();
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
}
