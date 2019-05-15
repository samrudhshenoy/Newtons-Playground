package application.model;

public class Force {

	private double angle;
	private double magnitude;
	
	public Force(double theta, double mag) {
		angle = theta;
		magnitude = mag;
	}
	
	public double getAngle() {
		return angle;
	}
	
	public double getMagnitude() {
		return magnitude;
	}
	
	public void setAngle(double theta) {
		angle = theta;
	}
	
	public void setMagnitude(double mag) {
		magnitude = mag;
	}
}
