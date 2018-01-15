package aed.proyectoderby.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PopUps{

	private Stage popUpStage;
	
	private TextField nombreDBText;
	private Label mensajeErrorLabel;
	private Button crearButton, salirButton;
	
	private ProgressIndicator progresoIndicator;
	
	public PopUps() {}
	
	public void CrearDB() {
		popUpStage = new Stage();
		
		VBox ventanaVBox = new VBox();
		HBox buttonsHBox = new HBox();
		Pane paneMid = new Pane();
		
		progresoIndicator = new ProgressIndicator();
		progresoIndicator.setProgress(-1.0);
		progresoIndicator.setVisible(false);
		
		
		nombreDBText = new TextField();
		mensajeErrorLabel = new Label();
		crearButton = new Button("Crear");
		salirButton = new Button("Salir");
		
		nombreDBText.setMaxWidth(100);
		nombreDBText.setPromptText("Indique el nombre de la base de datos");
		crearButton.setDefaultButton(true);
		
		
		/*
		 * HBox botones
		 * */
		buttonsHBox.getChildren().addAll(crearButton,salirButton);
		buttonsHBox.setAlignment(Pos.BOTTOM_RIGHT);
		buttonsHBox.setSpacing(10);
		
		/*
		 * Ventana
		 * */
		ventanaVBox.setPadding(new Insets(20));
		ventanaVBox.getChildren().addAll(nombreDBText,mensajeErrorLabel,paneMid, progresoIndicator, buttonsHBox);
		VBox.setVgrow(paneMid, Priority.ALWAYS);
		ventanaVBox.setAlignment(Pos.CENTER);
		
		
		popUpStage.setTitle("Crear base de datos");
		popUpStage.setScene(new Scene(ventanaVBox,300,150));
		popUpStage.setResizable(false);
		popUpStage.show();

	}
	
	/*
	 * Getter and Setter
	 * */

	/* PopUpCrear */
	public TextField getNombreDBText() {
		return nombreDBText;
	}

	public void setNombreDBText(TextField nombreDBText) {
		this.nombreDBText = nombreDBText;
	}

	public Label getMensajeErrorLabel() {
		return mensajeErrorLabel;
	}

	public void setMensajeErrorLabel(String mensajeErrorLabel) {
		this.mensajeErrorLabel.setText(mensajeErrorLabel);
	}

	public Button getCrearButton() {
		return crearButton;
	}

	public void setCrearButton(Button crearButton) {
		this.crearButton = crearButton;
	}

	public Button getSalirButton() {
		return salirButton;
	}

	public void setSalirButton(Button cancelarButton) {
		this.salirButton = cancelarButton;
	}
	
		
	/*
	 * Genericos
	 * */
	public ProgressIndicator getProgresoIndicator() {
		return progresoIndicator;
	}

	public void setProgresoIndicator(ProgressIndicator progresoIndicator) {
		this.progresoIndicator = progresoIndicator;
	}
	/*Stop Stage*/
	public void StopStage() {
		if (popUpStage.isShowing()) {
			popUpStage.close();
		}
	}

	

}
