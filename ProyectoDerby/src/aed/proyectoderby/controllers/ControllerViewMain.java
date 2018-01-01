package aed.proyectoderby.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;

public class ControllerViewMain implements Initializable {

	@FXML
	private Button buttonCrearDB;

	@FXML
	private Button buttonBorrarDB;

	@FXML
	private TreeView<?> treeViewDB;

	@FXML
	private Button buttonCrearTabla;

	@FXML
	private Button buttonBorrarTabla;

	@FXML
	private TextField textFieldBuscador;

	@FXML
	private Button buttonBuscador;

	@FXML
	private TableView<?> tableView;

	@FXML
	private Button buttonInsertarRegistro;

	@FXML
	private Button buttonEliminarRegistro;

	@FXML
	private Button buttonModificarRegistro;

	@FXML
	private TextArea textAreaMensajes;

	private FXMLLoader loader;
	private VBox view;
	
	public ControllerViewMain() {
		loader = new FXMLLoader(getClass().getResource("../view/ViewMain.fxml"));
		loader.setController(this);
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resource) {
		

	}
	
	public VBox getView() {
		try {
			view =  (VBox) loader.load();
		} catch (IOException e) {
			System.err.println("Error cargar vista main: "+ e.getMessage());
		}
		return view;
	}
	

}
