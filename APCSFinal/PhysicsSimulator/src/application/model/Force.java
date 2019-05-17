package application.model;

public class Force {

	private double angle;
	private double magnitude;
	
	public Force(double theta, double mag) {
		angle = theta;
		magnitude = mag;
	}
	
	
	// 0 is to the right, 90 is down, 180 is left and 270 is up
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
