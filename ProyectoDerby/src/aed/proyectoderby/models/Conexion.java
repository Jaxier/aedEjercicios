package aed.proyectoderby.models;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion {
	
	private Connection con;
	private String barra = File.separator;
	private String proyecto = System.getProperty("user.dir")+barra; //Url
	private String nombreDB;
	
	public Connection CrearDB(String nombreDB) {
		
		this.nombreDB = nombreDB;
		
		File url = new File(proyecto+nombreDB);
		
		if (url.exists()) 
			System.out.println("Ya existe la base de datos "+nombreDB);
		else
			try {
				Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
				String db = "jdbc:derby:"+proyecto+nombreDB+";create=true";
				
				con = DriverManager.getConnection(db);
				
				String tabla = "CREATE TABLE usuarios(ID INT PRIMARY KEY,Nombre VARCHAR(50),"
						+ "Apellidos VARCHAR(50), Color VARCHAR(50) )";
				
				PreparedStatement ps = con.prepareStatement(tabla);
				ps.execute();
				ps.close();
				
				System.out.println("Base de datos "+nombreDB+" creada");
				// Multi catch
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Error: "+e);
			}
		
		
		return con;
	}
	
	public Connection CargarDB(String nombreDB) {
		
		File url = new File(proyecto+nombreDB);
		
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			String db = "jdbc:derby:"+proyecto+nombreDB+";create=true";
			
			con = DriverManager.getConnection(db);
			
			System.out.println("Base de datos "+nombreDB + " cargada.");
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error: "+e);
		}
		
		return con;
	}

}
