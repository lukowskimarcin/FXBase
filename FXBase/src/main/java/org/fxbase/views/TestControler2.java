package org.fxbase.views;

import javax.inject.Inject;

import org.services.TestService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TestControler2 extends BaseControler {

	@FXML
	private Button button;

	
	public TestControler2() {
		super("TestControler2", "src/main/java/org/fxbase/views/TestControler2.fxml");
	}
	

	@Inject
	private TestService service;

	private String value = "TestControler222";
	
	@FXML
	void onButton(ActionEvent event)   {
		System.out.println(value);
		
		JFXView form = appControler.load(TestControler.class, true);
		appControler.setCenterNode(form);
	}

	
	 
}
