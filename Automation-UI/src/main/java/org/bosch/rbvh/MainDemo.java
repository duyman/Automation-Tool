package org.bosch.rbvh;

import org.bosch.rbvh.controls.JFXDecorator;
import org.bosch.rbvh.gui.MainController;

import com.jfoenix.svg.SVGGlyphLoader;
//import demos.gui.main.MainController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainDemo extends Application {

	@FXMLViewFlowContext 
	private ViewFlowContext flowContext;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {

		new Thread(()->{
			try {
				SVGGlyphLoader.loadGlyphsFont(MainDemo.class.getResourceAsStream("/fonts/icomoon.svg"),"icomoon.svg");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}).start();
		
		flowContext = new ViewFlowContext();
		flowContext.register("Stage", stage);

		Flow flow = new Flow(MainController.class);
		DefaultFlowContainer container = new DefaultFlowContainer();
		flow.createHandler(flowContext).start(container);
		
		JFXDecorator decorator = new JFXDecorator(stage, container.getView());
		decorator.setCustomMaximize(false);
		
		Scene scene = new Scene(decorator, 1400, 650);
		//scene.getStylesheets().add(MainDemo.class.getResource("/css/jfoenix-fonts.css").toExternalForm());
		//scene.getStylesheets().add(MainDemo.class.getResource("/css/jfoenix-design.css").toExternalForm());
		scene.getStylesheets().add(MainDemo.class.getResource("/css/jfoenix-main-demo.css").toExternalForm());
		//		stage.initStyle(StageStyle.UNDECORATED);
		//		stage.setFullScreen(true);
		stage.setMinWidth(700);
		stage.setMinHeight(800);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

}