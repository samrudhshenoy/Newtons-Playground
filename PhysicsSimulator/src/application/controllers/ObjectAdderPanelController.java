package application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ObjectAdderPanelController {
	
	MainScreenController m;
	
	@FXML TextField xField;
	@FXML TextField yField;
	@FXML TextField angleField;
	@FXML TextField lengthField;

	
	public void setController(MainScreenController main) {
		m = main;
	}
	
	@FXML
    void handleAdd(ActionEvent event) {
		if (isInputValid()) {
			double x = Double.parseDouble(xField.getText());
			double y = Double.parseDouble(yField.getText());
			double angle = Double.parseDouble(angleField.getText());
			double length = Double.parseDouble(lengthField.getText());
			m.addNewObstacle(x, y, angle, length);

		}
	}
	
	@FXML
	void handlePause(ActionEvent event) {
		m.pause();
	}
	
	@FXML
	void handlePlay(ActionEvent event) {
		m.play();
	}
	
	@FXML
	void handleBack(ActionEvent event) {
		m.getMain().showMenuScreen();
	}
	
	@FXML
	void handleReset(ActionEvent event) {
		m.getWorld().resetObstacles();
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
		
		if(angleField.getText() == null || angleField.getText().length() == 0) {
			errorMessage += "No valid x!\n";
		} else{
			try{
				Double.parseDouble(angleField.getText());
			} catch (NumberFormatException e){
				errorMessage += "No valid x!\n";
			}
		}
		
		if(lengthField.getText() == null || lengthField.getText().length() == 0) {
			errorMessage += "No valid grade!\n";
		} else{
			try{
				Double.parseDouble(lengthField.getText());
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
