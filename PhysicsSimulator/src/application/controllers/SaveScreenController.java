package application.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import application.model.World;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/** The class the controllers the window that is used to save worlds
 * 
 * @author samarthshah
 *
 */
public class SaveScreenController {

	@FXML private TextField saveNameField;
	private World w;

	/** Sets the world to save
	 * 
	 * @param w the world to save
	 */
	public void setWorld(World w) {
		this.w = w;
	}

	/** Saves the world in a new file with the name entered in the saves folder
	 * 
	 * @param event
	 */
	@FXML
	void handleSave(ActionEvent event) {
		if (isInputValid()) {

			try {

				File f = new File("saves/" + saveNameField.getText() + ".ser");
				f.createNewFile();

				FileOutputStream fileOutputStream
				= new FileOutputStream(f.getPath());
				ObjectOutputStream objectOutputStream 
				= new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeObject(w);
				objectOutputStream.flush();
				objectOutputStream.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/** 
	 * 
	 * @return True if the inputs in the fields are valid for  creating a new save file
	 */
	public boolean isInputValid() {
		String errorMessage = "";

		if(saveNameField.getText() == null || saveNameField.getText().length() == 0) {
			errorMessage += "No valid name!\n";
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
