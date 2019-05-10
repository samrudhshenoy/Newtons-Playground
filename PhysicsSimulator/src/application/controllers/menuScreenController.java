package application.controllers;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class menuScreenController {
	
	Main m;
	
	public void setMain(Main main) {
		m = main;
	}

	@FXML
	void handleHelp(ActionEvent event) {
		m.showHelpWindow();
	}

	@FXML
	void handleQuit(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void handleStart(ActionEvent event) {

	}


}
