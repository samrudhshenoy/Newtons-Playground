package application.controllers;

import application.model.Ball;
import application.model.World;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/** The class that controls the sidebar that can be used for setting fields of the world and the vall
 * 
 * @author samarthshah
 *
 */
public class WorldEditorPanelController {
	
	private	MainScreenController m;
    private World w;
    private Ball b;
    
    @FXML private TextField gravityField;
    @FXML private TextField speedField;
    @FXML private TextField xField;
    @FXML private TextField yField;
    @FXML private TextField radiusField;
    @FXML private TextField massField;
    @FXML private TextField vxField;
    @FXML private TextField vyField;

    
    /** Sets the main screen controller and puts in the current values into the text fields
     * 
     * @param main the main screen controller
     */
    public void setMainAndFields(MainScreenController main) {
    	m = main;
    	w = m.getWorld();
    	b = w.getBall();
    	
    	gravityField.setText("" + w.getGravity());
    	speedField.setText("" + w.getSpeed());
    	xField.setText("" + b.getX());
    	yField.setText("" + b.getY());
    	radiusField.setText("" + b.getRadius());
    	massField.setText("" + b.getMass());
    	vxField.setText("" + b.getVX());
    	vyField.setText("" + b.getVY());
    }
    
    /** Refreshed the textfields to the current values
     * 
     */
    public void refresh() {
    	
    	w = m.getWorld();
    	b = w.getBall();
    	
    	gravityField.setText("" + w.getGravity());
    	speedField.setText("" + w.getSpeed());
    	xField.setText("" + b.getX());
    	yField.setText("" + b.getY());
    	radiusField.setText("" + b.getRadius());
    	massField.setText("" + b.getMass());
    	vxField.setText("" + b.getVX());
    	vyField.setText("" + b.getVY());
    }

    /** Sets the fields of the world and ball when the button is pressed
     * 
     * @param event
     */
    @FXML
    void handleSet(ActionEvent event) {
    	if (isInputValid()) {
    		w.setGravity(Double.parseDouble(gravityField.getText()));
    		w.setSpeed(Double.parseDouble(speedField.getText()));
    		b.setX(Double.parseDouble(xField.getText()));
    		b.setY(Double.parseDouble(yField.getText()));
    		b.setRadius(Double.parseDouble(radiusField.getText()));
    		b.setMass(Double.parseDouble(massField.getText()));
    		b.setVX(Double.parseDouble(vxField.getText()));
    		b.setVY(Double.parseDouble(vyField.getText()));

    	}
    }
    
    /** Checks if the inputs in the text fields for setting the values of the world and the ball are valid
     * 
     * @return True if the input is valid
     */
	public boolean isInputValid() {
		String errorMessage = "";
		
		if(gravityField.getText() == null || gravityField.getText().length() == 0) {
			errorMessage += "No valid graivty!\n";
		} else{
			try{
				Double.parseDouble(gravityField.getText());
			} catch (NumberFormatException e){
				errorMessage += "No valid gravity!\n";
			}
		}
		
		if(speedField.getText() == null || speedField.getText().length() == 0) {
			errorMessage += "No valid speed!\n";
		} else{
			try{
				double d = Double.parseDouble(speedField.getText());
				if (d < 0 || d > 2) {
					errorMessage += "No valid speed!\n";
				}
			} catch (NumberFormatException e){
				errorMessage += "No valid speed!\n";
			}
		}

		if(xField.getText() == null || xField.getText().length() == 0) {
			errorMessage += "No valid x!\n";
		} else{
			try{
				Double.parseDouble(xField.getText());
			} catch (NumberFormatException e){
				errorMessage += "No valid x!\n";
			}
		}
		
		if(yField.getText() == null || yField.getText().length() == 0) {
			errorMessage += "No valid y!\n";
		} else{
			try{
				Double.parseDouble(yField.getText());
			} catch (NumberFormatException e){
				errorMessage += "No valid y!\n";
			}
		}
		
		if(radiusField.getText() == null || radiusField.getText().length() == 0) {
			errorMessage += "No valid r!\n";
		} else{
			try{
				Double.parseDouble(radiusField.getText());
			} catch (NumberFormatException e){
				errorMessage += "No valid r!\n";
			}
		}
		
		if(massField.getText() == null || massField.getText().length() == 0) {
			errorMessage += "No valid mass!\n";
		} else{
			try{
				Double.parseDouble(massField.getText());
			} catch (NumberFormatException e){
				errorMessage += "No valid mass!\n";
			}
		}
		
		if(vxField.getText() == null || vxField.getText().length() == 0) {
			errorMessage += "No valid vx!\n";
		} else{
			try{
				Double.parseDouble(vxField.getText());
			} catch (NumberFormatException e){
				errorMessage += "No valid vx!\n";
			}
		}
		
		if(vyField.getText() == null || vyField.getText().length() == 0) {
			errorMessage += "No valid vy!\n";
		} else{
			try{
				Double.parseDouble(vyField.getText());
			} catch (NumberFormatException e){
				errorMessage += "No valid vy!\n";
			}
		}
		
		

		//if there is no error message, returns true
		if (errorMessage.length() == 0) {
			return true;
		} else { //if there is an error message, create alert with error messages printed
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(errorMessage);
			alert.showAndWait();

			return false;
		}
	}
}
