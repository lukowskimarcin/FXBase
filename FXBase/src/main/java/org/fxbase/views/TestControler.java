package org.fxbase.views;

import javax.inject.Inject;

import org.services.TestService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TestControler extends BaseControler {

	@FXML
	private Button button;

	
	public TestControler() {
		super("TestControler", "src/main/java/org/fxbase/views/TestControler.fxml");
	}

	@Inject
	private TestService service;

	private String value = "TestControler";
	
	
	@FXML
	void onButton(ActionEvent event)   {
		
		System.out.println(value);
		
		JFXView form = appControler.load(TestControler2.class, true);
		appControler.setCenterNode(form);
	}

	
	 
}
