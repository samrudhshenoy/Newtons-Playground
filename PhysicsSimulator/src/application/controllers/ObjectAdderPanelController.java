package application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/** The class that controls the sidebar that can be used for actions or adding new obstacles
 * 
 * @author samarthshah
 *
 */
public class ObjectAdderPanelController {

	private MainScreenController m;

	@FXML TextField xField;
	@FXML TextField yField;
	@FXML TextField angleField;
	@FXML TextField lengthField;

	/**
	 * 
	 * @param main The main screen controller is controls the sidebar
	 */
	public void setController(MainScreenController main) {
		m = main;
	}

	/** Handles what to do if the add button is pressed
	 * 
	 * @param event
	 */
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

	/** Saves the current world to a file when that button is pressed
	 * 
	 * @param event
	 */
	@FXML
	void handleSave(ActionEvent event) {
		m.getMain().showSaveScreen();
	}

	/** Loads the world in the file thats name is entered in the window that opens up
	 * 
	 * @param event
	 */
	@FXML
	void handleLoad(ActionEvent event) {
		m.getMain().showLoadScreen();
	}

	/** Shows a new window with the data from the last play pause time
	 * 
	 */
	@FXML
	void handleShowData() {
		m.getMain().showDataWindow(m.getWorld().getData());
	}

	/** Pauses the simulation when the button is pressed
	 * 
	 * @param event
	 */
	@FXML
	void handlePause(ActionEvent event) {
		m.pause();
	}

	/** Plays the simulation when the button is pressed
	 * 
	 * @param event
	 */
	@FXML
	void handlePlay(ActionEvent event) {
		m.play();
	}

	/** Goes back to the main menu when the button is pressed
	 * 
	 * @param event
	 */
	@FXML
	void handleBack(ActionEvent event) {
		m.getMain().showMenuScreen();
	}

	/** Resets the obstacles when the button is pressed
	 * 
	 * @param event
	 */
	@FXML
	void handleReset(ActionEvent event) {
		m.getWorld().resetObstacles();
	}


	/** 
	 * 
	 * @return True if the inputs in the fields are valid for creating a new obstacle
	 */
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
			errorMessage += "No valid y!\n";
		} else{
			try{
				Double.parseDouble(yField.getText());
			} catch (NumberFormatException e){
				errorMessage += "No valid y!\n";
			}
		}

		if(angleField.getText() == null || angleField.getText().length() == 0) {
			errorMessage += "No valid angle!\n";
		} else{
			try{
				Double.parseDouble(angleField.getText());
			} catch (NumberFormatException e){
				errorMessage += "No valid angle!\n";
			}
		}

		if(lengthField.getText() == null || lengthField.getText().length() == 0) {
			errorMessage += "No valid length!\n";
		} else{
			try{
				Double.parseDouble(lengthField.getText());
			} catch (NumberFormatException e){
				errorMessage += "No valid length!\n";
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
