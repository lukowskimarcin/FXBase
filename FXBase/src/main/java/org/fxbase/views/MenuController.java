package org.fxbase.views;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MenuController extends BaseControler {

	public MenuController() {
		super("MenuController", "src/main/java/org/fxbase/views/MenuControler.fxml");
	}

	@FXML
	private MenuItem mClose;

	 
	
	@FXML
	void onClose(ActionEvent event) {
		Platform.exit();
	}

}
