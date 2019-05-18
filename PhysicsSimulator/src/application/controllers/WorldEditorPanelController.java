package application.controllers;

import application.model.Ball;
import application.model.World;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class WorldEditorPanelController {
	
	private	MainScreenController m;


    @FXML
    private TextField gravityField;

    @FXML
    private TextField xField;

    @FXML
    private TextField yField;

    @FXML
    private TextField radiusField;

    @FXML
    private TextField massField;

    @FXML
    private TextField vxField;

    @FXML
    private TextField vyField;
    
    private World w;
    private Ball b;
    
    public void setMainAndFields(MainScreenController main) {
    	m = main;
    	w = m.getWorld();
    	b = w.getBall();
    	
    	gravityField.setText("" + w.getGravity());
    	
    	xField.setText("" + b.getX());
    	yField.setText("" + b.getY());
    	radiusField.setText("" + b.getRadius());
    	massField.setText("" + b.getMass());
    	vxField.setText("" + b.getVX());
    	vyField.setText("" + b.getVY());

    }

    @FXML
    void handleSet(ActionEvent event) {
    	if (isInputValid()) {
    		w.setGravity(Double.parseDouble(gravityField.getText()));
    		b.setX(Double.parseDouble(xField.getText()));
    		b.setY(Double.parseDouble(yField.getText()));
    		b.setRadius(Double.parseDouble(radiusField.getText()));
    		b.setMass(Double.parseDouble(massField.getText()));
    		b.setVX(Double.parseDouble(vxField.getText()));
    		b.setVY(Double.parseDouble(vyField.getText()));

    	}
    }
    
	public boolean isInputValid() {
		String errorMessage = "";

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
			errorMessage += "No valid grade!\n";
		} else{
			try{
				Double.parseDouble(yField.getText());
			} catch (NumberFormatException e){
				errorMessage += "No valid grade!\n";
			}
		}
		
		if(radiusField.getText() == null || radiusField.getText().length() == 0) {
			errorMessage += "No valid x!\n";
		} else{
			try{
				Double.parseDouble(radiusField.getText());
			} catch (NumberFormatException e){
				errorMessage += "No valid x!\n";
			}
		}
		
		if(massField.getText() == null || massField.getText().length() == 0) {
			errorMessage += "No valid grade!\n";
		} else{
			try{
				Double.parseDouble(massField.getText());
			} catch (NumberFormatException e){
				errorMessage += "No valid grade!\n";
			}
		}
		
		if(vxField.getText() == null || vxField.getText().length() == 0) {
			errorMessage += "No valid x!\n";
		} else{
			try{
				Double.parseDouble(vxField.getText());
			} catch (NumberFormatException e){
				errorMessage += "No valid x!\n";
			}
		}
		
		if(vyField.getText() == null || vyField.getText().length() == 0) {
			errorMessage += "No valid grade!\n";
		} else{
			try{
				Double.parseDouble(vyField.getText());
			} catch (NumberFormatException e){
				errorMessage += "No valid grade!\n";
			}
		}
		
		

		//if there is no error message, returns true
		if (errorMessage.length() == 0) {
			return true;
		} else { //if there is an error message, create alert with error messages printed
//			Alert alert = new Alert(AlertType.ERROR);
//			alert.initOwner(s);
//			alert.setTitle("Invalid Fields");
//			alert.setHeaderText("Please correct invalid fields");
//			alert.setContentText(errorMessage);
//			alert.showAndWait();

			return false;
		}
	}
}
