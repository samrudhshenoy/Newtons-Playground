package application.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import application.model.World;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoadScreenController {

	@FXML
	private TextField saveNameField;
	private World w;
	private MainScreenController msc;

	public void setWorld(MainScreenController msc) {
		this.msc = msc;
		w = msc.getWorld();
	}

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
		         System.out.println(w.toString());
				
				
			} catch (IOException | ClassNotFoundException e ) {
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
