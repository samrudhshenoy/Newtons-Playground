package application.model;

import java.util.ArrayList;

//import samrudh.shapes.Line;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;
import samarthshah.shapes.Circle;
import samarthshah.shapes.Line;

public class Ball {

	private double x, y, r;
	private double mass;
	private double vx, vy;
	private Circle circle;
	//	private ArrayList<Line> bounds;

	public Ball(double xVal, double yVal, double radius, double mass, double velocityX, double velocityY) {
		x = xVal;
		y = yVal;
		r = radius;
		this.mass = mass;

		circle = new Circle(x, y, r); 

		vx = velocityX;
		vy = velocityY;

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

//		System.out.println(vx);
//		System.out.println(vy);

		x += vx/60;
		y -= vy/60;		
	}

	public void draw(GraphicsContext gc) {
		gc.fillOval(x, y, r, r);
	}

	public boolean collides(Obstacle o) {
		Line l = o.getLine();
		int a = Math.abs((int)(l.gety2()-l.getY()));
		int b = (int)(l.getX2()-l.getX());
		int c= (int)((l.getX()*l.gety2()) - (l.getX2()*l.getY()));

		double dist = (Math.abs(a * x + b * y + c)) /  
				Math.sqrt(a * a + b * b); 
		
//		System.out.println(x + ", " + y);
//		
//		System.out.println(dist);
//		System.out.println(r);
//		
//		System.out.println(r/2 >= dist);
//		System.out.println();
				
		return r >= dist;	
	}
	
	public void showDistProcess(Obstacle o) {
		Line l = o.getLine();
		int a = Math.abs((int)(l.gety2()-l.getY()));
		
		System.out.println("a = " + a);
		int b = (int)(l.getX2()-l.getX());
		System.out.println("b = " + b);

		int c= (int)((l.getX()*l.gety2()) - (l.getX2()*l.getY()));

		System.out.println("c = " + c);


		double dist = (Math.abs(a * x + b * y + c)) /  
				Math.sqrt(a * a + b * b); 
		
		
		System.out.println(x + ", " + y);
		
		System.out.println(dist);
		System.out.println(r);
		
		System.out.println(r/2 >= dist);
		System.out.println();
		

	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getRadius() {
		return r;
	}
	
	public double getMass() {
		return mass;
	}
	
	public double getVX() {
		return vx;
	}
	
	public double getVY() {
		return vy;
	}
	
	public void setX(double val) {
		x = val;
	}
	public void setY(double val) {
		y = val;
	}
	public void setRadius(double val) {
		r = val;
	}
	public void setMass(double val) {
		mass = val;
	}
	public void setVX(double val) {
		vx = val;
	}
	public void setVY(double val) {
		vy = val;
	}
	
	
	

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
	
	public void bounceX() {
		vx = 0;
		x = 100;
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
