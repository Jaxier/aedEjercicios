package aed.hibernate_olimpiadas.tablas;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "licencias")
public class Licencias implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(generator="myForeign")
	@GenericGenerator(name="myForeign",strategy="foreign",
	parameters=	{@org.hibernate.annotations.Parameter(name = "property", value = "deportista")})
	private int IdDeportista;

	@Column(columnDefinition = "char(7)")
	private String licencia;

	@Column()
	private int NumIncidenciasLesiones;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@PrimaryKeyJoinColumn
	private Deportistas deportista;
	
	// Get and Set

	public int getIdDeportista() {
		return IdDeportista;
	}

	public void setIdDeportista(int idDeportista) {
		IdDeportista = idDeportista;
	}

	public String getLicencia() {
		return licencia;
	}

	public void setLicencia(String licencia) {
		this.licencia = licencia;
	}

	public int getNumIncidenciasLesiones() {
		return NumIncidenciasLesiones;
	}

	public void setNumIncidenciasLesiones(int numIncidenciasLesiones) {
		NumIncidenciasLesiones = numIncidenciasLesiones;
	}

	public Deportistas getDeportista() {
		return deportista;
	}

	public void setDeportista(Deportistas deportista) {
		this.deportista = deportista;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
