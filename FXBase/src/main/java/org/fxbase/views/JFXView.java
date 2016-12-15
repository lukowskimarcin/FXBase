package org.fxbase.views;

import javafx.scene.Node;

public class JFXView<T extends BaseControler> {
	
	private Node node;
	
	private T controler;
	
	public JFXView(Node node, T controler){
		this.node = node;
		this.controler = controler;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public T getControler() {
		return controler;
	}

	public void setControler(T controler) {
		this.controler = controler;
	}
	
	
	

}
