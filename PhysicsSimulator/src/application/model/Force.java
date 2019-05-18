package application.model;

/** The class used to store forces with a angle and magnitude
 * 
 * @author samarthshah
 *
 */
public class Force {

	private double angle;
	private double magnitude;
	
	/** Creates a new force with the values
	 * 
	 * @param theta The angle of the force in degreses
	 * @param mag The magnitude of the force
	 */
	public Force(double theta, double mag) {
		angle = theta;
		magnitude = mag;
	}
	
	/**
	 * 
	 * @return A double with the angle of the force in degrees
	 */
	// 0 is to the right, 90 is down, 180 is left and 270 is up
	public double getAngle() {
		return angle;
	}
	
	/**
	 * 
	 * @return A double with the magnitude of the force
	 */
	public double getMagnitude() {
		return magnitude;
	}
	
	/** Sets the angle of the force to the entered value
	 * 
	 * @param theta The value to set the angle to
	 */
	public void setAngle(double theta) {
		angle = theta;
	}
	
	/** Sets the magnitude of the force to the entered value
	 * 
	 * @param mag The value to set the magnitude to
	 */
	public void setMagnitude(double mag) {
		magnitude = mag;
	}
}
