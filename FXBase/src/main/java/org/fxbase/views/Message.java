package org.fxbase.views;

/**
 * Dane wysyłane między kontrolerami
 * @author Marcin
 *
 */
public class Message {
	//Dane
	private Object data;
	
	//Nadawca wiadomości
	private BaseControler sender;
	
	//Typ wiadomości
	private String type;
	
	public Message(Object data) {
		this.data = data;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setSender(BaseControler sender) {
		this.sender = sender;
	}
	
	public BaseControler getSender() {
		return sender;
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
