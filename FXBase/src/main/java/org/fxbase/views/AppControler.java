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
import org.services.TestService;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AppControler {
	
	private static final Logger log = Logger.getLogger(AppControler.class.getName());   
	
	@Inject 
	private FXMLLoader fxmlLoader;
	
	private Map<String, JFXView> controlers = new HashMap<String, JFXView>();
	
	private BorderPane root;
	
	private Stage stage;
	
	@Inject
	private TestService service;
	
	
	public void launchJavaFXApplication(@Observes @StartupScene Stage primaryStage) {
		try {
			stage = primaryStage;
			InputStream is = getClass().getResourceAsStream("Main.fxml");
			root = (BorderPane) fxmlLoader.load(is);

			Scene scene = new Scene(root, 640, 480);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			JFXView menu = load(MenuController.class, false);
			setTopNode(menu);
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception ex) {
			log.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
	
	
	public JFXView load(Class<? extends BaseControler> clazz, boolean reload)  {
		JFXView form = null;
		try {
			BaseControler base = clazz.newInstance();
			String fxmlPath = base.getFxml();
			
			if(!reload) {
				form = controlers.get(base.getName());
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
				controlers.put(controler.getName(), form);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return form;
	}
	
	public void setCenterNode(JFXView form) {
		root.setCenter(form.getNode());
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
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}


	public BorderPane getRoot() {
		return root;
	}

	public void setRoot(BorderPane root) {
		this.root = root;
	}
	
	
}
