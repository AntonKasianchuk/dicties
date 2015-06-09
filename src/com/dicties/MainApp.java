package com.dicties;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import org.apache.log4j.Logger;

/**
 * 
 * @author Anton_Kasianchuk
 *
 */
public class MainApp extends Application {
	private final static Logger logger = Logger.getLogger(MainApp.class);
	
	private Stage mainStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.mainStage = primaryStage;
		this.mainStage.setTitle("Dicties");

		initRootLayout();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void initRootLayout() {
		FXMLLoader rootLayoutLoader = createLoaderForResource("view/RootLayout.fxml");
		tryLoadRootLayout(rootLayoutLoader);
		if (rootLayout == null) {
			exitApplication();
		} else {
			initMainStageWithSceneAndShow();
		}
	}
	
	private void tryLoadRootLayout(FXMLLoader rootLayoutLoader) {
		try {
			rootLayout = (BorderPane) rootLayoutLoader.load();
		} catch (IOException e) {
			logger.error("Cannot load the root layout", e);
		}
	}
	
	private FXMLLoader createLoaderForResource(String resourceUrlStr) {
		URL resourceUrl = MainApp.class.getResource(resourceUrlStr);
		FXMLLoader loader = new FXMLLoader(resourceUrl);
		return loader;
	}
	
	private void exitApplication() {
		logger.info("Exiting the application");
		mainStage.close();
	}
	
	private void initMainStageWithSceneAndShow() {
		Scene scene = new Scene(rootLayout);
		mainStage.setScene(scene);
		mainStage.show();
	}
}
