package org.mysoft.brainless.studio.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Studio extends Application {

	@Override
	public void start(Stage stage) {
		
		SimulationPreview preview = new SimulationPreview();

		Scene scene = new Scene(preview, 640, 400);

		stage.setTitle("Brainless studio 0.1");
		stage.setScene(scene);
		stage.show();
		
		preview.init();
		
		preview.start();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}

}
