package aed.proyectoderby.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import aed.proyectoderby.models.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
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
	private TreeView<String> treeViewDB;

	@FXML
	private Button buttonCrearTabla;

	@FXML
	private Button buttonBorrarTabla;

	@FXML
	private TextField textFieldBuscador;

	@FXML
	private Button buttonBuscador;

	@FXML
	private TableView<Object> tableView;

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
	
	private Conexion conex = new Conexion();
	private Connection conex2;
	
	
	public ControllerViewMain() {
		loader = new FXMLLoader(getClass().getResource("../view/ViewMain.fxml"));
		loader.setController(this);
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resource) {
		
		//Crearla en segundo plano
		
		String nombreDB = "Registro";
		conex.CrearDB(nombreDB);
		
		conex2 = conex.CargarDB(nombreDB);
		if (conex2!=null) {
			
			try {
				Statement orden = conex2.createStatement();
				String crear = "INSERT INTO usuarios(ID,Nombre,Apellidos,Color) VALUES(1,'Jhon','Lennon','Blue')";
				
				orden.executeUpdate(crear);
				System.out.println("Registro creado");
			} catch (SQLException e) {
				System.out.println("Error-Controlador: "+e);
			}
			
		}
		tabla();

	}
	
	public void tabla() {
		try {
			
			//		Consulta
			Statement orden = conex2.createStatement();
			ResultSet resul = orden.executeQuery("SELECT * FROM Usuarios");
			
			int totalColumnas = resul.getMetaData().getColumnCount();
			System.out.println("Limpiando tabla");
			tableView.getColumns().clear();
			
			
			
			System.out.println("Añadiendo columnas");
			
			for (int i = 1; i <= totalColumnas; i++) {
				
				String nombreColumna = resul.getMetaData().getColumnName(i);
				
				TableColumn<Object, String> tab = new TableColumn<>(nombreColumna);
				tableView.getColumns().add(tab);
				
			}
			ObservableList<ArrayList<String>> datosTabla = FXCollections.observableArrayList();
			
			while (resul.next()) {
				ArrayList<String> temporal = new ArrayList<>();
				for (int i = 0; i < totalColumnas; i++) {
					temporal.add(resul.getString(i));
				}
				datosTabla.add(temporal);
			}
		} catch (SQLException e) {
			System.out.println("Error-leer_a_tabla: "+e);
		}
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
