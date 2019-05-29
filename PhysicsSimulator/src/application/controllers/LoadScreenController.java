package application.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import application.model.World;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/** The class that controlls the loading of saved experiments
 * 
 * @author samarthshah
 *
 */
public class LoadScreenController {

	@FXML
	private TextField saveNameField;
	private World w;
	private MainScreenController msc;

	/**
	 * 
	 * @param msc The controller of the main screen used to set the world when loaded
	 */
	public void setWorld(MainScreenController msc) {
		this.msc = msc;
		w = msc.getWorld();
	}

	/** Loads the serialized the world from the loade file and sets the world in the screen to it
	 * 
	 * @param event
	 */
	@FXML
	void handleLoad(ActionEvent event) {
		if (isInputValid()) {

			try {

				FileInputStream fileIn = new FileInputStream("saves/" + saveNameField.getText() + ".ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				w = (World) in.readObject();
				in.close();
				fileIn.close();

				msc.setWorld(w);

			} catch (IOException | ClassNotFoundException e ) {
				e.printStackTrace();
			}

		}
	}

	/** 
	 * 
	 * @return True if the inputs in the fields are valid for loading the world
	 */
	public boolean isInputValid() {
		String errorMessage = "";

		if(saveNameField.getText() == null || saveNameField.getText().length() == 0) {
			errorMessage += "No valid name!\n";
		}
		
		try {

			FileInputStream fileIn = new FileInputStream("saves/" + saveNameField.getText() + ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			w = (World) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException | ClassNotFoundException e ) {
			errorMessage += "File does not exist\n";
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
