package aed.proyectoderby.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;


import aed.proyectoderby.models.Conexion;
import aed.proyectoderby.view.PopUps;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

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
	private TableView<String[]> tableView;

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
	private String nombreDBActiva;
	private PopUps miPopUp;
	
	public StringProperty mensajeProperty;
	
	@SuppressWarnings("unused")
	private ControllerCrearTabla nuevatabla;
	
	/*
	 * Conexiones
	 */

	private Conexion conex = new Conexion();
	private Connection conex2;

	//
	public ControllerViewMain() {
		loader = new FXMLLoader(getClass().getResource("../view/ViewMain.fxml"));
		loader.setController(this);
	}

	@Override
	public void initialize(URL location, ResourceBundle resource) {
		miPopUp = new PopUps();
		
		CargarTreeView();
		
		mensajeProperty = new SimpleStringProperty(this, "mensajeProperty");
		
		textAreaMensajes.textProperty().bind(mensajeProperty);
		
		/*
		 * On Actions
		 */
		buttonBorrarDB.setOnAction(e -> borrarDB_OnAction());
		buttonInsertarRegistro.setOnAction(e -> insertarDatosTabla());
		
		treeViewDB.getSelectionModel().selectedItemProperty().addListener((object, oldValue, newValue) -> {
			if (newValue.getParent() != null) {
				mostrarTabla(newValue.getValue(), newValue.getParent().getValue());
				if (newValue.getParent().getParent()==null && newValue.getChildren() != null)
					nombreDBActiva=newValue.getValue();
				else
					nombreDBActiva=null;
			}
		});
		
		//TODO para modificar y eliminar
		tableView.getSelectionModel().selectedItemProperty().addListener((object, oldValue,newValue) ->{
			System.out.println("valor -> "+newValue[0]);
		});
		
		buttonCrearTabla.setOnAction(e -> nuevaTabla_OnAction());

		/* Cargar las bases de datos en el treView */

		// Crearla en segundo plano

		// conex2 = conex.CargarDB(nombreDB);
		// if (conex2!=null) {
		//
		// try {
		// Statement orden = conex2.createStatement();
		// String crear = "INSERT INTO usuarios(ID,Nombre,Apellidos,Color)
		// VALUES(1,'Jhon','Lennon','Blue')";
		//
		// orden.executeUpdate(crear);
		// System.out.println("Registro creado");
		// } catch (SQLException e) {
		// System.out.println("Error-Controlador: "+e);
		// }
		//
		// }
		// tabla();

	}
	
	private void insertarDatosTabla() {
		int size = tableView.getColumns().size();
		String columnas="";
		for (int i = 0; i < size; i++) {
			columnas+=tableView.getColumns().get(i).getText()+",";
		}
		columnas = columnas.substring(0, columnas.length()-1);
		System.out.println(columnas);
		
		ArrayList<TextField> listaTextField = new ArrayList<>();
		Stage insertarStage = new Stage();
			VBox insertarVBox = new VBox();
			GridPane grid = new GridPane();
			Button insertar = new Button("Insertar");
			Button cancelar = new Button("Cancelar");
			HBox buttonsHB = new HBox();
				buttonsHB.getChildren().addAll(insertar,cancelar);
				buttonsHB.setSpacing(10);
			for (int i = 0; i < size; i++) {
				TextField nuevo = new TextField();
				listaTextField.add(nuevo);
				String nombreColum = tableView.getColumns().get(i).getText();
				
				grid.addRow(i, new Label(nombreColum),nuevo);
				
			}
			grid.setPadding(new Insets(15));
			grid.setVgap(10);
			grid.setHgap(10);
			grid.setAlignment(Pos.TOP_CENTER);
			insertarVBox.getChildren().add(grid);
			insertarVBox.getChildren().add(buttonsHB);
			insertarStage.setScene(new Scene(insertarVBox));
			insertarStage.show();
			
	}

	private void borrarDB_OnAction() {
		if(nombreDBActiva != null) {
			
			TextInputDialog dialog = new TextInputDialog(nombreDBActiva);
			dialog.setTitle("Borrar base de datos");
//			dialog.setHeaderText("");
			dialog.setContentText("Nombre base de datos a borrar");
			
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				System.out.println(conex.borrarDB(nombreDBActiva) ? "Se ha borrado": "No se ha podido borrar");
			    
			}
			
		}else {
			
			mensajeProperty.set("Seleccione la base de datos que quiera borrar");
		}
	}

	private void nuevaTabla_OnAction() {
		
		conex.CargarDB(nombreDBActiva);
		
		nuevatabla = new ControllerCrearTabla(conex);
		
					
//		System.out.println(nuevatabla.getSqlQuery());
	}
	
	@FXML
	public void crearDB_OnAction() {

		miPopUp.CrearDB();

		miPopUp.getCrearButton().setOnAction(value -> {
			nombreDBActiva = miPopUp.getNombreDBText().getText();
			if (nombreDBActiva.length() < 3)
				miPopUp.setMensajeErrorLabel("El nombre de la base de datos ha de tener mas de cacteres");
			else
				miPopUp.setMensajeErrorLabel("Creando base de datos, espere porfavor");
			Task<Void> tarea = new Task<Void>() {

				@Override
				protected Void call() throws Exception {
					updateMessage(conex.CrearDB(nombreDBActiva));

					return null;

				}

				@Override
				protected void cancelled() {
					super.cancelled();
					updateMessage("Cancelado");
				}

			};
			tarea.setOnSucceeded(e -> {
				mensajeProperty.set(tarea.getMessage());
				miPopUp.setMensajeErrorLabel(tarea.getMessage());
			});
			tarea.setOnFailed(e -> {
				mensajeProperty.set(tarea.getMessage());
				miPopUp.setMensajeErrorLabel(tarea.getMessage());
			});
			miPopUp.getProgresoIndicator().visibleProperty().bind(tarea.runningProperty());
			;
			new Thread(tarea).start();

		});
		miPopUp.getSalirButton().setOnAction(value -> {
			miPopUp.StopStage();
		});

	}

	private boolean cellMisColumnas(TableColumn<String[], String> column, int posicion) {
		
		column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[],String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<String[], String> param) {
				String[] x = param.getValue();
							
				if (x != null && x.length > 0) {
					return new SimpleStringProperty(x[posicion]);
				} else {
					return new SimpleStringProperty("<Sin valor>");
				}
				
			}
		});
		
		return false;
	}
	
	public void mostrarTabla(String tablaSeleccionada, String basededatos) {
		
		System.out.println("DB: "+basededatos +" TABLE: "+tablaSeleccionada);
		
		conex2 = conex.CargarDB(basededatos); // Cargamos la base de datos
		
		tableView.getColumns().clear();
		tableView.getItems().clear();
		try {
			// Consulta
			int totalRegistros = 0;
			int totalColumnas = 0;

			Statement orden = conex2.createStatement();
			ResultSet resul = orden.executeQuery("SELECT count(*) FROM "+tablaSeleccionada);
			if (resul.next()) {
				totalRegistros = resul.getInt(1);
//				System.out.println("Total de registros: " + totalRegistros);
			}
			resul = orden.executeQuery("SELECT * FROM "+tablaSeleccionada);
			totalColumnas = resul.getMetaData().getColumnCount();
			
			/*
			 * Creando tabla dinamica ByJax
			 * */
			tableView.getColumns().clear();
			ObservableList<TableColumn<String[], String>> listTableColumn = FXCollections.observableArrayList();
			TableColumn<String[], String> colum;
			for (int i = 1; i <= totalColumnas; i++) {

				String nombreColumna = resul.getMetaData().getColumnName(i);
				colum = new TableColumn<>(nombreColumna);
				listTableColumn.add(colum);
				cellMisColumnas(colum, i-1);
				
			}
			
			tableView.getColumns().addAll(listTableColumn);
			
			/*
			 * Introducir los datos
			 * */
			String[][] datos = new String[totalRegistros][totalColumnas];
			int indiceFila = 0;
			while (resul.next()) {

				for (int i = 1; i <= totalColumnas; i++) {
					datos[indiceFila][i - 1] = resul.getString(i);
				}
				indiceFila++;

			}

			tableView.getItems().addAll(Arrays.asList(datos));
			mensajeProperty.set("DB: "+basededatos+"\nTabla: "+tablaSeleccionada + " cargada.");
		} catch (SQLException e) {
			System.out.println("Error-leer_a_tabla: " + e.getMessage());
		}
	}

	/*
	 * Get View
	 */

	public VBox getView() {
		try {
			view = (VBox) loader.load();
		} catch (IOException e) {
			System.err.println("Error cargar vista main: " + e.getMessage());
		}
		return view;
	}

	private void CargarTreeView() {

		ArrayList<ArrayList<String>> listaDBAll = conex.basesDeDatos();

		Node rootIconDB, rootIconTable; // Nodo para incluir imagenes
		
		if (listaDBAll.size() > 0) {
			TreeItem<String> rootNode = new TreeItem<String>("Bases de datos");
			rootNode.setExpanded(true);

			for (ArrayList<String> obj : listaDBAll) {
				ArrayList<String> obj2 = obj;
				rootIconDB = new ImageView(new Image("file:imagenes/db.png"));
				TreeItem<String> rootItem = new TreeItem<String>(obj.get(0).toString(), rootIconDB);

				rootItem.setExpanded(false);

				for (int i = 1; i < obj2.size(); i++) {
					rootIconTable = new ImageView(new Image("file:imagenes/table.png"));
					TreeItem<String> item = new TreeItem<String>(obj2.get(i), rootIconTable);

					rootItem.getChildren().add(item);

				}
				rootNode.getChildren().add(rootItem);
			}
			treeViewDB.setRoot(rootNode);
		}
	}

}
