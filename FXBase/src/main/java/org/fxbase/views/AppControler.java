package org.fxbase.views;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.fxbase.cdi.CDIUtil;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AppControler  {
	
	protected static final Logger log = Logger.getLogger(AppControler.class.getName());   
	
	protected Map<String, JFXView<BaseControler>> controlers = new HashMap<String, JFXView<BaseControler>>();
	
	protected BorderPane root;
	
	protected Stage stage;
	
	public void launchJavaFXApplication(Stage primaryStage) {
		try {
			stage = primaryStage;
			root = new BorderPane();
			
			stage.setTitle(getTitle());
			
			Scene scene = new Scene(root, getWindowWidth(), getWindowHeight());
			
			init();
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception ex) {
			log.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
	
 	public  void init() {
 		
 	};
	
	public String getTitle() {
		return null;
	};
	
	public int getWindowWidth(){
		return 640;
	}
	
	public int getWindowHeight(){
		return 480;
	}
	
	
	public JFXView  load(Class<? extends BaseControler> clazz )  {
		return load(clazz, true);
	}
	
	public JFXView load(Class<? extends BaseControler> clazz, boolean reload)  {
		JFXView<BaseControler> form = null;
		try {
			BaseControler base = clazz.newInstance();
			String fxmlPath = base.getFxml();
			
			if(!reload) {
				form = controlers.get(clazz.getName());
			}
			
			if(form==null) {
				InputStream is = new FileInputStream(fxmlPath);
				
				FXMLLoader loader = new FXMLLoader();
				loader.setControllerFactory(new Callback<Class<?>, Object>() {
					@Override
					public Object call(Class<?> param) {
						return CDIUtil.instance().getWeldContainer().select(param).get();
					}
				});
				
				Node node = null;
				node = loader.load(is);
				
				BaseControler controler = loader.getController();
				controler.setAppControler(this);

				form = new JFXView(node, controler);
				controlers.put(clazz.getName(), form);
			}

		} catch (Exception ex) {
			log.log(Level.SEVERE, "load", ex);
		}
		return form;
	}
	
	public void setCenterNode(JFXView<?> center) {
		root.setCenter(center.getNode());
	}
	
	public void setTopNode(JFXView<?> menu) {
		root.setTop(menu.getNode());
	}
	
	public void setBottomNode(JFXView<?> bottom) {
		root.setBottom(bottom.getNode());
	}
	
	public void setLeftNode(JFXView<?> left) {
		root.setLeft(left.getNode()); 
	}
	
	public void setRightNode(JFXView<?> right) {
		root.setLeft(right.getNode()); 
	}
	
	public Stage getStage() {
		return stage;
	}

	public BorderPane getRoot() {
		return root;
	}

	public void setRoot(BorderPane root) {
		this.root = root;
	}
	
	
	public Map<String, JFXView<BaseControler>> getControlers() {
		return controlers;
	}
	
}
