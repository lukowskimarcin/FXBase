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
		super(  "src/main/java/org/fxbase/views/TestControler.fxml");
	}

	@Inject
	private TestService service;

	private String value = "TestControler";
	
	
	@FXML
	void onButton(ActionEvent event)   {
		
		Message message = new Message("wiadomoc 1");
		if(!sendMessage(TestControler2.class, message)) {
			System.out.println("nie dostarczona");
		};
		
		JFXView form = appControler.load(TestControler2.class, true);
		appControler.setCenterNode(form);
	}

	@Override
	public void receiveMessage(Message message) {
		System.out.println("TestControler Odebrana :" + message.getData());
		
	}
	 
}
