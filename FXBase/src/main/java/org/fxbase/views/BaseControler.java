package org.fxbase.views;


public class BaseControler {

	protected AppControler appControler;
	protected String name;
	protected String fxml;
	
	/**
	 * 
	 * @param name	nazwa formatki
	 * @param fxml	sciezka do pliku FXML formatki
	 */
	public BaseControler(String name, String fxml ) {
		this.name = name;
		this.fxml = fxml;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
}
