package org.fxbase.views;

import javafx.scene.Node;

public class JFXView {
	
	private Node node;
	
	private BaseControler controler;
	
	public JFXView(Node node, BaseControler controler){
		this.node = node;
		this.controler = controler;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public BaseControler getControler() {
		return controler;
	}

	public void setControler(BaseControler controler) {
		this.controler = controler;
	}
	
	
	

}
