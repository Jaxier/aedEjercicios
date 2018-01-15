package aed.proyectoderby.controllers;

import aed.proyectoderby.models.Conexion;
import aed.proyectoderby.view.ViewCrearTabla;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerCrearTabla {

	private ViewCrearTabla vistaCreartabla;
	private Stage ventanaStage;
	private int numFilas;
	private Conexion con;
	private String sqlQuery;
	
	public ControllerCrearTabla(Conexion conexion) {
		
		con = conexion;
		
		ventanaStage = new Stage();
		vistaCreartabla = new ViewCrearTabla();
							
		vistaCreartabla.getAgregarOtraFila().setOnAction(e -> {
			vistaCreartabla.agregarFila();
			System.out.println(numFilas);
			onLoadTipos();
			
		});
		vistaCreartabla.getSalirButton().setOnAction(e -> {
			ventanaStage.close();
		});
		
		vistaCreartabla.getCrearButton().setOnAction(e -> {
			crearTabla();
		});
		
		Scene scene = new Scene(vistaCreartabla.getView(), 600, 350);

		ventanaStage = new Stage();
		ventanaStage.setTitle("Nueva tabla");
		ventanaStage.setScene(scene);
		ventanaStage.setResizable(true);
		ventanaStage.show(); 

	}
	
	private void crearTabla() {
		
		sqlQuery = vistaCreartabla.getNombreTablaText().getText();
		sqlQuery = "CREATE TABLE "+sqlQuery+"(";
		int i = 0;
		
		sqlQuery+= vistaCreartabla.getNombreText().get(i).getText();
		sqlQuery+= " "+vistaCreartabla.getTipoDatosCombo().get(i).getSelectionModel().getSelectedItem();		
		sqlQuery+= (!vistaCreartabla.getDatosTipoAText().get(i).isDisable()) ? "("+vistaCreartabla.getDatosTipoAText().get(i).getText()+")" : "";
		sqlQuery+= (vistaCreartabla.getPrimaryKey().get(i).isSelected()) ? " PRIMARY KEY" : "";
		i++;
		for (; i < numFilas; i++) {
			sqlQuery+=", ";
			sqlQuery+= vistaCreartabla.getNombreText().get(i).getText();
			sqlQuery+= " "+vistaCreartabla.getTipoDatosCombo().get(i).getSelectionModel().getSelectedItem();
			sqlQuery+= (!vistaCreartabla.getDatosTipoAText().get(i).isDisable()) ? "("+vistaCreartabla.getDatosTipoAText().get(i).getText()+")" : "";
			sqlQuery+= (vistaCreartabla.getPrimaryKey().get(i).isSelected()) ? " PRIMARY KEY" : "";			
		}		
		sqlQuery+=")";
		
		System.out.println(sqlQuery);
		
		con.crearTabla(sqlQuery);
		
		
	}

	private void onLoadTipos() {
		int tem = numFilas;
		vistaCreartabla.getTipoTablaCombo().get(numFilas).getSelectionModel().selectedItemProperty()
		.addListener((object, oldValue, newValue) -> {
			if (newValue.equals("INTEGER")) {
				vistaCreartabla.getTipoDatosCombo().get(tem)
						.getItems().setAll(FXCollections.observableArrayList("BIGINT", "INTEGER", "INT", "SMALLINT"));
			}else if(newValue.equals("NUMERIC")){
				vistaCreartabla.getTipoDatosCombo().get(tem)
						.getItems().setAll(FXCollections.observableArrayList("DATE","NUMERIC", "TIME", "TIMESTAMP"));
			}else if(newValue.equals("REAL")){
				vistaCreartabla.getTipoDatosCombo().get(tem)
				.getItems().setAll(FXCollections.observableArrayList("DOUBLE", "FLOAT", "DECIMAL"));
			}else if(newValue.equals("TEXTO")){
				vistaCreartabla.getTipoDatosCombo().get(tem)
				.getItems().setAll(FXCollections.observableArrayList("CHAR", "VARCHAR"));
						
			}
			vistaCreartabla.getDatosTipoAText().get(tem).disableProperty().bind(
					Bindings.when(
							Bindings.equal(new SimpleStringProperty(newValue), "TEXTO").or(new SimpleStringProperty(newValue).isEqualTo("REAL")))
					
					.then(false)
					.otherwise(true)
					);
		});
		
		numFilas++;

	}
	public String getSqlQuery() {
		return sqlQuery;
	}
}
