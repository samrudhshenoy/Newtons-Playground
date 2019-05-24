package application.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import application.model.World;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SaveScreenController {

	@FXML
	private TextField saveNameField;
	private World w;

	public void setWorld(World w) {
		this.w = w;
	}

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
	 * @return True if the inputs in the fields are valid for creating a new obstacle
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

			return false;
		}
	}
}
