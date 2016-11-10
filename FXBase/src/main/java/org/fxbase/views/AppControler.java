package org.fxbase.views;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.fxbase.cdi.CDIUtil;
import org.fxbase.cdi.StartupScene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public   class AppControler {
	
	private static final Logger log = Logger.getLogger(AppControler.class.getName());   
	
	@Inject 
	private FXMLLoader fxmlLoader;
	
	protected Map<String, JFXView> controlers = new HashMap<String, JFXView>();
	
	protected BorderPane root;
	
	protected Stage stage;
	
	public void launchJavaFXApplication(@Observes @StartupScene Stage primaryStage) {
		try {
			stage = primaryStage;
			InputStream is = getClass().getResourceAsStream("Main.fxml");
			root = (BorderPane) fxmlLoader.load(is);

			Scene scene = new Scene(root, 640, 480);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//init();
			
			setTopNode(load(MenuController.class));
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception ex) {
			log.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
	
	//public abstract void init();
	
	
	public JFXView load(Class<? extends BaseControler> clazz )  {
		return load(clazz, true);
	}
	
	public JFXView load(Class<? extends BaseControler> clazz, boolean reload)  {
		JFXView form = null;
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
	
	public void setCenterNode(JFXView center) {
		root.setCenter(center.getNode());
	}
	
	public void setTopNode(JFXView menu) {
		root.setTop(menu.getNode());
	}
	
	public void setBottomNode(JFXView bottom) {
		root.setBottom(bottom.getNode());
	}
	
	public void setLeftNode(JFXView left) {
		root.setLeft(left.getNode()); 
	}
	
	public void setRightNode(JFXView right) {
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
	
	
	public Map<String, JFXView> getControlers() {
		return controlers;
	}
	
}
