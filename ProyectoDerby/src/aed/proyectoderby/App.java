package aed.proyectoderby;

import aed.proyectoderby.controllers.ControllerViewMain;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		
		ControllerViewMain control = new ControllerViewMain();
		
		primaryStage.setTitle("Proyecto Derby");
		primaryStage.setScene(new Scene(control.getView()));
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);

	}
	
	public Stage getStage() {
		return primaryStage;
	}

}
