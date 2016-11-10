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
		super( "src/main/java/org/fxbase/views/TestControler2.fxml");
	}
	

	@Inject
	private TestService service;

	private String value = "TestControler222";
	
	@FXML
	void onButton(ActionEvent event)   {
		Message message = new Message("wiadomoc 22");
		sendMessage(TestControler.class, message);
		
		JFXView form = appControler.load(TestControler.class, true);
		appControler.setCenterNode(form);
	}

	@Override
	public void receiveMessage(Message message) {
		System.out.println("TestControler2 Odebrana :" + message.getData());
		
	}
	
	 
}
