package aed.hibernate_olimpiadas.tablas;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pruebas")
public class Pruebas implements Serializable {

	private static final long serialVersionUID = -4888979227430612293L;
	@Id
	@Column(columnDefinition = "char(5)")
	private String codPrueba;
	
	@Column(length = 40)
	private String nombrePrueba;
		
	//Get and Set
	public String getCodPrueba() {
		return codPrueba;
	}
	public void setCodPrueba(String codPrueba) {
		this.codPrueba = codPrueba;
	}
	public String getNombrePrueba() {
		return nombrePrueba;
	}
	public void setNombrePrueba(String nombrePrueba) {
		this.nombrePrueba = nombrePrueba;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
}
