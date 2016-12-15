package org.fxbase.views;

import java.util.Map;

public class BaseControler   {

	protected AppControler appControler;
	protected String fxml;
	
	/**
	 * 
	 * @param name	nazwa formatki
	 * @param fxml	sciezka do pliku FXML formatki
	 */
	public BaseControler(String fxml ) {
		this.fxml = fxml;
	}

	public String getFxml() {
		return fxml;
	}

	public void setFxml(String fxml) {
		this.fxml = fxml;
	}

	public AppControler getAppControler() {
		return appControler;
	}

	public void setAppControler(AppControler appControler) {
		this.appControler = appControler;
	}
	
	public void receiveMessage(Message message) {
		
	}
	
	public boolean sendMessage(Class<? extends BaseControler> receiver, Message message) {
		boolean result = false;
		Map<String, JFXView<BaseControler>> controlers = appControler.getControlers();
		
		if(	controlers.containsKey(receiver.getName()) && message != null) {
			message.setSender(this);
			BaseControler controler = controlers.get(receiver.getName()).getControler();
			controler.receiveMessage(message);
			result = true;
		}
		return result;
	}
	
	public void sendMessageAll(Message message) {
		Map<String, JFXView<BaseControler>> controlers = appControler.getControlers();
		
		controlers.forEach((key, view) -> {
			if(!key.equals(getClass().getName())) {
				view.getControler().receiveMessage(message);
			}
		});
		
	}
}
