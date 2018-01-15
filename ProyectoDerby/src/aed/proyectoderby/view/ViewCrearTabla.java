package aed.proyectoderby.view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ViewCrearTabla {
	
	private BorderPane view;
	private ScrollPane scroll;
	private VBox ventanaVBox;
	private HBox buttonsHBox;
	private GridPane gridPane;
	private TextField nombreTablaText;
	private ArrayList<TextField> nombreText;
	private ArrayList<ComboBox<String>> tipoTablaCombo;
	private ArrayList<ComboBox<String>> tipoDatosCombo;
	private ArrayList<TextField> datosTipoAText;
	private ArrayList<RadioButton> primaryKey;
	private ToggleGroup grupoCheck;
	private Button crearButton, salirButton, agregarOtraFila;

	private ObservableList<String> tipoDato = FXCollections.observableArrayList("INTEGER","NUMERIC","REAL","TEXTO" );
		
	private int numeroFilas = 0; 
	
	public ViewCrearTabla() {
		
		view = new BorderPane();
		scroll = new ScrollPane();
		ventanaVBox = new VBox();
		buttonsHBox = new HBox();
		gridPane = new GridPane();
		nombreTablaText = new TextField();
		nombreText = new ArrayList<TextField>();
		tipoTablaCombo = new ArrayList<ComboBox<String>>();
		tipoDatosCombo = new ArrayList<ComboBox<String>>();
		datosTipoAText = new ArrayList<TextField>();
		primaryKey = new ArrayList<RadioButton>();
		grupoCheck = new ToggleGroup();
		agregarOtraFila = new Button();
		crearButton = new Button();
		salirButton = new Button();
		
		/*
		 * TextField
		 * */
		nombreTablaText.setPadding(new Insets(10));
		nombreTablaText.setPromptText("Nombre de la tabla");
		nombreTablaText.setFocusTraversable(false);
		nombreTablaText.setMaxWidth(200);
		/*
		 * Botones
		 */
		agregarOtraFila.setText("+ Nueva fila");

		crearButton.setText("Crear");
		crearButton.setDefaultButton(true);
		salirButton.setText("Salir");

		buttonsHBox.getChildren().addAll(crearButton, salirButton);
		buttonsHBox.setSpacing(10);
		buttonsHBox.setPadding(new Insets(15));
		buttonsHBox.setAlignment(Pos.BOTTOM_RIGHT);
		/*
		 * GridPane
		 */
		
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(5);
		gridPane.setHgap(5);
		
		gridPane.addRow(numeroFilas, new Label("Nombre de Campo"), new Label("Tipo de columna"), new Label("Tipo de dato"),new Label("Datos del tipo"), new Label("Clave primaria"));
//		gridPane.setGridLinesVisible(true);
		numeroFilas++;
		
		
		ColumnConstraints col1 = new ColumnConstraints();
		ColumnConstraints col2 = new ColumnConstraints();
		ColumnConstraints col3 = new ColumnConstraints();
		ColumnConstraints col4 = new ColumnConstraints();
		ColumnConstraints col5 = new ColumnConstraints();
		
		col1.setHgrow(Priority.ALWAYS);
		col1.setHalignment(HPos.CENTER);
		
		col2.setHgrow(Priority.NEVER);
		col2.setMaxWidth(150);
		col2.setHalignment(HPos.CENTER);
		
		col3.setHgrow(Priority.NEVER);
		col3.setMaxWidth(150);
		col3.setHalignment(HPos.CENTER);
		
		col4.setHgrow(Priority.NEVER);		
		col4.setMaxWidth(80);
		col4.setHalignment(HPos.CENTER);
		
		col5.setHgrow(Priority.NEVER);
		col5.setHalignment(HPos.CENTER);
		
		
		gridPane.getColumnConstraints().addAll(col1,col2,col3,col4,col5);
				
		/*
		 * VBox Ventana
		 * */
		ventanaVBox.getChildren().addAll(gridPane, agregarOtraFila);
		ventanaVBox.setSpacing(10);
		
		ventanaVBox.setPadding(new Insets(10));
		ventanaVBox.setAlignment(Pos.TOP_CENTER);
		
				
		VBox.setVgrow(gridPane, Priority.ALWAYS);
		
		/*
		 * Stage Ventana
		 * */
		scroll.setContent(ventanaVBox);
		scroll.setFitToHeight(true);
		scroll.setFitToWidth(true);
		
		
		view.setTop(nombreTablaText);
		view.setCenter(scroll);
		view.setBottom(buttonsHBox);
		
		BorderPane.setMargin(nombreTablaText, new Insets(15));
//		BorderPane.setAlignment(buttonsHBox, Pos.BOTTOM_LEFT);
		
		
	}
		
	public void agregarFila() {
					
		nombreText.add(new TextField());
		
		tipoTablaCombo.add(new ComboBox<>(tipoDato));
		tipoDatosCombo.add(new ComboBox<>());
		tipoDatosCombo.get(numeroFilas-1).setMaxWidth(Double.MAX_VALUE);
		datosTipoAText.add(new TextField());
		
		datosTipoAText.get(numeroFilas-1).setDisable(true);
		
		primaryKey.add(new RadioButton());
		primaryKey.get(numeroFilas-1).setToggleGroup(grupoCheck);
				
		gridPane.addRow(numeroFilas,
				nombreText.get(numeroFilas-1),
				tipoTablaCombo.get(numeroFilas-1),
				tipoDatosCombo.get(numeroFilas-1),
				datosTipoAText.get(numeroFilas-1),
				primaryKey.get(numeroFilas-1));
		
		numeroFilas++;

		
	}
	
	/* Getter and Setter*/

	public VBox getVentanaVBox() {
		return ventanaVBox;
	}

	public TextField getNombreTablaText() {
		return nombreTablaText;
	}

	public void setNombreTablaText(TextField nombreTablaText) {
		this.nombreTablaText = nombreTablaText;
	}

	public void setVentanaVBox(VBox ventanaVBox) {
		this.ventanaVBox = ventanaVBox;
	}

	public HBox getButtonsHBox() {
		return buttonsHBox;
	}

	public void setButtonsHBox(HBox buttonsHBox) {
		this.buttonsHBox = buttonsHBox;
	}

	public GridPane getGridPane() {
		return gridPane;
	}

	public void setGridPane(GridPane gridPane) {
		this.gridPane = gridPane;
	}

	public ArrayList<TextField> getNombreText() {
		return nombreText;
	}

	public void setNombreText(ArrayList<TextField> nombreText) {
		this.nombreText = nombreText;
	}

	public ArrayList<ComboBox<String>> getTipoTablaCombo() {
		return tipoTablaCombo;
	}

	public void setTipoTablaCombo(ArrayList<ComboBox<String>> tipoTablaCombo) {
		this.tipoTablaCombo = tipoTablaCombo;
	}

	public ArrayList<ComboBox<String>> getTipoDatosCombo() {
		return tipoDatosCombo;
	}

	public void setTipoDatosCombo(ArrayList<ComboBox<String>> tipoDatosCombo) {
		this.tipoDatosCombo = tipoDatosCombo;
	}

	public ArrayList<RadioButton> getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(ArrayList<RadioButton> primaryKey) {
		this.primaryKey = primaryKey;
	}

	public ToggleGroup getGrupoCheck() {
		return grupoCheck;
	}

	public void setGrupoCheck(ToggleGroup grupoCheck) {
		this.grupoCheck = grupoCheck;
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

	public void setSalirButton(Button salirButton) {
		this.salirButton = salirButton;
	}

	public Button getAgregarOtraFila() {
		return agregarOtraFila;
	}

	public void setAgregarOtraFila(Button agregarOtraFila) {
		this.agregarOtraFila = agregarOtraFila;
	}

	public ObservableList<String> getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(ObservableList<String> tipoDato) {
		this.tipoDato = tipoDato;
	}

	public Integer getNumeroFilas() {
		return numeroFilas;
	}

	public void setNumeroFilas(Integer numeroFilas) {
		this.numeroFilas = numeroFilas;
	}

	public BorderPane getView() {
		return view;
	}

	public void setView(BorderPane view) {
		this.view = view;
	}

	public ArrayList<TextField> getDatosTipoAText() {
		return datosTipoAText;
	}

	public void setDatosTipoAText(ArrayList<TextField> datosTipoAText) {
		this.datosTipoAText = datosTipoAText;
	}
	
	
	
}
