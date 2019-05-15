package application.model;

public class Force {

	private int angle;
	private int magnitude;
	
	public Force(int theta, int mag) {
		angle = theta;
		magnitude = mag;
	}
	
	public int getAngle() {
		return angle;
	}
	
	public int getMagnitude() {
		return magnitude;
	}
	
	public void setAngle(int theta) {
		angle = theta;
	}
	
	public void setMagnitude(int mag) {
		magnitude = mag;
	}
}
