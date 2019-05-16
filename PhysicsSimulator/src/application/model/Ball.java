package application.model;

import java.util.ArrayList;

import samrudh.shapes.Line;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;

public class Ball {
	
	private double x, y, r;
	private double mass;
	private double vx, vy;
	private Circle shape;
//	private ArrayList<Line> bounds;
	
	public Ball(double xVal, double yVal, double radius, double mass) {
		x = xVal;
		y = yVal;
		r = radius;
		this.mass = mass;
		
		shape = new Circle(x, y, r); 
		
		vx = 0.0;
		vy = 0.0;
		
//		bounds = new ArrayList<Line>();
//		
//		bounds.add(new Line(x, y, x+radius*2, y));
//		bounds.add(new Line(x, y+radius*2, x+radius*2, y+radius*2));
//		bounds.add(new Line(x, y, x, y+radius*2));
//		bounds.add(new Line(x+radius*2, y, x+radius*2, y+radius*2));
	}
	
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
		
		System.out.println(vx);
		System.out.println(vy);
				
		x += vx/60;
		y -= vy/60;		
	}
	
	public void draw(GraphicsContext gc) {
		gc.fillOval(x, y, r, r);
	}
	
	public boolean collides(Obstacle o) {
		return shape.getBoundsInLocal().intersects(o.getLine().getBoundsInLocal());	
	}
	
	public double getMass() {
		return mass;
	}
	
	public double getNormalForce() {
		return 9.81 * mass;
	}
	
	public double getVelocity() {
		return Math.sqrt(vx*vx + vy*vy);
	}
	
	public double getAngle() {
		return Math.toDegrees(Math.atan(vy/vx));
	}
}
