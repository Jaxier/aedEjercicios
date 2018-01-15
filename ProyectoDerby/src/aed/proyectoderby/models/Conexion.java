package aed.proyectoderby.models;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Conexion {

	private Connection con;
	private String barra = File.separator;
	private String ruta = System.getProperty("user.dir");
	private String proyecto = ruta + barra + "dbJax" + barra; // Url
	private String nombreDB;

	public Conexion() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ArrayList<String>> basesDeDatos() {
		ArrayList<ArrayList<String>> listaBaseDatos = new ArrayList<ArrayList<String>>(); // Contiene todas las bases de
																							// datos y sus tablas
		ArrayList<String> listaTemp; // Contiene Nombre de la base y datos y sus tablas

		File url = new File(proyecto);
		if (url.exists()) {
			File[] files = url.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					nombreDB = file.getName();
					listaTemp = new ArrayList<>();
					listaTemp.add(nombreDB);

					String db = "jdbc:derby:" + proyecto + nombreDB + ";create=true";

					try {
						con = DriverManager.getConnection(db);

						DatabaseMetaData dbm = con.getMetaData();

						String[] types = { "TABLE" };

						ResultSet tablas = dbm.getTables(null, null, "%", types);

						while (tablas.next()) {
							listaTemp.add(tablas.getString("TABLE_NAME"));
							System.out.println(tablas.getString("TABLE_NAME"));
						}

						listaBaseDatos.add(listaTemp);

					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
			}
		}
		return listaBaseDatos;
	}

	public void insertarDatos(String nombreTabla, String columnas) {
		if (con != null) {
			try {
				Statement orden = con.createStatement();
				String crear = "INSERT INTO "+nombreTabla+"("+columnas+")" 
				+ " VALUES(1,'Jhon','Lennon','Blue')";
				orden.executeUpdate(crear);
				System.out.println("Registro creado");
			} catch (SQLException e) {
				System.out.println("Error-Controlador: " + e);
			}
		}
	}

	public void crearTabla(String nombreTabla) {
		System.out.println("Creando");
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(nombreTabla);
			ps.execute();
			ps.close();
			System.out.println("Tabla creada");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String CrearDB(String nombreDB) {
		String mensajeDeTransaccion = null;
		this.nombreDB = nombreDB;

		File url = new File(proyecto + nombreDB);

		if (url.exists())
			mensajeDeTransaccion = "Ya existe la base de datos " + nombreDB;
		else
			try {

				String db = "jdbc:derby:" + proyecto + nombreDB + ";create=true";

				con = DriverManager.getConnection(db);

				mensajeDeTransaccion = "Base de datos creada con exito";

			} catch (SQLException e) {
				System.out.println("Error: " + e);
				mensajeDeTransaccion = e.getMessage();
			}

		return mensajeDeTransaccion;
	}

	public Connection CargarDB(String nombreDB) {

		if (!nombreDB.equals("null")) {
			try {
				Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
				String db = "jdbc:derby:" + proyecto + nombreDB + ";create=true";

				con = DriverManager.getConnection(db);

				System.out.println("CONECTADO: Base de datos " + nombreDB);

			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Error_Conexion: " + e);
			}
		}
		return con;
	}

	public Boolean borrarDB(String nombreDB) {

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		File file = new File(proyecto + nombreDB);
		if (!file.exists()) {
			return true;
		}
		if (!file.isDirectory()) {
			return file.delete();
		}
		return this.deleteChildren(file) && file.delete();

	}

	private boolean deleteChildren(File dir) {
		File[] children = dir.listFiles();
		boolean childrenDeleted = true;
		for (int i = 0; children != null && i < children.length; i++) {
			File child = children[i];
			if (child.isDirectory()) {
				System.out.println(child.getName());
				childrenDeleted = this.deleteChildren(child) && childrenDeleted;
			}
			if (child.exists()) {
				System.out.println(child.getName());
				childrenDeleted = child.delete() && childrenDeleted;
			}
		}
		return childrenDeleted;
	}

	public void setNombreDB(String nombreDB) {
		this.nombreDB = nombreDB;
	}
}
