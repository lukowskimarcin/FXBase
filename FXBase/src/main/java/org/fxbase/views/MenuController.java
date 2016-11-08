package org.fxbase.views;

import javax.inject.Inject;

import org.services.TestService;

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
	
	@Inject
	private TestService service;

	 
	
	@FXML
	void onClose(ActionEvent event) {	
		service.print();
		Platform.exit();
	}

}
