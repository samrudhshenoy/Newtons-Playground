package application.controllers;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


/** The class controlling the menu screen
 * 
 * @author samarthshah
 *
 */
public class MenuScreenController {

	Main m;

	/** Sets the main of the program to call methods
	 * 
	 * @param main the main of the program
	 */
	public void setMain(Main main) {
		m = main;
	}

	/** Handles the help button pressed by telling the main to show the help window
	 * 
	 * @param event
	 */
	@FXML
	void handleHelp(ActionEvent event) {
		m.showHelpWindow();
	}

	/** Handles the quit button pressed by closing the program
	 * 
	 * @param event
	 */
	@FXML
	void handleQuit(ActionEvent event) {
		System.exit(0);
	}

	/** Handles the start button pressed by telling the main to show the simulation window
	 * 
	 * @param event
	 */
	@FXML
	void handleStart(ActionEvent event) {
		m.showMainScreen();
	}


}
